package administrativo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

import transferobject.InstituicaoTO;
import excecao.ExcecaoDeCadastro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class SwingCadastrarInstituicao extends JFrame implements AbstractGUICadastrarInstituicao{
	

	private static final long serialVersionUID = 7379748951410562202L;
	private JPanel contentPane;
	
	private ControleCadastrarInstituicao controleCadastrarInstituicao;
	
	private JComboBox<String> comboBoxInstituicao;
	
	private JTextField textFieldNomeInstituicao;
	private JTextField textFieldLocalizacao;
	private JTextField textFieldSigla;
	
	private JButton btnNovaOuCancelar;
	private JButton btnSalvar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnSair;
	
	private boolean activeAction;

	
	private InstituicaoTO[] instituicoes;
	
	private enum Acao {
		NOVO, SALVAR, EDITAR, EXCLUIR, CANCELAR, ATUALIZAR, SELECIONAR
	}
	
	
	@Override
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes) {
		
		int i = 0;
		this.instituicoes = new InstituicaoTO[instituicoes.size()];	
		String instituicaoExibicao = "";
		
		for (InstituicaoTO instituicao : instituicoes) {
			this.instituicoes[i] = instituicao; i++;
			
			instituicaoExibicao = String.valueOf(i) + " " + instituicao.getSigla() + " - " + instituicao.getNome();
			this.comboBoxInstituicao.addItem(instituicaoExibicao);
		}
	}
	
	public InstituicaoTO obterInstituicaoCriada(){
		String nomeDaInstituicao = textFieldNomeInstituicao.getText();
		String localizacao = textFieldLocalizacao.getText();
		String sigla = textFieldSigla.getText();
		
		return new InstituicaoTO(nomeDaInstituicao, sigla, localizacao);
	}
	
	public void acaoSelecionar(){
		controleCadastrarInstituicao.acaoSelecionar();
	}	
	
	public void acaoNovo(){
		controleCadastrarInstituicao.acaoNovo();
	}
	
	public void acaoSalvar(){
		controleCadastrarInstituicao.acaoSalvar();
	}
	
	public void acaoEditar(){
		controleCadastrarInstituicao.acaoEditar();
	}
	
	public void acaoExcluir(){
		controleCadastrarInstituicao.acaoExcluir();
	}
	
	public void acaoCancelar(){
		controleCadastrarInstituicao.acaoCancelar();
	}
	
	public void acaoAtualizar(){
		controleCadastrarInstituicao.acaoAtualizar();
	}
	
	@Override
	public void fechar(){
		controleCadastrarInstituicao.fechar();
	}
	
	public SwingCadastrarInstituicao(ControleCadastrarInstituicao controleCadastrarInstituicao){	
		inicializarFrame();
		this.controleCadastrarInstituicao = controleCadastrarInstituicao;
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
	public void removerSelecao(){
		this.comboBoxInstituicao.setSelectedIndex(0);
	}
	
	@Override
	public void habilitarAcaoSelecionar(){
		this.comboBoxInstituicao.setEnabled(true);
	}
		
	@Override
	public void habilitarAcaoNovo(){
		this.btnNovaOuCancelar.setText("Nova");
		this.btnNovaOuCancelar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoSalvar(){
		this.btnSalvar.setText("Salvar");
		this.btnSalvar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoAtualizar(){
		this.btnSalvar.setText("Atualizar");
		this.btnSalvar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoEditar(){
		this.btnEditar.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoExcluir(){
		this.btnExcluir.setEnabled(true);
	}
	
	@Override
	public void habilitarAcaoCancelar(){
		this.btnNovaOuCancelar.setText("Cancelar");
		this.btnNovaOuCancelar.setEnabled(true);		
	}	
	
	@Override
	public void desabilitarAcaoSelecionar(){
		this.comboBoxInstituicao.setEnabled(true);
	}
	
	@Override
	public void desabilitarAcaoNovo(){
		this.btnNovaOuCancelar.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoSalvar(){
		this.btnSalvar.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoAtualizar(){
		
	}
	
	@Override
	public void desabilitarAcaoEditar(){
		this.btnEditar.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoExcluir(){
		this.btnExcluir.setEnabled(false);
	}
	
	@Override
	public void desabilitarAcaoCancelar(){
				
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
	public void inicializar() {
		inicializarFrame();
	}
	

	/**
	 * Create the frame.
	 */
	private void inicializarFrame() {
		setTitle("Cadastrar instituicao de ensino ou pesquisa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 365);
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		this.btnNovaOuCancelar = new JButton("Nova");
		btnNovaOuCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				activeAction = true;
				
				textFieldNomeInstituicao.setEnabled(true);
				textFieldLocalizacao.setEnabled(true);
				textFieldSigla.setEnabled(true);
				
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnNovaOuCancelar.setEnabled(false);
				
				btnSalvar.setEnabled(true);
				
				activeMnemonic = 'N';
			}
		});
		this.btnNovaOuCancelar.setMnemonic('N');
		
		this.btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//acao salvar
				
				switch(activeMnemonic){
				
				case 'N':
				{			
												
						controleCadastrarInstituicao.cadastrarInstituicao(textFieldNomeInstituicao.getText(), textFieldSigla.getText(), textFieldLocalizacao.getText());
					
					
					comboBoxInstituicao.removeAllItems();
					//carregarInstituicoes();
				}
		
					break;
					
				case 'E':
					
				case 'x':
				}
				
				textFieldNomeInstituicao.setText("");
				textFieldSigla.setText("");
				textFieldLocalizacao.setText("");
				
				textFieldNomeInstituicao.setEnabled(false);
				textFieldSigla.setEnabled(false);
				textFieldLocalizacao.setEnabled(false);
				
				btnNovaOuCancelar.setEnabled(true);
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
				
				btnSalvar.setEnabled(false);
				
				activeAction = false;
			}
		});
		this.btnSalvar.setMnemonic('r');
		
		this.btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//acao editar
				
				activeAction = true;
				
				comboBoxInstituicao.setEnabled(true);
				
				btnExcluir.setEnabled(false);
				btnNovaOuCancelar.setEnabled(false);
				
				btnSalvar.setEnabled(true);
				
				activeMnemonic = 'E';
			}
		});
		this.btnEditar.setMnemonic('E');
		
		this.btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//acao excluir
				
				activeAction = true;
				
				comboBoxInstituicao.setEnabled(true);
				
				btnEditar.setEnabled(false);
				btnNovaOuCancelar.setEnabled(false);
				
				btnSalvar.setEnabled(true);
				
				activeMnemonic = 'x';
			}
		});
		this.btnExcluir.setMnemonic('x');
		
		this.btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//acao sair
				
				if(activeAction){
					
					comboBoxInstituicao.setEnabled(false);
					
					textFieldNomeInstituicao.setEnabled(false);
					textFieldLocalizacao.setEnabled(false);
					textFieldSigla.setEnabled(false);
					
					btnNovaOuCancelar.setEnabled(true);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					
					btnSalvar.setEnabled(false);
					
					activeAction = false;
				}
				
				else{
					
					fechar();	
				}
				
			}
		});
		this.btnSair.setMnemonic('r');
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.btnNovaOuCancelar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.btnSalvar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnEditar, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnExcluir, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnSair, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.btnNovaOuCancelar)
						.addComponent(this.btnSalvar)
						.addComponent(this.btnEditar)
						.addComponent(this.btnExcluir)
						.addComponent(this.btnSair))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNome = new JLabel("Nome:");
		
		this.textFieldNomeInstituicao = new JTextField();
		this.textFieldNomeInstituicao.setColumns(10);
		
		JLabel lblLocalizacao = new JLabel("Localizacao:");
		
		this.textFieldLocalizacao = new JTextField();
		this.textFieldLocalizacao.setColumns(10);
		
		JLabel lblSigla = new JLabel("Sigla:");
		
		this.textFieldSigla = new JTextField();
		this.textFieldSigla.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(this.textFieldNomeInstituicao, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
						.addComponent(lblNome)
						.addComponent(lblLocalizacao)
						.addComponent(this.textFieldLocalizacao, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
						.addComponent(lblSigla)
						.addComponent(this.textFieldSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.textFieldNomeInstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLocalizacao)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.textFieldLocalizacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSigla)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.textFieldSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		comboBoxInstituicao = new JComboBox<String>();
		
		JLabel lblInstituicao = new JLabel("Instituicao:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(this.comboBoxInstituicao, 0, 488, Short.MAX_VALUE)
						.addComponent(lblInstituicao))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInstituicao)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(this.comboBoxInstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

}
