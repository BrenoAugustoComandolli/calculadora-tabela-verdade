package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import funcoes.ValidacaoCalculadoraTabelaVerdade;
import operacoes.TabelaVerdadeUtil;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.DebugGraphics;

public class CalculadoraTabelaVerdade extends JFrame {

	private static final long serialVersionUID = 5546392477834728693L;

	private JPanel contentPane;
	private JTable tExibicao;
	private static final String DIALOG = "Dialog";

	public static void main(String[] args) {
		Runnable run = () -> {
			try {
				var frame = new CalculadoraTabelaVerdade();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		EventQueue.invokeLater(run);
	}

	public CalculadoraTabelaVerdade() {
		
		setTitle("Tabela Verdade");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CalculadoraConversao.class.getResource("/imagens/akatsuki.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 670);
		setVisible(true);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		var pPlanoFundo = new JPanel();
		pPlanoFundo.setBackground(new Color(38, 38, 38));
		pPlanoFundo.setBounds(10, 56, 679, 63);
		contentPane.add(pPlanoFundo);
		pPlanoFundo.setLayout(null);
		
		var btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(20, 11, 94, 23);
		pPlanoFundo.add(btnVoltar);
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font(DIALOG, Font.PLAIN, 12));
		btnVoltar.setBackground(Color.BLACK);
		btnVoltar.setFocusPainted(false);
		
		var btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font(DIALOG, Font.PLAIN, 12));
		btnLimpar.setBackground(Color.BLACK);
		btnLimpar.setBounds(20, 138, 526, 48);
		btnLimpar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLimpar.setFocusPainted(false);
		btnLimpar.setSelectedIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/naruto.png")));
		btnLimpar.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/jiraya.png")));
		pPlanoFundo.add(btnLimpar);
		
		var btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font(DIALOG, Font.PLAIN, 12));
		btnCalcular.setForeground(Color.WHITE);
		btnCalcular.setBackground(Color.BLACK);
		btnCalcular.setBounds(564, 138, 96, 48);
		btnCalcular.setFocusPainted(false);
		pPlanoFundo.add(btnCalcular);
		
		var tAreaDigitacao = new JTextArea();
		tAreaDigitacao.setAutoscrolls(false);
		tAreaDigitacao.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		tAreaDigitacao.setForeground(Color.WHITE);
		tAreaDigitacao.setBackground(Color.DARK_GRAY);
		tAreaDigitacao.setFont(new Font("Arial", Font.PLAIN, 40));
		tAreaDigitacao.setBounds(20, 47, 640, 80);
		pPlanoFundo.add(tAreaDigitacao);
		
		var lblImgJirayaVerdade = new JLabel("");
		lblImgJirayaVerdade.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblImgJirayaVerdade.setIcon(new ImageIcon(CalculadoraTabelaVerdade.class.getResource("/imagens/JirayaVerdade.jpg")));
		lblImgJirayaVerdade.setBounds(88, 412, 500, 186);
		pPlanoFundo.add(lblImgJirayaVerdade);
		
		tExibicao = new JTable();
		tExibicao.setForeground(Color.WHITE);
		tExibicao.setModel(new DefaultTableModel());
		tExibicao.setBorder(UIManager.getBorder("Button.border"));
		tExibicao.setBackground(Color.DARK_GRAY);
		tExibicao.setColumnSelectionAllowed(true);
		tExibicao.setCellSelectionEnabled(true);
		tExibicao.setBounds(10, 197, 640, 412);
		pPlanoFundo.add(tExibicao);
		
		var barraRolagem = new JScrollPane(tExibicao);
		barraRolagem.setAutoscrolls(true);
		barraRolagem.setBackground(Color.DARK_GRAY);
		barraRolagem.setVisible(true);
		barraRolagem.setBounds(20, 197, 640, 186);
		pPlanoFundo.add(barraRolagem);
		
		btnVoltar.addActionListener(e -> voltarMenu());

		btnLimpar.addActionListener(e -> limpar(tAreaDigitacao, tExibicao));
		
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
		
		btnCalcular.addActionListener(e -> calcularExibirTabelaVerdade(tAreaDigitacao));
		
	}
	
	/**
	 * 
	 * Realiza operações para cálculo e exibição
	 * da tabela verdade
	 *
	 * @author Comandolli
	 * @param tAreaDigitacao
	 */
	private void calcularExibirTabelaVerdade(JTextArea tAreaDigitacao) {
		try {
			String expressao = tAreaDigitacao.getText();
			if(expressao != null && !expressao.equals("")) {
				if (ValidacaoCalculadoraTabelaVerdade.validarExpressao(expressao.trim()) != null) {
					TabelaVerdadeUtil.dividirExpressoesSeparadores(expressao);
					TabelaVerdadeUtil.realizarAdicicaoValoresCalculo(expressao);
					TabelaVerdadeUtil.realizarOperacaoesResultado();
					TabelaVerdadeUtil.montarMatrizExibicaoResultado();
					TabelaVerdadeUtil.realizaExibicaoMatriz(TabelaVerdadeUtil.getMatrizExibicao(), tExibicao);
					limparValoresTabelaUtil();
				} else {
					tAreaDigitacao.setText("Expressão Inválida");
				}
			}
		}catch (Exception e) {
			tAreaDigitacao.setText("Expressão Inválida");
		}
	}
	
	/**
	 * 
	 * Limpa os valores da classe útil 
	 * para os próximos cálculos 
	 *
	 * @author Comandolli
	 */
	private void limparValoresTabelaUtil() {
		TabelaVerdadeUtil.setMatrizExibicao(null);
		TabelaVerdadeUtil.setMatrizExpressoesResp(null);
		TabelaVerdadeUtil.setTamanhoPosibilidades(null);
		TabelaVerdadeUtil.setTamanhoExpressoes(null);
		TabelaVerdadeUtil.setListaExpressoes(null);
		TabelaVerdadeUtil.setListaExpressoesInterm(new HashMap<>());
	}
	
	/**
	 * 
	 * Limpar conteúdo da área de 
	 * digitação e dados da tabela
	 *
	 * @author Comandolli
	 * @param tAreaDigitacao
	 * @param pVisor
	 */
	private void limpar(JTextArea tAreaDigitacao, JTable tExibicao) {
		limparValoresTabelaUtil();
		tAreaDigitacao.setText("");
		tExibicao.setModel(new DefaultTableModel());
	}
	
	/**
	 * 
	 * Retorna para o menu de opções 
	 *
	 * @author Comandolli
	 */
	private void voltarMenu() {
		var menu = new Menu();
		menu.setVisible(true);
		setVisible(false);
	}
	
}