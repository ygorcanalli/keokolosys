package cadastro;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.Collection;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import cadastro.ControleCadastrarEvento;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JComboBox;

import transferobject.EventoTO;
import transferobject.InstituicaoTO;
import transferobject.UsuarioTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingCadastrarEvento extends JFrame implements AbstractGUICadastrarEvento {


	private static final long serialVersionUID = 2545136064899431525L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNomeDoEvento;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblDataDeIncio;
	private JLabel lblDataMximaPara_1;
	private JLabel lblDataMximaPara;
	private JLabel lblDataDeFim;
	private JLabel lblUsuario;
	
	private JTextField textFieldNomeDoEvento;
	private JButton btnCriar;
	private JButton btnIncluir;
	private JDateChooser dateChooserDataDeInicioDoEvento;
	private JDateChooser dateChooserDataDeFimDoEvento;
	private JDateChooser dateChooserDataSubmissaoDeTrabalhos;
	private JDateChooser dateChooserDataAceitacaoDeTrabalhos;
	private JLabel lblUsuarioResponsavel;
	private JLabel lblInstituioDeOcorrncia;
	private JComboBox<String> comboBoxInstituicao;
	
	private InstituicaoTO[] instituicoes;
	
	private ControleCadastrarEvento controleCadastrarEvento;


	@Override
	public void inicializar() {
		inicializarFrame();
	}

	@Override
	public void tornarVisivel(){
		this.setVisible(true);
	}
	
	@Override
	public void tornarInvisivel(){
		this.setVisible(false);
	}
	
	@Override
	public void fechar(){
		controleCadastrarEvento.fechar();
	}
	
	@Override
	public void bloquear(){
		this.setEnabled(false);
	}
	
	@Override
	public void desbloquear(){
		this.setEnabled(true);
	}
	
	@Override
	public void atualizarListaDeInstituicoes(Collection<InstituicaoTO> instituicoes){ 
		
		int i = 0;
		this.instituicoes = new InstituicaoTO[instituicoes.size()];	
		String instituicaoExibicao = "";
		
		for (InstituicaoTO instituicao : instituicoes) {
			this.instituicoes[i] = instituicao; i++;
			
			instituicaoExibicao = instituicao.getSigla() + " - " + instituicao.getNome();
			this.comboBoxInstituicao.addItem(instituicaoExibicao);
		}
	}
	
	@Override
	public void definirUsuarioResponsavel(UsuarioTO usuarioResponsavel){
		this.lblUsuarioResponsavel.setText(usuarioResponsavel.getNome() + " : " + usuarioResponsavel.getEmail());
	}
	
	@Override
	public void criarEvento(){
		controleCadastrarEvento.criarEvento();
	}


	@Override
	public EventoTO obterEventoCriado(){
		String nome = textFieldNomeDoEvento.getText();
		InstituicaoTO instituicao = capturarInstituicao();
		Date dataDeInicio = dateChooserDataDeInicioDoEvento.getDate();
		Date dataDeFim = dateChooserDataDeFimDoEvento.getDate();
		Date dataMaximaParaSubmissaoDeTrabalhos = dateChooserDataSubmissaoDeTrabalhos.getDate();
		Date dataMaximaParaAceitacaoDeTrabalhos = dateChooserDataAceitacaoDeTrabalhos.getDate();
		
		EventoTO evento = new EventoTO();
		evento.setNome(nome);
		evento.setInstituicao(instituicao);
		evento.setDataMaximaParaSubmissaoDeTrabalhos(dataMaximaParaSubmissaoDeTrabalhos);
		evento.setDataMaximaParaAceitacaoDeTrabalhos(dataMaximaParaAceitacaoDeTrabalhos);
		evento.setDataDeInicio(dataDeInicio);
		evento.setDataDeFim(dataDeFim);
		
		return evento;
	}
		
	
	private InstituicaoTO capturarInstituicao(){
		int i = comboBoxInstituicao.getSelectedIndex();
		return instituicoes[i];
	}
	
	private void incluirNovaInstituicao(){
		controleCadastrarEvento.incluirNovaInstituicao();
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
	
	public SwingCadastrarEvento(ControleCadastrarEvento controleCadastrarEvento){
		this.controleCadastrarEvento = controleCadastrarEvento;
		inicializarFrame();
	}

	
	private void inicializarFrame(){
		setResizable(false);
		setTitle("Cadastro de Evento");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 667, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 546, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(13))
		);
		
		btnCriar = new JButton("Criar evento");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarEvento();
			}
		});
		btnCriar.setMnemonic('C');
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCriar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(238, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCriar)
						.addComponent(btnSair))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		lblDataDeIncio = new JLabel("Data de inicio do evento:");		
		dateChooserDataDeInicioDoEvento = new JDateChooser();
		lblDataDeFim = new JLabel("Data de fim do evento:");
		dateChooserDataDeFimDoEvento = new JDateChooser();
		lblDataMximaPara = new JLabel("Data maxima para submissao de trabalhos:");
		dateChooserDataSubmissaoDeTrabalhos = new JDateChooser();
		
		lblDataMximaPara_1 = new JLabel("Data maxima para aceitacao de trabalhos:");
		
		dateChooserDataAceitacaoDeTrabalhos = new JDateChooser();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataMximaPara)
						.addComponent(lblDataDeIncio)
						.addComponent(dateChooserDataDeInicioDoEvento, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooserDataSubmissaoDeTrabalhos, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooserDataAceitacaoDeTrabalhos, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataMximaPara_1)
						.addComponent(dateChooserDataDeFimDoEvento, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataDeFim))
					.addGap(44))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblDataDeIncio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserDataDeInicioDoEvento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(lblDataDeFim)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserDataDeFimDoEvento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataMximaPara)
						.addComponent(lblDataMximaPara_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateChooserDataSubmissaoDeTrabalhos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooserDataAceitacaoDeTrabalhos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		textFieldNomeDoEvento = new JTextField();
		textFieldNomeDoEvento.setColumns(10);
		
		lblNomeDoEvento = new JLabel("Nome do evento:");
		lblUsuario = new JLabel("Usuario responsavel:");
		lblUsuarioResponsavel = new JLabel("nome_usuario_responsavel");
		lblInstituioDeOcorrncia = new JLabel("Instituicao de ocorrencia do evento:");
		
		comboBoxInstituicao = new JComboBox<String>();
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirNovaInstituicao();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeDoEvento)
						.addComponent(textFieldNomeDoEvento, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
						.addComponent(lblUsuario)
						.addComponent(lblUsuarioResponsavel, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
						.addComponent(lblInstituioDeOcorrncia)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(comboBoxInstituicao, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnIncluir, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNomeDoEvento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldNomeDoEvento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUsuarioResponsavel)
					.addGap(18)
					.addComponent(lblInstituioDeOcorrncia)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxInstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIncluir))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
}
