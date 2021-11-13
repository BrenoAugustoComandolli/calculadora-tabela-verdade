package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Menu extends JFrame {

	private static final long serialVersionUID = 8585683151277643617L;
	
	private JPanel panelPlanoFundo;
	private JButton btnCalculadoraConversao;

	public static void main(String[] args) {
		Runnable run = () -> {
			try {
				var frame = new Menu();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		EventQueue.invokeLater(run);
	}

	public Menu() {

		setBackground(Color.DARK_GRAY);
		setTitle("Menu");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/imagens/akatsuki.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 318);
		setLocationRelativeTo(null);
		
		panelPlanoFundo = new JPanel();
		panelPlanoFundo.setBackground(new Color(26, 26, 26));
		panelPlanoFundo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPlanoFundo);
		panelPlanoFundo.setLayout(null);
		
		btnCalculadoraConversao = new JButton("Convers\u00E3o");
		btnCalculadoraConversao.setForeground(Color.WHITE);
		btnCalculadoraConversao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCalculadoraConversao.setBackground(Color.BLACK);
		btnCalculadoraConversao.setBounds(73, 122, 138, 36);
		btnCalculadoraConversao.setFocusPainted(false);
		panelPlanoFundo.add(btnCalculadoraConversao);
		
		var btnCalculadoraTabelaVerdade = new JButton("Tabela Verdade");
		btnCalculadoraTabelaVerdade.setForeground(Color.WHITE);
		btnCalculadoraTabelaVerdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCalculadoraTabelaVerdade.setBackground(Color.BLACK);
		btnCalculadoraTabelaVerdade.setBounds(221, 122, 138, 36);
		btnCalculadoraTabelaVerdade.setFocusPainted(false);
		panelPlanoFundo.add(btnCalculadoraTabelaVerdade);
		
		var lblItachiFundo = new JLabel("");
		lblItachiFundo.setIcon(new ImageIcon(Menu.class.getResource("/imagens/imgFundoItachi.png")));
		lblItachiFundo.setBounds(0, 0, 884, 283);
		panelPlanoFundo.add(lblItachiFundo);
		
		btnCalculadoraConversao.addActionListener(e -> abrirCalculadoraConversao());
		
		btnCalculadoraTabelaVerdade.addActionListener(e -> abrirCalculadoraTabelaVerdade());
		
	}
	
	/**
	 * 
	 * Abre a calculadora de conversão 
	 *
	 * @author Comandolli
	 */
	private void abrirCalculadoraConversao() {
		var calculadoraConversao = new CalculadoraConversao();
		calculadoraConversao.setVisible(true);
		setVisible(false);
	}
	
	/**
	 * 
	 * Abre a calculadora de tabela verdade
	 *
	 * @author Comandolli
	 */
	private void abrirCalculadoraTabelaVerdade() {
		var calculadoraTabelaVerdade = new CalculadoraTabelaVerdade();
		calculadoraTabelaVerdade.setVisible(true);
		setVisible(false);
	}
	
}
