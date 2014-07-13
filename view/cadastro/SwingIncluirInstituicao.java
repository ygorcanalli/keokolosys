package cadastro;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import transferobject.InstituicaoTO;
import cadastro.ControleIncluirInstituicao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingIncluirInstituicao extends JFrame implements AbstractGUIIncluirInstituicao{


	private static final long serialVersionUID = -3896949162725193015L;
	private JPanel contentPane;
	
	private ControleIncluirInstituicao controleIncluirInstituicao;
	private JTextField textFieldNome;
	private JTextField textFieldLocalizacao;
	private JTextField textFieldSigla;

	public SwingIncluirInstituicao(ControleIncluirInstituicao controleIncluirInstituicao) {
		this.controleIncluirInstituicao = controleIncluirInstituicao;
		inicializarFrame();
	}

	@Override
	public void inicializar() {
		inicializarFrame();
	}

	@Override
	public void tornarVisivel() {
		this.setVisible(true);
		
	}

	@Override
	public void tornarInvisivel() {
		this.setVisible(false);
	}

	@Override
	public void bloquear() {
		this.setEnabled(true);
		
	}

	@Override
	public void desbloquear() {
		this.setEnabled(false);
	}

	@Override
	public void exibirMensagemDeErro(String mensagem, String titulo){
		JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void exibirMensagemDeAviso(String mensagem, String titulo){
		JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
	}
	
	@Override
	public void exibirMensagemDeInformacao(String mensagem, String titulo){
		JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public Integer exibirMensagemDeConfirmacao(String mensagem, String titulo, Object[] opcoes, Object opcaoPadrao){
		return JOptionPane.showOptionDialog(this, mensagem, titulo, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcaoPadrao);
	}
	
	@Override
	public void incluir(){
		controleIncluirInstituicao.incluir();
	}
	
	@Override
	public void fechar(){
		controleIncluirInstituicao.fechar();
	}
	
	@Override
	public InstituicaoTO obterDadosDaInstituicaoPreenchida() {
		String nomeDaInstituicao = textFieldNome.getText();
		String localizacao = textFieldLocalizacao.getText();
		String sigla = textFieldSigla.getText();
		
		return new InstituicaoTO(nomeDaInstituicao, sigla, localizacao);
	}
	
	private void inicializarFrame(){
		setTitle("Incluir nova instituicao de ensino ou pesquisa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 503, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JButton btnIncluirInstituicao = new JButton("Incluir");
		btnIncluirInstituicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir();
			}
		});
		btnIncluirInstituicao.setMnemonic('I');
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnSair.setMnemonic('S');
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnIncluirInstituicao, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(282, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluirInstituicao)
						.addComponent(btnSair))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNomeDaInstituicao = new JLabel("Nome:");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		JLabel lblLocalizacao = new JLabel("Localizacao:");
		
		textFieldLocalizacao = new JTextField();
		textFieldLocalizacao.setColumns(10);
		
		JLabel lblSiglaDaInstituicao = new JLabel("Sigla:");
		
		textFieldSigla = new JTextField();
		textFieldSigla.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
						.addComponent(lblNomeDaInstituicao)
						.addComponent(lblLocalizacao)
						.addComponent(textFieldLocalizacao, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
						.addComponent(lblSiglaDaInstituicao)
						.addComponent(textFieldSigla, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNomeDaInstituicao)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblLocalizacao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldLocalizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSiglaDaInstituicao)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				fechar();
			}
		});
	}

}
