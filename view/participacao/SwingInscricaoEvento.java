package participacao;

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
import javax.swing.JButton;

import cadastro.ControleInscricaoEvento;

public class SwingInscricaoEvento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6140181565246826400L;
	private JPanel contentPane;
	
	private ControleInscricaoEvento controleInscricaoEvento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingInscricaoEvento frame = new SwingInscricaoEvento();
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
	public SwingInscricaoEvento() {
		setResizable(false);
		setTitle("Inscri\u00E7\u00E3o em evento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Evento selecionado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnRealizarInscrio = new JButton("Realizar inscri\u00E7\u00E3o");
		btnRealizarInscrio.setMnemonic('R');
		
		JButton btnSair = new JButton("Sair");
		btnSair.setMnemonic('S');
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRealizarInscrio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRealizarInscrio)
						.addComponent(btnSair))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblTtulo = new JLabel("Nome:");
		
		JLabel lblNomeevento = new JLabel("nome_evento");
		
		JLabel lblDataDeIncio = new JLabel("Data de in\u00EDcio do evento:");
		
		JLabel lblDataDeInicioEvento = new JLabel("data_inicio_evento");
		
		JLabel lblDataDeFim = new JLabel("Data de fim do evento:");
		
		JLabel lblDatadeFimevento = new JLabel("data_de_fim_evento");
		
		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o:");
		
		JLabel lblNomeInstituicao = new JLabel("nome_instituicao");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTtulo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNomeevento, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDataDeIncio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDataDeInicioEvento, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDataDeFim)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDatadeFimevento, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblInstituio)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNomeInstituicao, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTtulo)
						.addComponent(lblNomeevento))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeIncio)
						.addComponent(lblDataDeInicioEvento)
						.addComponent(lblDataDeFim)
						.addComponent(lblDatadeFimevento))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInstituio)
						.addComponent(lblNomeInstituicao))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

}
