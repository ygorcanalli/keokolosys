package keokolosys;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class SwingUsuarioHome extends JFrame implements AbstractGUIUsuarioHome {
	
	ControleUsuarioHome controleUsuarioHome;
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
			    
			  }
			};
	}
	
	private String[] columnsTabelaEventosDisponiveis() {
		String[] columns = {"Evento", ""};
		return columns;
	}
	
	private Object[][] dataTabelaEventosDisponiveis() {
		
		ArrayList<String> eventos = new ArrayList<String>(controleUsuarioHome.obterEventosDisponiveis());
		Object[][] data = new Object[eventos.size()][2];
		for (int i = 0; i < eventos.size(); i++) {
			data[i][0] = eventos.get(i);
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
