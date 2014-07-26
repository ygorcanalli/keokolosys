package avaliacao;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import transferobject.BancaExaminadoraTO;
import transferobject.TrabalhoTO;
import transferobject.UsuarioTO;
import avaliacao.ControleAssociarTrabalhoABancaExaminadora;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class SwingAssociarTrabalhoABancaExaminadora extends JFrame implements AbstractGUIAssociarTrabalhoABancaExaminadora{

	private static final long serialVersionUID = -642612900603489710L;
	private JPanel contentPane;
	
	private JButton btnAssociarBancaExaminadora;
	private JButton btnGerenciarBancasExaminadoras;
	private JButton btnSair;
	
	private ControleAssociarTrabalhoABancaExaminadora controleAssociarTrabalhoABancaExaminadora;
	private JTable tabelaDeBancasExaminadoras;
	private JLabel lblTitulodotrabalho;
	private JLabel lblNomesubmissor;
	private JLabel lblCaminho;
	private JTextField textFieldCaminho;
	private JLabel lblAutores;
	private JLabel lblNomeDosAutores;
	
	private BancaExaminadoraTO bancasExaminadoras[];
	private JScrollPane scrollPane_1;
	private JTextArea textAreaResumo;
	
	public SwingAssociarTrabalhoABancaExaminadora(ControleAssociarTrabalhoABancaExaminadora controleAssociarTrabalhoABancaExaminadora) {
		this.controleAssociarTrabalhoABancaExaminadora = controleAssociarTrabalhoABancaExaminadora;
		inicializarFrame();
	}
	
	private void fechar() {
		controleAssociarTrabalhoABancaExaminadora.fechar();
	}
	
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
	public void bloquear(){
		this.setEnabled(false);
	}
	
	@Override
	public void desbloquear(){
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
	public void atualizarListDeBancasExaminadoras(Collection<BancaExaminadoraTO> bancasExaminadoras){
		atualizarTabelaDeBancasExaminadoras(bancasExaminadoras);
	}
	
	private void associarABancaExaminadora(){
		controleAssociarTrabalhoABancaExaminadora.associarABancaExaminadora();
	}
	
	private void gerenciarBancasExaminadoras(){
		controleAssociarTrabalhoABancaExaminadora.gerenciarBancasExaminadoras();
	}
	
	@Override
	public BancaExaminadoraTO obterBancaExaminadoraSelecionada() {
		return capturarBancaExaminadora(tabelaDeBancasExaminadoras.getSelectedRow());
	}
	
	@Override
	public void definirTrabalho(TrabalhoTO trabalho){
		this.lblTitulodotrabalho.setText(trabalho.getTitulo());
		this.lblNomesubmissor.setText(trabalho.getSubmissor().getNome() + " : " + trabalho.getSubmissor().getEmail());
		this.textFieldCaminho.setText(trabalho.getCaminhoArquivoSubmissao());
		this.textAreaResumo.setText(trabalho.getResumo());
		this.lblNomeDosAutores.setText(trabalho.getAutores());
	}
	
	private void atualizarTabelaDeBancasExaminadoras(Collection<BancaExaminadoraTO> bancasExaminadoras){
		Iterator<BancaExaminadoraTO> iteradorBancasExaminadoras = bancasExaminadoras.iterator();
		String[] colunas = {" ", "Examinador[1]","Examinador[2]", "Examinador[3]", "Quantidade de trabalhos associados"};
		String[][] dados = new String[bancasExaminadoras.size()][5];
		int j;
		
		this.bancasExaminadoras = new BancaExaminadoraTO[bancasExaminadoras.size()];
		
		for (int i = 0; i < bancasExaminadoras.size(); i++) {
			BancaExaminadoraTO bancaExaminadoraTO = iteradorBancasExaminadoras.next();
			this.bancasExaminadoras[i] = bancaExaminadoraTO;
			
			dados[i][0] = String.valueOf(i + 1);
			j = 1;
			
			for (UsuarioTO examinador : bancaExaminadoraTO.getExaminadores()) {
				dados[i][j] = examinador.getNome() + " : " + examinador.getEmail();
				j++;
			}
			
			dados[i][4] = String.valueOf(bancaExaminadoraTO.getQuantidadeDeTrabalhosAssociados());
			
			tabelaDeBancasExaminadoras.setModel(new DefaultTableModel(dados, colunas));
		}
	}

	private BancaExaminadoraTO capturarBancaExaminadora(Integer index){
		int i = index;
		
		if(i >= 0)
			return this.bancasExaminadoras[i];
		else
			return null;
	}
	
	private void inicializarFrame(){
		setTitle("Associar trabalho a banca examinadora");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 635, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Trabalho", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		
		btnAssociarBancaExaminadora = new JButton("Associar");
		btnAssociarBancaExaminadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				associarABancaExaminadora();
			}
		});
		btnAssociarBancaExaminadora.setMnemonic('A');
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnSair.setMnemonic('S');
		
		btnGerenciarBancasExaminadoras = new JButton("Gerenciar bancas examinadoras");
		btnGerenciarBancasExaminadoras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerenciarBancasExaminadoras();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAssociarBancaExaminadora)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGerenciarBancasExaminadoras)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(135, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(14, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAssociarBancaExaminadora)
						.addComponent(btnGerenciarBancasExaminadoras)
						.addComponent(btnSair))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
		);
		
		tabelaDeBancasExaminadoras = new JTable();
		scrollPane.setViewportView(tabelaDeBancasExaminadoras);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblTtulo = new JLabel("Título:");
		
		lblTitulodotrabalho = new JLabel("");
		
		JLabel lblSubmissor = new JLabel("Submissor:");
		lblNomesubmissor = new JLabel("");
		
		JLabel lblResumo = new JLabel("Resumo:");
		
		lblCaminho = new JLabel("Localizacao");
		
		textFieldCaminho = new JTextField();
		textFieldCaminho.setEditable(false);
		textFieldCaminho.setColumns(10);
		
		lblAutores = new JLabel("Autores:");
		
		lblNomeDosAutores = new JLabel("");
		
		scrollPane_1 = new JScrollPane();
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblCaminho, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldCaminho, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblTtulo)
							.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
							.addComponent(lblTitulodotrabalho, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSubmissor, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblAutores, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addGap(46)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNomeDosAutores, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
								.addComponent(lblNomesubmissor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)))
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblResumo))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTtulo)
							.addGap(10)
							.addComponent(lblSubmissor))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTitulodotrabalho, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNomesubmissor, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAutores)
						.addComponent(lblNomeDosAutores, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblResumo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCaminho)
						.addComponent(textFieldCaminho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		textAreaResumo = new JTextArea();
		textAreaResumo.setEditable(false);
		scrollPane_1.setViewportView(textAreaResumo);
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
