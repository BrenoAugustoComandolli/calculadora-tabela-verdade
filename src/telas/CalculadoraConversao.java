package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import funcoes.ValidacaoCalculadoraConversao;
import operacoes.OperacoesMatematicasUtil;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
    
public class CalculadoraConversao extends JFrame {

	private static final long serialVersionUID = 8585683151277643617L;

	private JPanel contentPane;
	
	private static final String ERRO_CONVERSAO = "Erro na Conversão";
	private static final String DIALOG = "Dialog";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					var frame = new CalculadoraConversao();
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

	public CalculadoraConversao() { 
		setTitle("Calculadora de Convers\u00E3o");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CalculadoraConversao.class.getResource("/imagens/akatsuki.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 817);
		setVisible(true);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(38, 38, 38));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		var planoFundo = new JPanel();
		planoFundo.setBackground(new Color(26, 26, 26));
		planoFundo.setBounds(0, 130, 710, 648);
		contentPane.add(planoFundo);
		planoFundo.setLayout(null);
		
		var lblItachiPlanoFundo = new JLabel("");
		lblItachiPlanoFundo.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/imgItachiFundoCalcConversao.png")));
		lblItachiPlanoFundo.setBounds(159, 451, 541, 185);
		planoFundo.add(lblItachiPlanoFundo);
		
		var rdbEntradaDecimal = new JRadioButton("Decimal");
		rdbEntradaDecimal.setBounds(578, 73, 109, 23);
		planoFundo.add(rdbEntradaDecimal);
		rdbEntradaDecimal.setSelected(true);
		
		var rdbEntradaBinario = new JRadioButton("Bin\u00E1ria");
		rdbEntradaBinario.setBounds(578, 147, 109, 23);
		planoFundo.add(rdbEntradaBinario);
		
		var rdbEntradaHexadecimal = new JRadioButton("Hexadecimal");
		rdbEntradaHexadecimal.setBounds(578, 108, 109, 23);
		planoFundo.add(rdbEntradaHexadecimal);
		
		var rdbSaidaDecimal = new JRadioButton("Decimal");
		rdbSaidaDecimal.setBounds(578, 249, 109, 23);
		planoFundo.add(rdbSaidaDecimal);
		
		var rdbSaidaBinario = new JRadioButton("Bin\u00E1ria");
		rdbSaidaBinario.setBounds(578, 323, 109, 23);
		planoFundo.add(rdbSaidaBinario);
		rdbSaidaBinario.setSelected(true);

		var rdbSaidaHexadecimal = new JRadioButton("Hexadecimal");
		rdbSaidaHexadecimal.setBounds(578, 284, 109, 23);
		planoFundo.add(rdbSaidaHexadecimal);
		
		var btnSubtracao = new JButton("-");
		btnSubtracao.setForeground(Color.WHITE);
		btnSubtracao.setBackground(Color.DARK_GRAY);
		btnSubtracao.setFont(new Font(DIALOG, Font.PLAIN, 20));
		btnSubtracao.setBounds(493, 73, 73, 53);
		planoFundo.add(btnSubtracao);
		
		var btnAdicao = new JButton("+");
		btnAdicao.setForeground(Color.WHITE);
		btnAdicao.setBackground(Color.DARK_GRAY);
		btnAdicao.setFont(new Font(DIALOG, Font.PLAIN, 20));
		btnAdicao.setBounds(493, 128, 73, 53);
		planoFundo.add(btnAdicao);
		
		var btnMultiplicacao = new JButton("X");
		btnMultiplicacao.setForeground(Color.WHITE);
		btnMultiplicacao.setBackground(Color.DARK_GRAY);
		btnMultiplicacao.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnMultiplicacao.setBounds(493, 183, 73, 53);
		planoFundo.add(btnMultiplicacao);
		
		var btnCalcularResultado = new JButton("=");
		btnCalcularResultado.setVerticalTextPosition(SwingConstants.TOP);
		btnCalcularResultado.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCalcularResultado.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/gamakichi=.png")));
		btnCalcularResultado.setForeground(Color.WHITE);
		btnCalcularResultado.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnCalcularResultado.setBackground(SystemColor.activeCaption);
		btnCalcularResultado.setBounds(493, 239, 73, 107);
		planoFundo.add(btnCalcularResultado);
	
		var btnNum0 = new JButton("0");
		btnNum0.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum0.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/hidan0.png")));
		btnNum0.setForeground(Color.WHITE);
		btnNum0.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum0.setBackground(Color.BLACK);
		btnNum0.setBounds(170, 353, 150, 87);
		planoFundo.add(btnNum0);
		
		var btnNum1 = new JButton("1");
		btnNum1.setForeground(Color.WHITE);
		btnNum1.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum1.setBackground(SystemColor.desktop);
		btnNum1.setBounds(10, 73, 150, 87);
		planoFundo.add(btnNum1);
		
		var btnNum2 = new JButton("2");
		btnNum2.setForeground(Color.WHITE);
		btnNum2.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum2.setBackground(SystemColor.desktop);
		btnNum2.setBounds(170, 73, 150, 87);
		planoFundo.add(btnNum2);
		
		var btnNum3 = new JButton("3");
		btnNum3.setForeground(Color.WHITE);
		btnNum3.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum3.setBackground(SystemColor.desktop);
		btnNum3.setBounds(330, 73, 157, 87);
		planoFundo.add(btnNum3);
		
		var btnNum4 = new JButton("4");
		btnNum4.setForeground(Color.WHITE);
		btnNum4.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum4.setBackground(SystemColor.desktop);
		btnNum4.setBounds(10, 166, 150, 87);
		planoFundo.add(btnNum4);
		
		var btnNum5 = new JButton("5");
		btnNum5.setForeground(Color.WHITE);
		btnNum5.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum5.setBackground(SystemColor.desktop);
		btnNum5.setBounds(170, 166, 150, 87);
		planoFundo.add(btnNum5);
		
		var btnNum6 = new JButton("6");
		btnNum6.setForeground(Color.WHITE);
		btnNum6.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum6.setBackground(SystemColor.desktop);
		btnNum6.setBounds(330, 166, 157, 87);
		planoFundo.add(btnNum6);
		
		var btnNum7 = new JButton("7");
		btnNum7.setForeground(Color.WHITE);
		btnNum7.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNum7.setBackground(SystemColor.desktop);
		btnNum7.setBounds(10, 259, 150, 87);
		planoFundo.add(btnNum7);

		var btnNum8 = new JButton("8");
		btnNum8.setForeground(Color.WHITE);
		btnNum8.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNum8.setBackground(SystemColor.desktop);
		btnNum8.setBounds(170, 259, 150, 87);
		planoFundo.add(btnNum8);

		var btnNum9 = new JButton("9");
		btnNum9.setForeground(Color.WHITE);
		btnNum9.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum9.setBackground(SystemColor.desktop);
		btnNum9.setBounds(330, 259, 157, 87);
		planoFundo.add(btnNum9);
		
		var btnLetraA = new JButton("A");
		btnLetraA.setVisible(false);
		btnLetraA.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/hashiramaA.png")));
		btnLetraA.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraA.setForeground(Color.WHITE);
		btnLetraA.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraA.setBackground(Color.BLACK);
		btnLetraA.setBounds(10, 353, 150, 87);
		planoFundo.add(btnLetraA);
		
		var btnLetraB = new JButton("B");
		btnLetraB.setVisible(false);
		btnLetraB.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraB.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/tobiramaB.png")));
		btnLetraB.setForeground(Color.WHITE);
		btnLetraB.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraB.setBackground(Color.BLACK);
		btnLetraB.setBounds(330, 353, 157, 87);
		planoFundo.add(btnLetraB);
		
		var btnLetraC = new JButton("C");
		btnLetraC.setVisible(false);
		btnLetraC.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/hiruzenC.png")));
		btnLetraC.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraC.setForeground(Color.WHITE);
		btnLetraC.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraC.setBackground(Color.BLACK);
		btnLetraC.setBounds(10, 451, 150, 87);
		planoFundo.add(btnLetraC);
		
		var btnLetraD = new JButton("D");
		btnLetraD.setVisible(false);
		btnLetraD.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/minatoD.png")));
		btnLetraD.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraD.setForeground(Color.WHITE);
		btnLetraD.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraD.setBackground(Color.BLACK);
		btnLetraD.setBounds(170, 451, 150, 87);
		planoFundo.add(btnLetraD);
		
		var btnLetraE = new JButton("E");
		btnLetraE.setVisible(false);
		btnLetraE.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/tsunadeE.png")));
		btnLetraE.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraE.setForeground(Color.WHITE);
		btnLetraE.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraE.setBackground(Color.BLACK);
		btnLetraE.setBounds(330, 451, 157, 87);
		planoFundo.add(btnLetraE);
		
		var btnLetraF = new JButton("F");
		btnLetraF.setVisible(false);
		btnLetraF.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/kakashiF.png")));
		btnLetraF.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraF.setForeground(Color.WHITE);
		btnLetraF.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraF.setBackground(Color.BLACK);
		btnLetraF.setBounds(170, 549, 150, 87);
		planoFundo.add(btnLetraF);
		
		var btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLimpar.setBackground(Color.BLACK);
		btnLimpar.setBounds(10, 11, 477, 53);
		planoFundo.add(btnLimpar);
		
		var pVisor = new JPanel();
		pVisor.setBackground(new Color(38, 38, 38));
		pVisor.setBounds(10, 45, 679, 74);
		contentPane.add(pVisor);
		pVisor.setLayout(null);
		
		var tAreaDigitacao = new JTextArea();
		tAreaDigitacao.setForeground(Color.WHITE);
		tAreaDigitacao.setFont(new Font("Arial", Font.PLAIN, 45));
		tAreaDigitacao.setBackground(Color.DARK_GRAY);
		tAreaDigitacao.setBounds(0, 0, 679, 74);
		pVisor.add(tAreaDigitacao);
		
		var btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font(DIALOG, Font.PLAIN, 12));
		btnVoltar.setBackground(Color.BLACK);
		btnVoltar.setBounds(10, 11, 94, 23);
		contentPane.add(btnVoltar);
		
