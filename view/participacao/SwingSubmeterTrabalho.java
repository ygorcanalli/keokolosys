package participacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import excecao.ExcecaoDeParticipacao;
import transferobject.TrabalhoTO;
import java.awt.GridLayout;

public class SwingSubmeterTrabalho extends JFrame  implements AbstractGUISubmeterTrabalho{

	/**
	 * 
	 */
	private static final long serialVersionUID = 854489031198612100L;
	private JPanel contentPane;
	private JFileChooser jFileChooser;
	
	private JTextField txtTitulo;
	private JTextField txtAutores;
	private JTextField txtCaminhoArquivo;
	
	private JButton btnSubmeterTrabalho;
	private JButton btnSair;
	
	private JLabel lblQtdcaracteres;
	
	private ControleSubmeterTrabalho controleSubmeterTrabalho;
	private JLabel lblNomeusuariosubmissor;
	private JEditorPane txtResumo;

	public SwingSubmeterTrabalho(ControleSubmeterTrabalho controleSubmeterTrabalho) {
		this.controleSubmeterTrabalho = controleSubmeterTrabalho;		
		inicializar();
	}
	
	public void setNomeusuariosubmissor(String nomeusuariosubmissor) 
	{
		this.lblNomeusuariosubmissor.setText(nomeusuariosubmissor);		
	}
	
	private void submeterTrabalho() {
		TrabalhoTO trabalhoTO = new TrabalhoTO();
		trabalhoTO.setTitulo(txtTitulo.getText());
		trabalhoTO.setAutores(txtAutores.getText());
		trabalhoTO.setResumo(txtResumo.getText());
		trabalhoTO.setCaminhoArquivoSubmissao(txtCaminhoArquivo.getText());
		try {
			controleSubmeterTrabalho.submeterTrabalho(trabalhoTO);
		} catch (ExcecaoDeParticipacao e)
		{			
			e.printStackTrace();
		}
		
	}

	@Override
	public void inicializar() {
		inicializarFrame();
		tornarVisivel();
	}

	@Override
	public void tornarVisivel() {
		setVisible(true);
		repaint();
		
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
		JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public Integer exibirMensagemDeConfirmacao(String mensagem, String titulo,
			Object[] opcoes, Object opcaoPadrao) {
		return JOptionPane.showOptionDialog(this, mensagem, titulo, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcaoPadrao);
	}
	
	private void acaoSelecionarArquivo(){
		File file = jFileChooser.getSelectedFile();
		txtCaminhoArquivo.setText(file.getAbsolutePath());
	}
	
	private void acaoDigitarResumo(){
		lblQtdcaracteres.setText(String.valueOf(txtResumo.getText().length()));
	}
	
	private void inicializarFrame(){
		setResizable(false);
		setTitle("Submeter trabalho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Submissor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		btnSubmeterTrabalho = new JButton("Submeter trabalho");
		btnSubmeterTrabalho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submeterTrabalho();
			}
		});
		btnSubmeterTrabalho.setMnemonic('S');
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleSubmeterTrabalho.sair();
			}
		});
		btnSair.setMnemonic('r');
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSubmeterTrabalho)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(324, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmeterTrabalho)
						.addComponent(btnSair))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblTtuloDoTrabalho = new JLabel("Titulo do trabalho:");
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		
		JLabel lblResumo = new JLabel("Resumo:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblCaracteres = new JLabel("Caracteres:");
		
		lblQtdcaracteres = new JLabel("0");
		lblQtdcaracteres.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblAutores = new JLabel("Autores:");
		
		txtAutores = new JTextField();
		txtAutores.setColumns(10);
		
		JLabel lblCaminhoArquivoDo = new JLabel("Caminho arquivo do trabalho:");
		
		txtCaminhoArquivo = new JTextField();
		txtCaminhoArquivo.setColumns(10);
		
		jFileChooser = new JFileChooser();
		
		
		JButton abrirFileChoose = new JButton("Abrir");
		abrirFileChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Integer integer = jFileChooser.showOpenDialog(txtCaminhoArquivo);
				if (integer == JFileChooser.APPROVE_OPTION) {
					acaoSelecionarArquivo();
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtAutores)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(2)
									.addComponent(txtTitulo, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(23, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblTtuloDoTrabalho)
							.addContainerGap(394, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblAutores)
							.addContainerGap(465, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblResumo)
							.addContainerGap(465, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtCaminhoArquivo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
										.addComponent(lblCaracteres))
									.addGap(35)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
											.addComponent(abrirFileChoose, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addComponent(lblQtdcaracteres))))
							.addGap(21))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblCaminhoArquivoDo)
							.addContainerGap(316, Short.MAX_VALUE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTtuloDoTrabalho)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAutores)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtAutores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblResumo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCaracteres)
						.addComponent(lblQtdcaracteres))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(8)
							.addComponent(lblCaminhoArquivoDo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtCaminhoArquivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(29)
							.addComponent(abrirFileChoose, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		txtResumo = new JEditorPane();
		txtResumo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				acaoDigitarResumo();
			}
		});
		scrollPane.setViewportView(txtResumo);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNomeusuariosubmissor = new JLabel();		
		panel.add(lblNomeusuariosubmissor);
		contentPane.setLayout(gl_contentPane);
	}
}
