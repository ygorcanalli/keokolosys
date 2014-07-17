package participacao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import cadastro.ControleGerenciarTrabalhosSubmetidos;
import transferobject.TrabalhoTO;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingGerenciarTrabalhosSubmetidos extends JFrame implements
		AbstractGUIGerenciarTrabalhosSubmetidos {
	private JList<String> list;
	List<TrabalhoTO> trabalhoTOs;
	ControleGerenciarTrabalhosSubmetidos controleGerenciarTrabalhosSubmetidos;
	DefaultListModel defaultListModel;
	public SwingGerenciarTrabalhosSubmetidos(final ControleGerenciarTrabalhosSubmetidos controleGerenciarTrabalhosSubmetidos) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controleGerenciarTrabalhosSubmetidos.debloquearGUICaller();
			}
		});
		setSize(new Dimension(600, 300));
		
		this.controleGerenciarTrabalhosSubmetidos = controleGerenciarTrabalhosSubmetidos;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel centro = new JPanel();
		getContentPane().add(centro, BorderLayout.CENTER);
		
		defaultListModel = new DefaultListModel();
		centro.setLayout(new BorderLayout(0, 0));
		list = new  JList<String>();
		centro.add(list);	
		
		JPanel sul = new JPanel();
		getContentPane().add(sul, BorderLayout.SOUTH);
		sul.setLayout(new BorderLayout(0, 0));
		
		JButton btnS = new JButton("Submeter Versão Final");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submeterVersaoFinal();
			}
		});
		sul.add(btnS, BorderLayout.EAST);
		
		JLabel lblSubmeterVersoFinal = new JLabel("Submeter Versão Final");
		lblSubmeterVersoFinal.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblSubmeterVersoFinal, BorderLayout.NORTH);
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
		controleGerenciarTrabalhosSubmetidos.submeterVersaoFinal(trabalhoTO);
	}

	@Override
	public void inicializar() {
		tornarVisivel();
		
	}

	@Override
	public void tornarVisivel() {
		setVisible(true);
		//repaint();
		
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
	public Integer exibirMensagemDeConfirmacao(String mensagem, String titulo,
			Object[] opcoes, Object opcaoPadrao) {
		// TODO Auto-generated method stub
		return null;
	}
}
