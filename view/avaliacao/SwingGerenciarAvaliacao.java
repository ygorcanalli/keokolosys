package avaliacao;

import javax.swing.JFrame;

import transferobject.TrabalhoTO;

import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.WindowAdapter;
import java.awt.Dimension;

public class SwingGerenciarAvaliacao extends JFrame implements AbstractGUIGerenciarAvaliacao {

	
	private static final long serialVersionUID = 1119500081331747240L;
	
	JList<String> list;
	DefaultListModel<String> defaultListModel;
	ControleGerenciarAvaliacao controleGerenciarAvaliacao;

	public SwingGerenciarAvaliacao(final ControleGerenciarAvaliacao controleGerenciarAvaliacao) {
		this.controleGerenciarAvaliacao = controleGerenciarAvaliacao;
		inicializarFrame();
	}
	
	@Override
	public void inicializar() {
		inicializarFrame();
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
		this.setEnabled(false);
		
	}

	@Override
	public void desbloquear() {
		this.setEnabled(true);
		
	}
	
	private void avaliarTrabalhoSelecionado(){
		TrabalhoTO trabalhoTO = new TrabalhoTO();
		trabalhoTO.setTitulo(list.getSelectedValue());
		controleGerenciarAvaliacao.avaliarTrabalho(trabalhoTO);
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
	public void setTrabalhosNaoAvaliados(Collection<TrabalhoTO> trabalhos) {
		defaultListModel.removeAllElements();
		for (TrabalhoTO trabalhoTO : trabalhos) {
			defaultListModel.addElement(trabalhoTO.getTitulo());
		}
		repaint(10);
	}
	
	private void inicializarFrame(){
		setSize(new Dimension(800, 600));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controleGerenciarAvaliacao.encerrarGUI();
			}
		});
				
		defaultListModel = new DefaultListModel<String>();
		list = new JList<String>(defaultListModel);
		
		getContentPane().add(list, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton avaliarBtn = new JButton("Avaliar");
		avaliarBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				avaliarTrabalhoSelecionado();
			}
		});
		panel.add(avaliarBtn, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Trabalhos nao avaliados");
		panel_3.add(lblNewLabel);
		// TODO Auto-generated constructor stub
	}

}
