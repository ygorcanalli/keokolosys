package cadastro;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import valueobject.EventoVO;
import cadastro.ControleUsuarioHome;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;


public class SwingUsuarioHome extends JFrame implements AbstractGUIUsuarioHome {
	
	ControleUsuarioHome controleUsuarioHome;
	EventoVO[] eventos;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	public SwingUsuarioHome(ControleUsuarioHome controleUsuarioHome) {
	
		this.controleUsuarioHome = controleUsuarioHome;
		
		setSize(new Dimension(800, 600));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		
		JPanel paneParticipacao = new JPanel();
		tabbedPane.addTab("Participação", null, paneParticipacao, null);
		
		JPanel paneEventosDisponiveis = new JPanel();
		tabbedPane.addTab("Eventos Disponiveis", null, paneEventosDisponiveis, null);
		paneEventosDisponiveis.setLayout(new BorderLayout(0, 0));
		
		JTable tableEventosDisponiveis = new JTable(dataTabelaEventosDisponiveis(), columnsTabelaEventosDisponiveis());
		tableEventosDisponiveis.addMouseListener(mouseAdapterTabelaEventosDisponiveis());
		tableEventosDisponiveis.repaint();
		
		
		JScrollPane scrollPaneEventosDisponiveis = new JScrollPane();
		scrollPaneEventosDisponiveis.setViewportView(tableEventosDisponiveis);
		tableEventosDisponiveis.setLayout(new GridLayout(1, 0, 0, 0));
		paneEventosDisponiveis.add(scrollPaneEventosDisponiveis);		
			
		JPanel paneAdministracao = new JPanel();
		tabbedPane.addTab("Administração", null, paneAdministracao, null);
		
		JPanel paneExames = new JPanel();
		tabbedPane.addTab("Exames", null, paneExames, null);
		
		JPanel paneMeusEventos = new JPanel();
		tabbedPane.addTab("Meus Eventos", null, paneMeusEventos, null);
		
		JPanel panePerfil = new JPanel();
		tabbedPane.addTab("Perfil", null, panePerfil, null);
	}
	
	private MouseAdapter mouseAdapterTabelaEventosDisponiveis(){
		return new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    
			      JTable target = (JTable) e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      System.out.println(row + ", " + column);
			      
			      if (column == 1) {
			    	  controleUsuarioHome.realizarInscricaoEmEvento(eventos[row]);
			      }
			    
			  }
			};
	}
	
	private String[] columnsTabelaEventosDisponiveis() {
		String[] columns = {"Evento", ""};
		return columns;
	}
	
	private Object[][] dataTabelaEventosDisponiveis() {
		
		Collection<EventoVO> eventosDisponiveis = controleUsuarioHome.obterEventosDisponiveis();
		
		eventos = new EventoVO[eventosDisponiveis.size()];
		Object[][] data = new Object[eventos.length][2];
		for (int i = 0; i < eventos.length; i++) {
			eventos[i] = eventosDisponiveis.iterator().next();
			data[i][0] = eventos[i].getNome();
			data[i][1] = "Realizar Inscrição";
		}
		
		return data;
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		this.setVisible(true);
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
