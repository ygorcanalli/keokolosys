package keokolosys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SwingAssociarTrabalhoABancaExaminadora extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -642612900603489710L;
	private JPanel contentPane;
	
	private ControleAssociarTrabalhoABancaExaminadora controleAssociarTrabalhoABancaExaminadora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingAssociarTrabalhoABancaExaminadora frame = new SwingAssociarTrabalhoABancaExaminadora();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwingAssociarTrabalhoABancaExaminadora() {
		setTitle("Associar trabalho a banca examinadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 473, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 262, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

}
