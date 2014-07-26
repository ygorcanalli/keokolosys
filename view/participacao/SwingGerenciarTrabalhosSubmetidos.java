package participacao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cadastro.ControleGerenciarTrabalhosSubmetidos;
import transferobject.TrabalhoTO;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingGerenciarTrabalhosSubmetidos extends JFrame implements AbstractGUIGerenciarTrabalhosSubmetidos {
	
	private static final long serialVersionUID = -2518632856969342839L;
	
	private JList<String> list;
	List<TrabalhoTO> trabalhoTOs;
	ControleGerenciarTrabalhosSubmetidos controleGerenciarTrabalhosSubmetidos;
	DefaultListModel<String> defaultListModel;
	
	
	public SwingGerenciarTrabalhosSubmetidos(ControleGerenciarTrabalhosSubmetidos controleGerenciarTrabalhosSubmetidos) {
		this.controleGerenciarTrabalhosSubmetidos = controleGerenciarTrabalhosSubmetidos;
		inicializarFrame();
	}
	
	public void setTrabalhos(List<TrabalhoTO> trabalhoTOs) {
		this.trabalhoTOs = trabalhoTOs;
		
		
		for (TrabalhoTO trabalhoTO : trabalhoTOs) {			
			defaultListModel.addElement(trabalhoTO.getTitulo());
		}
		list.setModel(defaultListModel);
		list.repaint();		
	}
	
	private void submeterVersaoFinal() {
		TrabalhoTO trabalhoTO = trabalhoTOs.get(list.getSelectedIndex());
		trabalhoTO.setPosicao(list.getSelectedIndex());
		controleGerenciarTrabalhosSubmetidos.submeterVersaoFinal(trabalhoTO);
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
		setEnabled(false);
	}

	@Override
	public void desbloquear() {
		setEnabled(true);
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
	
	private void inicializarFrame(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controleGerenciarTrabalhosSubmetidos.debloquearGUICaller();
			}
		});
		setSize(new Dimension(600, 300));
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel centro = new JPanel();
		getContentPane().add(centro, BorderLayout.CENTER);
		
		defaultListModel = new DefaultListModel<String>();
		centro.setLayout(new BorderLayout(0, 0));
		list = new  JList<String>();
		centro.add(list);	
		
		JPanel sul = new JPanel();
		getContentPane().add(sul, BorderLayout.SOUTH);
		sul.setLayout(new BorderLayout(0, 0));
		
		JButton btnS = new JButton("Submeter Versao Final");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submeterVersaoFinal();
			}
		});
		sul.add(btnS, BorderLayout.EAST);
		
		JLabel lblSubmeterVersoFinal = new JLabel("Submeter Versao Final");
		lblSubmeterVersoFinal.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblSubmeterVersoFinal, BorderLayout.NORTH);
	}
	
}
