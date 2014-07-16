package cadastro;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;
import cadastro.ControleAtualizarUsuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingAtualizarUsuario extends JFrame implements AbstractGUIAtualizarUsuario{

	private static final long serialVersionUID = -7261933796799925187L;
	private JPanel contentPane;
	
	private JComboBox<String> comboBoxInstituicao;
	private JButton buttonAtualizar;
	private JButton buttonSair;
	
	private ControleAtualizarUsuario controleAtualizarUsuario;
	private JPasswordField passwordFieldConfirmacaoSenha;
	private JTextField textFieldEmail;
	private JPasswordField passwordFieldSenha;
	private JTextField textFieldNome;
	private JTextField textFieldUltimoNome;
	
	private InstituicaoTO[] instituicoes;


	public SwingAtualizarUsuario(ControleAtualizarUsuario controleAtualizarUsuario) {
		this.controleAtualizarUsuario = controleAtualizarUsuario;
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
		char[] _confirmacaoSenha = passwordFieldSenha.getPassword();
		String confirmcaoSenha = new String(_confirmacaoSenha);
		
		return confirmcaoSenha;
	}
	
	@Override
	public UsuarioTO obterAtualizacaoDosDadosDoUsuario() {
		String email = textFieldEmail.getText();
		char[] _senha = passwordFieldConfirmacaoSenha.getPassword();
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
	
	private void atualizar(){
		controleAtualizarUsuario.atualizar();
	}
	
	private void incluirInstituicao(){
		controleAtualizarUsuario.incluirInstituicao();
	}
	
	@Override
	public void exibirDadosDoUsuario(UsuarioTO usuario){
		textFieldEmail.setText(usuario.getEmail());
		textFieldNome.setText(usuario.getNome());
		textFieldUltimoNome.setText(usuario.getUltimoNome());
		definirSelecao(usuario.getInstituicao());
	}
	
	private void definirSelecao(InstituicaoTO instituicao){
		int i = 0;
		
		while((i < instituicoes.length) && (instituicoes[i].getSigla().compareTo(instituicao.getSigla()) != 0))
			i++;
		
		if(i < instituicoes.length)
			this.comboBoxInstituicao.setSelectedIndex(i);
		else
			this.comboBoxInstituicao.setSelectedIndex(-1);
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
	
	private void fechar() {
		controleAtualizarUsuario.fechar();
	}
	
	private void inicializarFrame(){
		setResizable(false);
		setTitle("Atualizar dados cadastrais");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 484, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		passwordFieldConfirmacaoSenha = new JPasswordField();
		
		JLabel label = new JLabel("Confirmacao senha:");
		
		JLabel label_1 = new JLabel("Email:");
		
		JLabel label_2 = new JLabel("Senha:");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		passwordFieldSenha = new JPasswordField();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 449, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(passwordFieldConfirmacaoSenha, Alignment.LEADING)
						.addComponent(label, Alignment.LEADING)
						.addComponent(label_1, Alignment.LEADING)
						.addComponent(label_2, Alignment.LEADING)
						.addComponent(textFieldEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addComponent(passwordFieldSenha, Alignment.LEADING))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 178, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_1)
					.addGap(9)
					.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2)
					.addGap(2)
					.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordFieldConfirmacaoSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label_3 = new JLabel("Instituicao:");
		
		JLabel label_4 = new JLabel("Ultimo nome:");
		
		JLabel label_5 = new JLabel("Nome:");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		textFieldUltimoNome = new JTextField();
		textFieldUltimoNome.setColumns(10);
		
		comboBoxInstituicao = new JComboBox<String>();
		
		JButton button = new JButton("Incluir nova");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirInstituicao();
			}
		});
		button.setMnemonic('i');
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 448, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(label_5)
						.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(textFieldUltimoNome)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(comboBoxInstituicao, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 194, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_5)
					.addGap(7)
					.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldUltimoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxInstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(9, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		

		buttonSair = new JButton("Sair");
		buttonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		buttonSair.setMnemonic('s');
		
		buttonAtualizar = new JButton("Atualizar");
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		buttonAtualizar.setMnemonic('A');
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonAtualizar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonSair, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(279, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonAtualizar)
						.addComponent(buttonSair))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				fechar();
			}
		});
	}

}
