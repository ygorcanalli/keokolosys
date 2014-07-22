package participacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

import excecao.ExcecaoDeParticipacao;
import transferobject.TrabalhoTO;
import util.AbstractGUI;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class SwingSubmeterVersaoFinalDeTrabalho extends JFrame implements AbstractGUI{

	private JPanel contentPane;
	private JTextField textField;
	private JLabel titulodotrabalho;
	
	private JFileChooser jFileChooser;

	private ControleSubmeterVersaoFinalDeTrabalho controleSubmeterVersaoFinalTrabalho;
	
	
	/**
	 * Create the frame.
	 */
	private void acaoSelecionarArquivo(){
		File file = jFileChooser.getSelectedFile();
		textField.setText(file.getAbsolutePath());
	}
	
	private void submeterVersaoFinal(){
		
		TrabalhoTO trabalhoTO = new TrabalhoTO();
		trabalhoTO.setCaminhoArquivoFinal(textField.getText());
		
		try {
			controleSubmeterVersaoFinalTrabalho.submeterVersaoFinal(trabalhoTO);
			
		} catch (ExcecaoDeParticipacao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SwingSubmeterVersaoFinalDeTrabalho(final ControleSubmeterVersaoFinalDeTrabalho controleSubmeterVersaoFinalTrabalho) {
		this.controleSubmeterVersaoFinalTrabalho = controleSubmeterVersaoFinalTrabalho;
		
		setResizable(false);
		setTitle("Submeter vers\u00E3o final de trabalho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSubmeterVersoFinal = new JButton("Submeter vers\u00E3o final");
		btnSubmeterVersoFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submeterVersaoFinal();
			}
		});
		btnSubmeterVersoFinal.setMnemonic('S');
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleSubmeterVersaoFinalTrabalho.sair();
			}
		});
		btnSair.setMnemonic('r');
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSubmeterVersoFinal)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmeterVersoFinal)
						.addComponent(btnSair))
					.addContainerGap())
		);
		
		JLabel lblCaminhoVersoFinal = new JLabel("Caminho vers\u00E3o final do trabalho:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		jFileChooser = new JFileChooser();
		
		JButton abrirFileChooser = new JButton("Abrir");
		abrirFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer integer = jFileChooser.showOpenDialog(textField);
				if (integer == JFileChooser.APPROVE_OPTION) {
					acaoSelecionarArquivo();
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(abrirFileChooser, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblCaminhoVersoFinal))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCaminhoVersoFinal)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(abrirFileChooser))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTtuloDoTrabalho = new JLabel("T\u00EDtulo do trabalho:");
		panel.add(lblTtuloDoTrabalho);
		
		titulodotrabalho = new JLabel("titulo_do_trabalho");
		panel.add(titulodotrabalho);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setTitulodotrabalho(String titulodotrabalho) {
		this.titulodotrabalho.setText(titulodotrabalho);
	}

	@Override
	public void inicializar() {
		tornarVisivel();
		repaint(10);
		
	}


	@Override
	public void tornarVisivel() {
		setVisible(true);
		
	}


	@Override
	public void tornarInvisivel() {
		setVisible(false);
		
	}


	@Override
	public void bloquear() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void desbloquear() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void exibirMensagemDeErro(String mensagem, String titulo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void exibirMensagemDeAviso(String mensagem, String titulo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void exibirMensagemDeInformacao(String mensagem, String titulo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Integer exibirMensagemDeConfirmacao(String mensagem, String titulo,
			Object[] opcoes, Object opcaoPadrao) {
		// TODO Auto-generated method stub
		return null;
	}
}
