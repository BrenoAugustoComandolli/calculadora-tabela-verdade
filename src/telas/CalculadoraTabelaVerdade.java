package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import funcoes.ValidacaoCalculadoraTabelaVerdade;
import operacoes.OperacoesMatematicasUtil;
import operacoes.TabelaVerdadeUtil;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.DebugGraphics;

public class CalculadoraTabelaVerdade extends JFrame {

	private static final long serialVersionUID = 5546392477834728693L;

	String expressao;
	private JPanel contentPane;
	private JTable tExibicao;
	private static final String DIALOG = "Dialog";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					var frame = new CalculadoraTabelaVerdade();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.requestFocus();
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}  
		});
	}

	public CalculadoraTabelaVerdade() {
		
		setTitle("Tabela Verdade");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CalculadoraConversao.class.getResource("/imagens/akatsuki.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 670);
		setVisible(true);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		var pVisor = new JPanel();
		pVisor.setBackground(new Color(38, 38, 38));
		pVisor.setBounds(10, 56, 679, 63);
		contentPane.add(pVisor);
		pVisor.setLayout(null);
		
		var btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font(DIALOG, Font.PLAIN, 12));
		btnLimpar.setBackground(Color.BLACK);
		btnLimpar.setBounds(10, 138, 526, 48);
		btnLimpar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLimpar.setSelectedIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/naruto.png")));
		btnLimpar.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/jiraya.png")));
		pVisor.add(btnLimpar);
		
		var btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font(DIALOG, Font.PLAIN, 12));
		btnCalcular.setForeground(Color.WHITE);
		btnCalcular.setBackground(Color.BLACK);
		btnCalcular.setBounds(554, 138, 96, 48);
		pVisor.add(btnCalcular);
		
		var btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(10, 11, 76, 25);
		pVisor.add(btnVoltar);
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font(DIALOG, Font.PLAIN, 12));
		btnVoltar.setBackground(Color.BLACK);
		
		var lblImgJirayaVerdade = new JLabel("");
		lblImgJirayaVerdade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblImgJirayaVerdade.setIcon(new ImageIcon(CalculadoraTabelaVerdade.class.getResource("/imagens/JirayaVerdade.jpg")));
		lblImgJirayaVerdade.setBounds(85, 412, 500, 186);
		pVisor.add(lblImgJirayaVerdade);
		
		var tAreaDigitacao = new JTextArea();
		tAreaDigitacao.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		tAreaDigitacao.setForeground(Color.WHITE);
		tAreaDigitacao.setBackground(Color.DARK_GRAY);
		tAreaDigitacao.setFont(new Font("Arial", Font.PLAIN, 45));
		tAreaDigitacao.setBounds(10, 47, 640, 80);
		pVisor.add(tAreaDigitacao);
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String expressao = tAreaDigitacao.getText();
				if(expressao != null && !expressao.equals("")) {
					if (ValidacaoCalculadoraTabelaVerdade.validarExpressao(expressao.trim()) != null) {
						TabelaVerdadeUtil.setMatrizExibicao(TabelaVerdadeUtil.realizarAdicicaoVariaveis(expressao));
						TabelaVerdadeUtil.realizarOperacaoNot(expressao);
						TabelaVerdadeUtil.realizarOperacaoOr(expressao);
						TabelaVerdadeUtil.realizarOperacaoAnd(expressao);
						TabelaVerdadeUtil.realizaExibicaoMatriz(TabelaVerdadeUtil.getMatrizExibicao(), tExibicao);
					} else {
						tAreaDigitacao.setText("Inválido");
					}
				}
			}
		});
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var menu = new Menu();
				menu.setVisible(true);
				setVisible(false);
			}
		});
		
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacoesMatematicasUtil.setOperacao(null); 
				OperacoesMatematicasUtil.setTipoAtual(null);
				OperacoesMatematicasUtil.setValorAtual(null);
				tAreaDigitacao.setText("");
				pVisor.requestFocusInWindow();
			}
		});
		
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpar.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/jiraya.png")));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimpar.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/naruto.png")));
			}
		});
		
		tExibicao = new JTable();
		tExibicao.setForeground(Color.WHITE);
		tExibicao.setModel(new DefaultTableModel());
		tExibicao.setBorder(UIManager.getBorder("Button.border"));
		tExibicao.setBackground(Color.DARK_GRAY);
		tExibicao.setColumnSelectionAllowed(true);
		tExibicao.setCellSelectionEnabled(true);
		tExibicao.setBounds(10, 197, 640, 412);
		pVisor.add(tExibicao);
		
		var barraRolagem = new JScrollPane(tExibicao);
		barraRolagem.setAutoscrolls(true);
		barraRolagem.setBackground(Color.DARK_GRAY);
		barraRolagem.setVisible(true);
		barraRolagem.setBounds(10, 197, 640, 186);
		pVisor.add(barraRolagem);
	}
}