		var lblNarutinho = new JLabel("");
		lblNarutinho.setHorizontalAlignment(SwingConstants.CENTER);
		lblNarutinho.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNarutinho.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/narutinho.png")));
		lblNarutinho.setBounds(603, 183, 57, 53);
		planoFundo.add(lblNarutinho);
		
		rdbSaidaDecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbSaidaDecimal.isSelected()) {
					ValidacaoCalculadoraConversao.limparCalculadora(tAreaDigitacao);
				}
				rdbSaidaDecimal.setSelected(true);
				rdbSaidaBinario.setSelected(false);
				rdbSaidaHexadecimal.setSelected(false);
			}
		});
		
		rdbSaidaBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbSaidaBinario.isSelected()) {
					ValidacaoCalculadoraConversao.limparCalculadora(tAreaDigitacao);
				}
				rdbSaidaDecimal.setSelected(false);
				rdbSaidaBinario.setSelected(true);
				rdbSaidaHexadecimal.setSelected(false);
			}
		});
		
		rdbSaidaHexadecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbSaidaHexadecimal.isSelected()) {
					ValidacaoCalculadoraConversao.limparCalculadora(tAreaDigitacao);
				}
				rdbSaidaDecimal.setSelected(false);
				rdbSaidaBinario.setSelected(false);
				rdbSaidaHexadecimal.setSelected(true);
			}
		});
		
		btnSubtracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacoesMatematicasUtil.setOperacao(OperacoesMatematicasUtil.SUBTRACAO);
				String valorSemPonto = tAreaDigitacao.getText().replace(".", "");
				try {
					if(!valorSemPonto.equals("")) {
						ValidacaoCalculadoraConversao.verificarAtribuicaoValorAtual(valorSemPonto, tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal);
						tAreaDigitacao.setText("");
					}
				} catch (Exception e1) {
					tAreaDigitacao.setText(ERRO_CONVERSAO);
				}
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnAdicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacoesMatematicasUtil.setOperacao(OperacoesMatematicasUtil.SOMA);
				String valorSemPonto = tAreaDigitacao.getText().replace(".", "");
				try {
					if(!valorSemPonto.equals("")) {
						ValidacaoCalculadoraConversao.verificarAtribuicaoValorAtual(valorSemPonto, tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal);
						tAreaDigitacao.setText("");
					}
				} catch (Exception e1) {
					tAreaDigitacao.setText(ERRO_CONVERSAO);
				}
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnMultiplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperacoesMatematicasUtil.setOperacao(OperacoesMatematicasUtil.MULTIPLICACAO);
				String valorSemPonto = tAreaDigitacao.getText().replace(".", "");
				try {
					if(!valorSemPonto.equals("")) {
						ValidacaoCalculadoraConversao.verificarAtribuicaoValorAtual(valorSemPonto, tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal);
						tAreaDigitacao.setText("");
					}
				} catch (Exception e1) {
					tAreaDigitacao.setText(ERRO_CONVERSAO);
				}
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnCalcularResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(OperacoesMatematicasUtil.getOperacao() != null && OperacoesMatematicasUtil.getValorAtual() != null && !tAreaDigitacao.getText().equals("")) {
					String valorSemPonto = tAreaDigitacao.getText().replace(".", "");
					try {
						valorSemPonto = ValidacaoCalculadoraConversao.realizarConversao(rdbEntradaBinario, rdbEntradaHexadecimal, valorSemPonto);
					} catch (Exception e1) { 
						tAreaDigitacao.setText(ERRO_CONVERSAO);
					}
					OperacoesMatematicasUtil.calcular(new BigDecimal(valorSemPonto));
					OperacoesMatematicasUtil.setValorAtual(ValidacaoCalculadoraConversao.convercaoValorFinal(OperacoesMatematicasUtil.getValorAtual(), rdbSaidaBinario, rdbSaidaHexadecimal));
					ValidacaoCalculadoraConversao.alterarTipoNumeroFinal(rdbSaidaBinario, rdbSaidaHexadecimal, rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal);
					tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(OperacoesMatematicasUtil.getValorAtual(), rdbSaidaDecimal));
					OperacoesMatematicasUtil.setOperacao("");
					OperacoesMatematicasUtil.setValorAtual(null);
					OperacoesMatematicasUtil.setTipoAtual(null);
				}
				planoFundo.requestFocusInWindow();
			}
		}); 
		
		btnNum1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum1.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/fotoBranco.png")));
		btnNum1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNum1.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/tobi1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNum1.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/fotoBranco.png")));
			}
		});
		
		btnNum0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "0");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow(); 
			}
		}); 
		
		btnNum1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "1");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnNum2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum2.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/zetsu2.png")));
		btnNum2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "2");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnNum3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum3.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/konan3.png")));
		btnNum3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "3");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnNum4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum4.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/deidara4.png")));
		btnNum4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "4");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnNum5.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum5.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/kakuzu5.png")));
		btnNum5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "5");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnNum6.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/pain6.png")));
		btnNum6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "6");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});

		btnNum7.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum7.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/sasori7.png")));
		btnNum7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "7");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});

		btnNum8.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/kisame8.png")));
		btnNum8.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "8");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnNum9.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum9.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/itachi9.png")));
		btnNum9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "9");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnLetraA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "A");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnLetraB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "B");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnLetraC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "C");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});

		btnLetraD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "D");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnLetraE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "E");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnLetraF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tAreaDigitacao.setText(tAreaDigitacao.getText() + "F");
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.tirarSimboloNaoBinario(tAreaDigitacao, rdbEntradaBinario);
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
				planoFundo.requestFocusInWindow();
			}
		});
		
		rdbEntradaDecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tAreaDigitacao.getText().equals("")) {
					tAreaDigitacao.setText(tAreaDigitacao.getText().replace(".", ""));
					tAreaDigitacao.setText(ValidacaoCalculadoraConversao.realizaConversaoTrocaTipo(rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal, 3, tAreaDigitacao.getText()));
				}
				lblItachiPlanoFundo.setVisible(true);
				btnLetraA.setVisible(false);
				btnLetraB.setVisible(false);
				btnLetraC.setVisible(false);
				btnLetraD.setVisible(false);
				btnLetraE.setVisible(false);
				btnLetraF.setVisible(false);
				rdbEntradaDecimal.setSelected(true); 
				rdbEntradaBinario.setSelected(false);
				rdbEntradaHexadecimal.setSelected(false);
				
				ValidacaoCalculadoraConversao.isNumeroDecimalTratando(tAreaDigitacao);
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
			}
		});
		
		rdbEntradaBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tAreaDigitacao.getText().equals("")) {
					tAreaDigitacao.setText(tAreaDigitacao.getText().replace(".", ""));
					tAreaDigitacao.setText(ValidacaoCalculadoraConversao.realizaConversaoTrocaTipo(rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal, 1, tAreaDigitacao.getText()));
				}
				lblItachiPlanoFundo.setVisible(true);
				btnLetraA.setVisible(false);
				btnLetraB.setVisible(false);
				btnLetraC.setVisible(false);
				btnLetraD.setVisible(false);
				btnLetraE.setVisible(false);
				btnLetraF.setVisible(false);
				rdbEntradaDecimal.setSelected(false);
				rdbEntradaBinario.setSelected(true);
				rdbEntradaHexadecimal.setSelected(false);
				
				ValidacaoCalculadoraConversao.isNumeroBinarioTratando(tAreaDigitacao);
				tAreaDigitacao.setText(tAreaDigitacao.getText().replace(".", ""));
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
			}
		});
		
		rdbEntradaHexadecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tAreaDigitacao.getText().equals("")) {
					tAreaDigitacao.setText(tAreaDigitacao.getText().replace(".", ""));
					tAreaDigitacao.setText(ValidacaoCalculadoraConversao.realizaConversaoTrocaTipo(rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal, 2, tAreaDigitacao.getText()));
				}
				lblItachiPlanoFundo.setVisible(false);
				btnLetraA.setVisible(true);
				btnLetraB.setVisible(true);
				btnLetraC.setVisible(true);
				btnLetraD.setVisible(true);
				btnLetraE.setVisible(true);
				btnLetraF.setVisible(true);
				rdbEntradaDecimal.setSelected(false);
				rdbEntradaBinario.setSelected(false);
				rdbEntradaHexadecimal.setSelected(true);
				
				ValidacaoCalculadoraConversao.isNumeroHexadecimalTratando(tAreaDigitacao);
				tAreaDigitacao.setText(tAreaDigitacao.getText().replace(".", ""));
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
			}
		});

		btnLimpar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpar.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/sasuke.png")));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimpar.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/sakura.png")));
			}
		});
		btnLimpar.setSelectedIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/sakura.png")));
		btnLimpar.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/sasuke.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValidacaoCalculadoraConversao.limparCalculadora(tAreaDigitacao);
				planoFundo.requestFocusInWindow();
			}
		});
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var menu = new Menu();
				menu.setVisible(true);
				setVisible(false);
			}
		});
		
		tAreaDigitacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				var valorAnteriorPrecionado = tAreaDigitacao.getText();
				if(ValidacaoCalculadoraConversao.getTextoDigitadoValidacao() == null) {
					ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(valorAnteriorPrecionado);
				}
				tAreaDigitacao.setText(ValidacaoCalculadoraConversao.getTextoDigitadoValidacao());
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(ValidacaoCalculadoraConversao.getTextoDigitadoValidacao() != null) {
					tAreaDigitacao.setText(ValidacaoCalculadoraConversao.getTextoDigitadoValidacao());
				}
			}
		});
		
	}
	
}
