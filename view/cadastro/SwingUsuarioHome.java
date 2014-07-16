package cadastro;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import transferobject.EventoTO;
import cadastro.ControleUsuarioHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import excecao.ExcecaoDeParticipacao;



public class SwingUsuarioHome extends JFrame implements AbstractGUIUsuarioHome {
	
	ControleUsuarioHome controleUsuarioHome;
	EventoTO[] eventosArray;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> listaEventosDisponiveis;
	private JList<String> listaParticipacao;
	JTabbedPane tabbedPane;
	
	private static final int ABA_EVENTOS_DISPONIVEIS = 0;
	private static final int ABA_PARTICIPACAO = 1;
	/*private static final Integer ABA_EVENTOS_DISPONIVEIS = 0;
	private static final Integer ABA_EVENTOS_DISPONIVEIS = 0;
	private static final Integer ABA_EVENTOS_DISPONIVEIS = 0;*/
	
	
	public SwingUsuarioHome(ControleUsuarioHome controleUsuarioHome) {
	
		this.controleUsuarioHome = controleUsuarioHome;
		inicializarFrame();
	}
	
	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		inicializarFrame();
		controleUsuarioHome.exibirEventosDisponiveis();
	}
	
	private void inicializarFrame() {
		setSize(new Dimension(800, 600));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel paneEventosDisponiveis = new JPanel();
		tabbedPane.addTab("Eventos Disponiveis", null, paneEventosDisponiveis, null);
		
		listaEventosDisponiveis = new JList<String>();
		listaEventosDisponiveis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEventosDisponiveis.repaint();
		
		
		JScrollPane scrollPaneEventosDisponiveis = new JScrollPane();
		scrollPaneEventosDisponiveis.setViewportView(listaEventosDisponiveis);
		
		JButton btnInscreverEvento = new JButton("Realizar inscrição");
		btnInscreverEvento.addMouseListener(mouseAdapterRealizarInscricao);
		GroupLayout gl_paneEventosDisponiveis = new GroupLayout(paneEventosDisponiveis);
		gl_paneEventosDisponiveis.setHorizontalGroup(
			gl_paneEventosDisponiveis.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneEventosDisponiveis.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_paneEventosDisponiveis.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneEventosDisponiveis, GroupLayout.PREFERRED_SIZE, 723, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInscreverEvento))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_paneEventosDisponiveis.setVerticalGroup(
			gl_paneEventosDisponiveis.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneEventosDisponiveis.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPaneEventosDisponiveis, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnInscreverEvento)
					.addGap(24))
		);
		paneEventosDisponiveis.setLayout(gl_paneEventosDisponiveis);
		
		JPanel paneParticipacao = new JPanel();
		tabbedPane.addTab("Participação", null, paneParticipacao, null);
		
		JScrollPane scrollPaneParticipacao = new JScrollPane();
		
		JButton btnSubmeterTrabalho = new JButton("Submeter trabalho");
		btnSubmeterTrabalho.addMouseListener(mouseAdapterSubmeterTrabalho);
		
		JButton btnGerenciarTrabalhosSubmetidos = new JButton("Gerenciar trabalhos submetidos");
		btnGerenciarTrabalhosSubmetidos.addMouseListener(mouseAdapterGerenciarTrabalhoSubmetidos);

		GroupLayout gl_paneParticipacao = new GroupLayout(paneParticipacao);
		gl_paneParticipacao.setHorizontalGroup(
			gl_paneParticipacao.createParallelGroup(Alignment.LEADING)
				.addGap(0, 793, Short.MAX_VALUE)
				.addGroup(gl_paneParticipacao.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_paneParticipacao.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneParticipacao, GroupLayout.PREFERRED_SIZE, 723, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_paneParticipacao.createSequentialGroup()
							.addComponent(btnGerenciarTrabalhosSubmetidos, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSubmeterTrabalho)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_paneParticipacao.setVerticalGroup(
			gl_paneParticipacao.createParallelGroup(Alignment.LEADING)
				.addGap(0, 544, Short.MAX_VALUE)
				.addGroup(gl_paneParticipacao.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPaneParticipacao, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_paneParticipacao.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmeterTrabalho)
						.addComponent(btnGerenciarTrabalhosSubmetidos))
					.addGap(24))
		);
		
		listaParticipacao = new JList<String>();
		listaParticipacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneParticipacao.setViewportView(listaParticipacao);
		paneParticipacao.setLayout(gl_paneParticipacao);
			
		JPanel paneAdministracao = new JPanel();
		tabbedPane.addTab("Administração", null, paneAdministracao, null);
		
		JPanel paneExames = new JPanel();
		tabbedPane.addTab("Exames", null, paneExames, null);
		
		JPanel paneMeusEventos = new JPanel();
		tabbedPane.addTab("Meus Eventos", null, paneMeusEventos, null);
		
		JPanel panePerfil = new JPanel();
		tabbedPane.addTab("Perfil", null, panePerfil, null);
		
		tabbedPane.addChangeListener(changeListenerAbas);
	}
	
	private ChangeListener changeListenerAbas = new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			switch (tabbedPane.getSelectedIndex()) {
				case ABA_EVENTOS_DISPONIVEIS: 
					controleUsuarioHome.exibirEventosDisponiveis();
					break;
				case ABA_PARTICIPACAO:
					controleUsuarioHome.exibirParticipacao();
					break;
			}
		}
	};
	
	private MouseAdapter mouseAdapterRealizarInscricao = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaEventosDisponiveis.getSelectedIndex();
		      controleUsuarioHome.realizarInscricaoEmEvento(eventosArray[row]);		    
		  }
	};
	
	
	private MouseAdapter mouseAdapterSubmeterTrabalho = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaParticipacao.getSelectedIndex();
		      controleUsuarioHome.submeterTrabalhos(eventosArray[row]);		    
		  }
	};
	
	private MouseAdapter mouseAdapterGerenciarTrabalhoSubmetidos = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaParticipacao.getSelectedIndex();
			  try {
				controleUsuarioHome.gerenciarTrabalhosSubmetidos(eventosArray[row]);
			} catch (ExcecaoDeParticipacao e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	    
		  }
	};

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

	
	private void atualizarListaEventos(JList<String> jlist, Collection<EventoTO> eventos) {
		Iterator<EventoTO> iteradorEventos = eventos.iterator();
		
		eventosArray = new EventoTO[eventos.size()];
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < eventosArray.length; i++) {
			eventosArray[i] = iteradorEventos.next();
			listModel.add(i, eventosArray[i].getNome());
		}
		
		jlist.setModel(listModel);
		jlist.setSelectedIndex(0);
	}
	
	@Override
	public void atualizarListaEventosDisponiveis(
			Collection<EventoTO> eventosDisponiveis) {
		// TODO Auto-generated method stub
		atualizarListaEventos(listaEventosDisponiveis, eventosDisponiveis);
		
	}
	
	@Override
	public void atualizarListaParticipacao(
			Collection<EventoTO> eventosInscritos) {
		// TODO Auto-generated method stub
		atualizarListaEventos(listaParticipacao, eventosInscritos);
	}

	@Override
	public void tornarVisivel() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

	@Override
	public void tornarInvisivel() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

	@Override
	public void bloquear() {
		// TODO Auto-generated method stub
		this.setEnabled(false);
	}

	@Override
	public void desbloquear() {
		// TODO Auto-generated method stub
		this.setEnabled(true);
		
	}
}
