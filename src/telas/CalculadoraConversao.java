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
		Runnable run = () -> {
			try {
				var frame = new CalculadoraConversao();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		EventQueue.invokeLater(run);
	}

	public CalculadoraConversao() { 
		
		setTitle("Calculadora de Convers\u00E3o");
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
		btnSubtracao.setFocusPainted(false);
		planoFundo.add(btnSubtracao);
		
		var btnAdicao = new JButton("+");
		btnAdicao.setForeground(Color.WHITE);
		btnAdicao.setBackground(Color.DARK_GRAY);
		btnAdicao.setFont(new Font(DIALOG, Font.PLAIN, 20));
		btnAdicao.setBounds(493, 128, 73, 53);
		btnAdicao.setFocusPainted(false);
		planoFundo.add(btnAdicao);
		
		var btnMultiplicacao = new JButton("X");
		btnMultiplicacao.setForeground(Color.WHITE);
		btnMultiplicacao.setBackground(Color.DARK_GRAY);
		btnMultiplicacao.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnMultiplicacao.setBounds(493, 183, 73, 53);
		btnMultiplicacao.setFocusPainted(false);
		planoFundo.add(btnMultiplicacao);
		
		var btnCalcularResultado = new JButton("=");
		btnCalcularResultado.setVerticalTextPosition(SwingConstants.TOP);
		btnCalcularResultado.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCalcularResultado.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/gamakichi=.png")));
		btnCalcularResultado.setForeground(Color.WHITE);
		btnCalcularResultado.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnCalcularResultado.setBackground(SystemColor.activeCaption);
		btnCalcularResultado.setBounds(493, 239, 73, 107);
		btnCalcularResultado.setFocusPainted(false);
		planoFundo.add(btnCalcularResultado);
	
		var btnNum0 = new JButton("0");
		btnNum0.setForeground(Color.WHITE);
		btnNum0.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum0.setBackground(Color.BLACK);
		btnNum0.setBounds(170, 353, 150, 87);
		btnNum0.setFocusPainted(false);
		btnNum0.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum0.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/hidan0.png")));
		planoFundo.add(btnNum0);
		
		var btnNum1 = new JButton("1");
		btnNum1.setForeground(Color.WHITE);
		btnNum1.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum1.setBackground(SystemColor.desktop);
		btnNum1.setBounds(10, 73, 150, 87);
		btnNum1.setFocusPainted(false);
		btnNum1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum1.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/fotoBranco.png")));
		planoFundo.add(btnNum1);
		
		var btnNum2 = new JButton("2");
		btnNum2.setForeground(Color.WHITE);
		btnNum2.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum2.setBackground(SystemColor.desktop);
		btnNum2.setBounds(170, 73, 150, 87);
		btnNum2.setFocusPainted(false);
		btnNum2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum2.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/zetsu2.png")));
		planoFundo.add(btnNum2);
		
		var btnNum3 = new JButton("3");
		btnNum3.setForeground(Color.WHITE);
		btnNum3.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum3.setBackground(SystemColor.desktop);
		btnNum3.setBounds(330, 73, 157, 87);
		btnNum3.setFocusPainted(false);
		btnNum3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum3.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/konan3.png")));
		planoFundo.add(btnNum3);
		
		var btnNum4 = new JButton("4");
		btnNum4.setForeground(Color.WHITE);
		btnNum4.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum4.setBackground(SystemColor.desktop);
		btnNum4.setBounds(10, 166, 150, 87);
		btnNum4.setFocusPainted(false);
		btnNum4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum4.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/deidara4.png")));
		planoFundo.add(btnNum4);
		
		var btnNum5 = new JButton("5");
		btnNum5.setForeground(Color.WHITE);
		btnNum5.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum5.setBackground(SystemColor.desktop);
		btnNum5.setBounds(170, 166, 150, 87);
		btnNum5.setFocusPainted(false);
		btnNum5.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum5.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/kakuzu5.png")));
		planoFundo.add(btnNum5);
		
		var btnNum6 = new JButton("6");
		btnNum6.setForeground(Color.WHITE);
		btnNum6.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum6.setBackground(SystemColor.desktop);
		btnNum6.setBounds(330, 166, 157, 87);
		btnNum6.setFocusPainted(false);
		btnNum6.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/pain6.png")));
		btnNum6.setHorizontalAlignment(SwingConstants.LEFT);
		planoFundo.add(btnNum6);
		
		var btnNum7 = new JButton("7");
		btnNum7.setForeground(Color.WHITE);
		btnNum7.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum7.setBackground(SystemColor.desktop);
		btnNum7.setBounds(10, 259, 150, 87);
		btnNum7.setFocusPainted(false);
		btnNum7.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum7.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/sasori7.png")));
		planoFundo.add(btnNum7);

		var btnNum8 = new JButton("8");
		btnNum8.setForeground(Color.WHITE);
		btnNum8.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum8.setBackground(SystemColor.desktop);
		btnNum8.setBounds(170, 259, 150, 87);
		btnNum8.setFocusPainted(false);
		btnNum8.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/kisame8.png")));
		btnNum8.setHorizontalAlignment(SwingConstants.LEFT);
		planoFundo.add(btnNum8);

		var btnNum9 = new JButton("9");
		btnNum9.setForeground(Color.WHITE);
		btnNum9.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnNum9.setBackground(SystemColor.desktop);
		btnNum9.setBounds(330, 259, 157, 87);
		btnNum9.setFocusPainted(false);
		btnNum9.setHorizontalAlignment(SwingConstants.LEFT);
		btnNum9.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/itachi9.png")));
		planoFundo.add(btnNum9);
		
		var btnLetraA = new JButton("A");
		btnLetraA.setVisible(false);
		btnLetraA.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/hashiramaA.png")));
		btnLetraA.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraA.setForeground(Color.WHITE);
		btnLetraA.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraA.setBackground(Color.BLACK);
		btnLetraA.setBounds(10, 353, 150, 87);
		btnLetraA.setFocusPainted(false);
		planoFundo.add(btnLetraA);
		
		var btnLetraB = new JButton("B");
		btnLetraB.setVisible(false);
		btnLetraB.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraB.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/tobiramaB.png")));
		btnLetraB.setForeground(Color.WHITE);
		btnLetraB.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraB.setBackground(Color.BLACK);
		btnLetraB.setBounds(330, 353, 157, 87);
		btnLetraB.setFocusPainted(false);
		planoFundo.add(btnLetraB);
		
		var btnLetraC = new JButton("C");
		btnLetraC.setVisible(false);
		btnLetraC.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/hiruzenC.png")));
		btnLetraC.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraC.setForeground(Color.WHITE);
		btnLetraC.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraC.setBackground(Color.BLACK);
		btnLetraC.setBounds(10, 451, 150, 87);
		btnLetraC.setFocusPainted(false);
		planoFundo.add(btnLetraC);
		
		var btnLetraD = new JButton("D");
		btnLetraD.setVisible(false);
		btnLetraD.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/minatoD.png")));
		btnLetraD.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraD.setForeground(Color.WHITE);
		btnLetraD.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraD.setBackground(Color.BLACK);
		btnLetraD.setBounds(170, 451, 150, 87);
		btnLetraD.setFocusPainted(false);
		planoFundo.add(btnLetraD);
		
		var btnLetraE = new JButton("E");
		btnLetraE.setVisible(false);
		btnLetraE.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/tsunadeE.png")));
		btnLetraE.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraE.setForeground(Color.WHITE);
		btnLetraE.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraE.setBackground(Color.BLACK);
		btnLetraE.setBounds(330, 451, 157, 87);
		btnLetraE.setFocusPainted(false);
		planoFundo.add(btnLetraE);
		
		var btnLetraF = new JButton("F");
		btnLetraF.setVisible(false);
		btnLetraF.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/kakashiF.png")));
		btnLetraF.setHorizontalAlignment(SwingConstants.LEFT);
		btnLetraF.setForeground(Color.WHITE);
		btnLetraF.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLetraF.setBackground(Color.BLACK);
		btnLetraF.setBounds(170, 549, 150, 87);
		btnLetraF.setFocusPainted(false);
		planoFundo.add(btnLetraF);
		
		var btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font(DIALOG, Font.PLAIN, 16));
		btnLimpar.setBackground(Color.BLACK);
		btnLimpar.setBounds(10, 11, 477, 53);
		btnLimpar.setFocusPainted(false);
		btnLimpar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLimpar.setSelectedIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/sakura.png")));
		btnLimpar.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/sasuke.png")));
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
		btnVoltar.setFocusPainted(false);
		contentPane.add(btnVoltar);
		
		var lblNarutinho = new JLabel("");
		lblNarutinho.setHorizontalAlignment(SwingConstants.CENTER);
		lblNarutinho.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNarutinho.setIcon(new ImageIcon(CalculadoraConversao.class.getResource("/imagens/narutinho.png")));
		lblNarutinho.setBounds(603, 183, 57, 53);
		planoFundo.add(lblNarutinho);
		
		rdbSaidaDecimal.addActionListener(e -> selecionarTipoConversao(true, false, false, rdbSaidaDecimal, 
		rdbSaidaBinario, rdbSaidaHexadecimal, tAreaDigitacao));
		
		rdbSaidaBinario.addActionListener(e -> selecionarTipoConversao(false, true, false, rdbSaidaDecimal, 
		rdbSaidaBinario, rdbSaidaHexadecimal, tAreaDigitacao));
		
		rdbSaidaHexadecimal.addActionListener(e -> selecionarTipoConversao(false, false, true, rdbSaidaDecimal, 
		rdbSaidaBinario, rdbSaidaHexadecimal, tAreaDigitacao));
		
		btnSubtracao.addActionListener(e -> realizarOperacaoBotao(tAreaDigitacao, OperacoesMatematicasUtil.SUBTRACAO, 
		rdbEntradaBinario, rdbEntradaHexadecimal));
		
		btnAdicao.addActionListener(e -> realizarOperacaoBotao(tAreaDigitacao, OperacoesMatematicasUtil.SOMA, 
		rdbEntradaBinario, rdbEntradaHexadecimal));
		
		btnMultiplicacao.addActionListener(e -> realizarOperacaoBotao(tAreaDigitacao, OperacoesMatematicasUtil.MULTIPLICACAO, 
		rdbEntradaBinario, rdbEntradaHexadecimal));
		
		btnCalcularResultado.addActionListener(e -> calcularResultadoFinal(tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal,
		rdbEntradaDecimal, rdbSaidaBinario, rdbSaidaHexadecimal, rdbSaidaDecimal)); 
		
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
		
		btnNum0.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "0")); 
		btnNum1.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "1")); 
		btnNum2.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "2")); 
		btnNum3.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "3")); 
		btnNum4.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "4"));
		btnNum5.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "5"));
		btnNum6.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "6"));
		btnNum7.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "7"));
		btnNum8.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "8"));
		btnNum9.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "9"));
		
		btnLetraA.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "A"));
		btnLetraB.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "B"));
		btnLetraC.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "C"));
		btnLetraD.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "D"));
		btnLetraE.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "E"));
		btnLetraF.addActionListener(e -> adicionaValorNumericoBotao(tAreaDigitacao, rdbEntradaDecimal, rdbEntradaBinario, "F"));
		
		rdbEntradaBinario.addActionListener(e -> {
			realizaConversaoTrocaTipo(tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal, 1);
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
			realizaValidacaoTrocaTipo(tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal);
		});
		
		rdbEntradaHexadecimal.addActionListener(e -> {
			realizaConversaoTrocaTipo(tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal, 2);
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
			realizaValidacaoTrocaTipo(tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal);
		});
		
		rdbEntradaDecimal.addActionListener(e -> {
			realizaConversaoTrocaTipo(tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal, 3);
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
			realizaValidacaoTrocaTipo(tAreaDigitacao, rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal);
		});
		
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
		
		btnLimpar.addActionListener(e -> ValidacaoCalculadoraConversao.limparCalculadora(tAreaDigitacao));
		btnVoltar.addActionListener(e -> voltarMenu());
		
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
	
	/**
	 * 
	 * Realiza troca de tipo de dado 
	 * no componente de seleção
	 *
	 * @author Comandolli
	 * @param selectedDecimal
	 * @param selectedBinario
	 * @param selectedHexadecimal
	 * @param rdbDecimal
	 * @param rdbBinario
	 * @param rdbHexadecimal
	 * @param tAreaDigitacao
	 */
	private void selecionarTipoConversao(Boolean selectedDecimal, Boolean selectedBinario, Boolean selectedHexadecimal,
			                     JRadioButton rdbDecimal, JRadioButton rdbBinario, JRadioButton rdbHexadecimal,
			                     JTextArea tAreaDigitacao) {
		if(rdbDecimal.isSelected()) {
			ValidacaoCalculadoraConversao.limparCalculadora(tAreaDigitacao);
		}
		rdbDecimal.setSelected(selectedDecimal);
		rdbBinario.setSelected(selectedBinario);
		rdbHexadecimal.setSelected(selectedHexadecimal);
	}
	
	/**
	 * 
	 * Realiza a ação de clique na operação do botão,
	 * definido a operação atual e caso seja uma sequencia de operações 
	 * é feito o calculo temporário até clicar no resultado
	 *
	 * @author Comandolli
	 * @param tAreaDigitacao
	 * @param operacaoEscolhida
	 * @param rdbBinario
	 * @param rdbHexadecimal
	 */
	private void realizarOperacaoBotao(JTextArea tAreaDigitacao, String operacaoEscolhida, JRadioButton rdbBinario, JRadioButton rdbHexadecimal) {
		OperacoesMatematicasUtil.setOperacao(operacaoEscolhida);
		String valorSemPonto = tAreaDigitacao.getText().replace(".", "");
		try {
			if(!valorSemPonto.equals("")) {
				ValidacaoCalculadoraConversao.verificarAtribuicaoValorAtual(valorSemPonto, tAreaDigitacao, rdbBinario, rdbHexadecimal);
				tAreaDigitacao.setText("");
			}
		} catch (Exception e1) {
			tAreaDigitacao.setText(ERRO_CONVERSAO);
		}
	}
	
	/**
	 * 
	 * Realizar cálculo final do resultado, tirando os pontos, realizado as conversões
	 * para decimal para realização das operações e convertendo para o tipo escolhido 
	 * de exibição, depois de mostrado o resultado é limpo os valores e operações
	 *
	 * @author Comandolli
	 * @param tAreaDigitacao
	 * @param rdbEntradaBinario
	 * @param rdbEntradaHexadecimal
	 * @param rdbEntradaDecimal
	 * @param rdbSaidaBinario
	 * @param rdbSaidaHexadecimal
	 * @param rdbSaidaDecimal
	 */
	private void calcularResultadoFinal(JTextArea tAreaDigitacao, JRadioButton rdbEntradaBinario, JRadioButton rdbEntradaHexadecimal, 
										JRadioButton rdbEntradaDecimal, JRadioButton rdbSaidaBinario, JRadioButton rdbSaidaHexadecimal, 
										JRadioButton rdbSaidaDecimal) { 
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
			tAreaDigitacao.setText(tAreaDigitacao.getText().equals("") ? "0" : tAreaDigitacao.getText());
			OperacoesMatematicasUtil.setOperacao("");
			OperacoesMatematicasUtil.setValorAtual(null);
			OperacoesMatematicasUtil.setTipoAtual(null);
		}
	}
	
	/**
	 * 
	 * Atribui o valor do botão a área de texto,
	 * adicionando pontuação milhar se for decimal
	 *
	 * @author Comandolli
	 * @param tAreaDigitacao
	 * @param rdbEntradaDecimal
	 * @param valorBotao
	 */
	private void adicionaValorNumericoBotao(JTextArea tAreaDigitacao, JRadioButton rdbEntradaDecimal, JRadioButton rdbEntradaBinario,
			                                String valorBotao) {
		if((rdbEntradaBinario.isSelected() && (valorBotao.equals("0") || 
		    valorBotao.equals("1"))) || !rdbEntradaBinario.isSelected()) {
			
			tAreaDigitacao.setText(tAreaDigitacao.getText() + valorBotao);
			tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
			ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
			
			if(!valorBotao.equals("0") && !valorBotao.equals("1")) {
				ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
			}
		}
	}
	
	/**
	 * 
	 * Faz a validação de troca de tipo, tirando a pontuação e 
	 * convertendo o valor para o selecionado 
	 *
	 * @author Comandolli
	 * @param tAreaDigitacao
	 * @param rdbEntradaBinario
	 * @param rdbEntradaHexadecimal
	 * @param rdbEntradaDecimal
	 */
	private void realizaConversaoTrocaTipo(JTextArea tAreaDigitacao, JRadioButton rdbEntradaBinario, JRadioButton rdbEntradaHexadecimal,
			                               JRadioButton rdbEntradaDecimal, Integer tipoConversao) {
		if(!tAreaDigitacao.getText().equals("")) {
			tAreaDigitacao.setText(tAreaDigitacao.getText().replace(".", ""));
			tAreaDigitacao.setText(ValidacaoCalculadoraConversao.realizaConversaoTrocaTipo(rdbEntradaBinario, rdbEntradaHexadecimal, rdbEntradaDecimal, tipoConversao, tAreaDigitacao.getText()));
		}
	}
	
	/**
	 * 
	 * Verifica qual está selecionado para então fazer a 
	 * validação da troca dos tipos 
	 *
	 * @author Comandolli
	 * @param tAreaDigitacao
	 * @param rdbEntradaBinario
	 * @param rdbEntradaHexadecimal
	 * @param rdbEntradaDecimal
	 */
	private void realizaValidacaoTrocaTipo(JTextArea tAreaDigitacao, JRadioButton rdbEntradaBinario, JRadioButton rdbEntradaHexadecimal,
	                                       JRadioButton rdbEntradaDecimal) {
		if(rdbEntradaBinario.isSelected()) {
			ValidacaoCalculadoraConversao.isNumeroBinarioTratando(tAreaDigitacao);
			tAreaDigitacao.setText(tAreaDigitacao.getText().replace(".", ""));
			ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
		}else if(rdbEntradaHexadecimal.isSelected()) {
			ValidacaoCalculadoraConversao.isNumeroHexadecimalTratando(tAreaDigitacao);
			tAreaDigitacao.setText(tAreaDigitacao.getText().replace(".", ""));
			ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
		}else {
			ValidacaoCalculadoraConversao.isNumeroDecimalTratando(tAreaDigitacao);
			tAreaDigitacao.setText(ValidacaoCalculadoraConversao.adicionarPontuacaoMilhar(tAreaDigitacao.getText(), rdbEntradaDecimal));
			ValidacaoCalculadoraConversao.setTextoDigitadoValidacao(tAreaDigitacao.getText());
		}
	}
	
}
