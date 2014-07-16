package cadastro;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import cadastro.ControleCadastrarUsuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class SwingCadastrarUsuario extends JFrame implements AbstractGUICadastrarUsuario{

	private static final long serialVersionUID = 5127614288668064707L;
	private JPanel contentPane;
	
	private JButton btnIncluirInstituicao;
	
	private JTextField textFieldEmail;
	private JPasswordField passwordFieldSenha;
	private JPasswordField passwordFieldConfirmacaoSenha;
	private JTextField textFieldNome;
	private JTextField textFieldUltimoNome;
	private JComboBox<String> comboBoxInstituicao;
	
	private InstituicaoTO[] instituicoes;
	
	private ControleCadastrarUsuario controleCadastrarDeUsuario;


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
		this.setEnabled(false);
		
	}

	@Override
	public void desbloquear() {
		this.setEnabled(true);
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
	public String obterConfirmacaoSenha(){
		char[] _confirmacaoSenha = passwordFieldConfirmacaoSenha.getPassword();
		String confirmcaoSenha = new String(_confirmacaoSenha);
		
		return confirmcaoSenha;
	}
	
	@Override
	public UsuarioTO obterUsuarioCriado() {
		String email = textFieldEmail.getText();
		char[] _senha = passwordFieldSenha.getPassword();
		String senha = new String(_senha);
		InstituicaoTO instituicao = capturarInstituicao();
		String nome = textFieldNome.getText();
		String ultimoNome = textFieldUltimoNome.getText();
		
		return new UsuarioTO(email, senha, nome, ultimoNome, instituicao);
	}
	
	private InstituicaoTO capturarInstituicao(){
		int i = comboBoxInstituicao.getSelectedIndex();
		
		if(i >= 0)
			return instituicoes[i];
		else
			return null;
	}
	
	private void realizarCadastro(){
		controleCadastrarDeUsuario.realizarCadastro();
	}
	
	private void incluirInstituicao(){
		controleCadastrarDeUsuario.incluirInstituicao();
	}
	
	private void fechar() {
		controleCadastrarDeUsuario.fechar();
	}
	
	public SwingCadastrarUsuario(ControleCadastrarUsuario controleDeCadastrarUsuario){
		this.controleCadastrarDeUsuario = controleDeCadastrarUsuario;
		inicializar();
	}
	
	@Override
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes){ 
		
		int i = 0;
		this.instituicoes = new InstituicaoTO[instituicoes.size()];	
		String instituicaoExibicao = "";
		
		this.comboBoxInstituicao.removeAllItems();
		
		for (InstituicaoTO instituicao : instituicoes) {
			this.instituicoes[i] = instituicao; i++;
			
			instituicaoExibicao = instituicao.getSigla() + " - " + instituicao.getNome();
			this.comboBoxInstituicao.addItem(instituicaoExibicao);
		}
	}
	
	private void inicializarFrame(){
		setResizable(false);
		setTitle("Cadastro de usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
								.addGap(6)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		
		JButton btnSalvar = new JButton("Cadastrar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarCadastro();
			}
		});
		btnSalvar.setToolTipText("C");
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnSair.setToolTipText("S");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(223, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnSair))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblEmail = new JLabel("Email:");
		
		JLabel lblSenha = new JLabel("Senha:");
		
		JLabel lblConfirmaoSenha = new JLabel("Confirmacao da senha:");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		passwordFieldSenha = new JPasswordField();
		
		passwordFieldConfirmacaoSenha = new JPasswordField();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(passwordFieldConfirmacaoSenha, Alignment.LEADING)
						.addComponent(lblConfirmaoSenha, Alignment.LEADING)
						.addComponent(lblEmail, Alignment.LEADING)
						.addComponent(lblSenha, Alignment.LEADING)
						.addComponent(textFieldEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addComponent(passwordFieldSenha, Alignment.LEADING))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmail)
					.addGap(9)
					.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSenha)
					.addGap(2)
					.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblConfirmaoSenha)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordFieldConfirmacaoSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblltimoNome = new JLabel("Ultimo nome:");
		
		JLabel lblNewLabel = new JLabel("Instituicao:");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		textFieldUltimoNome = new JTextField();
		textFieldUltimoNome.setColumns(10);
		
		comboBoxInstituicao = new JComboBox<String>();
		
		btnIncluirInstituicao = new JButton("Incluir nova");
		btnIncluirInstituicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirInstituicao();
			}
		});
		btnIncluirInstituicao.setMnemonic('i');
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel)
						.addComponent(lblltimoNome)
						.addComponent(lblNome)
						.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(textFieldUltimoNome)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(comboBoxInstituicao, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnIncluirInstituicao, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNome)
					.addGap(7)
					.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblltimoNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldUltimoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxInstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIncluirInstituicao))
					.addContainerGap(32, Short.MAX_VALUE))
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
