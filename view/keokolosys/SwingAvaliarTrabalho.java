package keokolosys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class SwingAvaliarTrabalho extends JFrame {

	private JPanel contentPane;

	private ControleAvaliarTrabalho controleAvaliarTrabalho;
	private JTable tableAvaliacoesRecebidas;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingAvaliarTrabalho frame = new SwingAvaliarTrabalho();
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
	public SwingAvaliarTrabalho() {
		setResizable(false);
		setTitle("Avaliar trabalho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnAvaliarTrabalho = new JButton("Avaliar trabalho");
		btnAvaliarTrabalho.setMnemonic('A');
		
		JButton btnSair = new JButton("Sair");
		btnSair.setMnemonic('S');
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAvaliarTrabalho)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(336, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAvaliarTrabalho)
						.addComponent(btnSair))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblExaminador = new JLabel("Examinador:");
		
		JLabel lblNomeexaminador = new JLabel("nome_examinador");
		
		JLabel lblResultadoAvaliao = new JLabel("Resultado avalia\u00E7\u00E3o:");
		
		JComboBox comboBox = new JComboBox();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeexaminador, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
						.addComponent(lblExaminador)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblResultadoAvaliao)
							.addGap(38)
							.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblExaminador)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNomeexaminador)
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblResultadoAvaliao)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
		);
		
		tableAvaliacoesRecebidas = new JTable();
		tableAvaliacoesRecebidas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome examinador", "Avalia\u00E7\u00E3o recebida"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableAvaliacoesRecebidas.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableAvaliacoesRecebidas.getColumnModel().getColumn(1).setPreferredWidth(310);
		scrollPane.setViewportView(tableAvaliacoesRecebidas);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblTtuloDoTrabalho = new JLabel("T\u00EDtulo do trabalho:");
		
		JLabel lblTitulodotrabalho = new JLabel("titulo_do_trabalho");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitulodotrabalho, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
						.addComponent(lblTtuloDoTrabalho))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTtuloDoTrabalho)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTitulodotrabalho)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
