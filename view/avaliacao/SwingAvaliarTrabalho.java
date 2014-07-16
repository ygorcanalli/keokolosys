package avaliacao;

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

import dominio.EstadoAvaliacao;
import excecao.ExcecaoDeAvaliacao;
import transferobject.AvaliacaoTO;
import avaliacao.ControleAvaliarTrabalho;

import java.awt.GridLayout;
import java.util.Collection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingAvaliarTrabalho extends JFrame implements AbstractGUIAvaliarTrabalho{

	private JPanel contentPane;

	private ControleAvaliarTrabalho controleAvaliarTrabalho;
	private JTable tableAvaliacoesRecebidas;
	private DefaultTableModel defaultTableModel = new DefaultTableModel(); 
	private JLabel valorTitulo;
	private JLabel valorResumoTrabalho;
	private JLabel valorNomeArquivo;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public SwingAvaliarTrabalho(ControleAvaliarTrabalho controleAvaliarTrabalho){
		
		this.controleAvaliarTrabalho = controleAvaliarTrabalho;
		
		setResizable(false);
		setTitle("Avaliar trabalho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 443);
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
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		JButton btnAvaliarTrabalho = new JButton("Avaliar trabalho");
		btnAvaliarTrabalho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					avaliarTrabalho();
				} catch (ExcecaoDeAvaliacao e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAvaliarTrabalho.setMnemonic('A');
		
		JButton btnSair = new JButton("Sair");
		btnSair.setMnemonic('S');
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(6)
					.addComponent(btnAvaliarTrabalho)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(298, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSair)
						.addComponent(btnAvaliarTrabalho))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblExaminador = new JLabel("Examinador:");
		
		JLabel lblNomeexaminador = new JLabel("nome_examinador");
		
		JLabel lblResultadoAvaliao = new JLabel("Resultado avalia\u00E7\u00E3o:");
		
		EstadoAvaliacao[] estados = {EstadoAvaliacao.ACEITO,EstadoAvaliacao.REJEITADO};
		
		comboBox = new JComboBox(estados);
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
		defaultTableModel = new DefaultTableModel(new Object[][] {},new String[] {"Nome examinador", "Avalia\u00E7\u00E3o recebida"});
		tableAvaliacoesRecebidas = new JTable(defaultTableModel);		
		
		tableAvaliacoesRecebidas.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableAvaliacoesRecebidas.getColumnModel().getColumn(1).setPreferredWidth(310);
		scrollPane.setViewportView(tableAvaliacoesRecebidas);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(new GridLayout(3, 3, 2, 0));
		
		JLabel lblTtuloDoTrabalho = new JLabel("T\u00EDtulo do trabalho:");
		panel.add(lblTtuloDoTrabalho);
		
		valorTitulo = new JLabel("");
		panel.add(valorTitulo);
		
		JLabel lblResumoTrabalho = new JLabel("Resumo do Trabalho");
		panel.add(lblResumoTrabalho);
		
		valorResumoTrabalho = new JLabel("");
		panel.add(valorResumoTrabalho);
		
		JLabel lblNomeArquivo = new JLabel("Nome do arquivo");
		panel.add(lblNomeArquivo);
		
		valorNomeArquivo = new JLabel("");
		panel.add(valorNomeArquivo);
		contentPane.setLayout(gl_contentPane);
		
		
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tornarVisivel() {
		setVisible(true);
		repaint();
	}

	@Override
	public void tornarInvisivel() {
		// TODO Auto-generated method stub
		
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
	public Integer exibirMensagemDeConfirmacao(String mensagem, String titulo,Object[] opcoes, Object opcaoPadrao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTituloTrabalho(String titulo) {
		valorTitulo.setText(titulo);		
	}

	@Override
	public void setResumoTrabalho(String resumo) {
		valorResumoTrabalho.setText(resumo);
	}

	@Override
	public void setNomeArquivo(String nomeArquivo) {
		valorNomeArquivo.setText(nomeArquivo);
	}

	@Override
	public void setAvaliacoes(Collection<AvaliacaoTO> nomeArquivo) {
		for (AvaliacaoTO avaliacaoTO : nomeArquivo) 
		{
			String vetor[] = new String[2];
			vetor[0] = avaliacaoTO.getNomeExaminador();
			vetor[1] = avaliacaoTO.getEnumEstadoTrabalho().getValor();
			defaultTableModel.addRow(vetor);
		}		
	}

	@Override
	public void avaliarTrabalho() throws ExcecaoDeAvaliacao {
		AvaliacaoTO avaliacaoTO = new AvaliacaoTO();
		avaliacaoTO.setEstadoAvaliacao((EstadoAvaliacao) comboBox.getSelectedItem());
		controleAvaliarTrabalho.avaliarTrabalho(avaliacaoTO);
		
	}

	
}