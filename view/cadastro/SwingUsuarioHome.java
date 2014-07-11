package cadastro;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import transferobject.EventoTO;
import cadastro.ControleUsuarioHome;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;


public class SwingUsuarioHome extends JFrame implements AbstractGUIUsuarioHome {
	
	ControleUsuarioHome controleUsuarioHome;
	EventoTO[] eventos;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> listaEventosDisponiveis;
	
	public SwingUsuarioHome(ControleUsuarioHome controleUsuarioHome) {
	
		this.controleUsuarioHome = controleUsuarioHome;
		
		setSize(new Dimension(800, 600));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		
		JPanel paneParticipacao = new JPanel();
		tabbedPane.addTab("Participação", null, paneParticipacao, null);
		
		JPanel paneEventosDisponiveis = new JPanel();
		tabbedPane.addTab("Eventos Disponiveis", null, paneEventosDisponiveis, null);
		
		listaEventosDisponiveis = new JList<String>();
		listaEventosDisponiveis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEventosDisponiveis.repaint();
		paneEventosDisponiveis.setLayout(new BorderLayout(0, 0));
		
		
		JScrollPane scrollPaneEventosDisponiveis = new JScrollPane();
		scrollPaneEventosDisponiveis.setViewportView(listaEventosDisponiveis);
		paneEventosDisponiveis.add(scrollPaneEventosDisponiveis);		
		
		JButton btnInscreverEvento = new JButton("Realizar inscrição");
		btnInscreverEvento.addMouseListener(mouseAdapterRealizarInscricao());
		paneEventosDisponiveis.add(btnInscreverEvento, BorderLayout.SOUTH);
			
		JPanel paneAdministracao = new JPanel();
		tabbedPane.addTab("Administração", null, paneAdministracao, null);
		
		JPanel paneExames = new JPanel();
		tabbedPane.addTab("Exames", null, paneExames, null);
		
		JPanel paneMeusEventos = new JPanel();
		tabbedPane.addTab("Meus Eventos", null, paneMeusEventos, null);
		
		JPanel panePerfil = new JPanel();
		tabbedPane.addTab("Perfil", null, panePerfil, null);
	}
	
	private MouseAdapter mouseAdapterRealizarInscricao(){
		return new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  int row = listaEventosDisponiveis.getSelectedIndex();
			      controleUsuarioHome.realizarInscricaoEmEvento(eventos[row]);		    
			  }
			};
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		this.setVisible(true);
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
	public void atualizarTabelaEventosDisponiveis(
			Collection<EventoTO> eventosDisponiveis) {
		// TODO Auto-generated method stub
		
		Iterator<EventoTO> iteradorEventosDisponiveis = eventosDisponiveis.iterator();
		
		eventos = new EventoTO[eventosDisponiveis.size()];
		DefaultListModel<String> modelEventosDisponiveis = new DefaultListModel<String>();
		for (int i = 0; i < eventos.length; i++) {
			eventos[i] = iteradorEventosDisponiveis.next();
			modelEventosDisponiveis.add(i, eventos[i].getNome());
		}
		
		
		listaEventosDisponiveis.setModel(modelEventosDisponiveis);
		listaEventosDisponiveis.setSelectedIndex(0);
		
	}

	@Override
	public void tornarVisivel() {
		// TODO Auto-generated method stub
		
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
}
