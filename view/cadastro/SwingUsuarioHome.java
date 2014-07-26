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
import transferobject.UsuarioTO;
import cadastro.ControleUsuarioHome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import excecao.ExcecaoDeParticipacao;

import javax.swing.JTable;



public class SwingUsuarioHome extends JFrame implements AbstractGUIUsuarioHome {
	
	ControleUsuarioHome controleUsuarioHome;
	EventoTO[] eventosArray;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> listaEventosDisponiveis;
	private JList<String> listaParticipacao;
	private JList<String> listaAdministracao;
	private JList<String> listaExames;
	private JTable tabelMeusEventos;

	JTabbedPane tabbedPane;
	
	private static final int ABA_EVENTOS_DISPONIVEIS = 0;
	private static final int ABA_PARTICIPACAO = 1;
	private static final int ABA_EXAMES = 2;
	private static final int ABA_ADMINISTRACAO = 3;
	private static final int ABA_MEUS_EVENTOS = 4;
	private static final int ABA_PERFIL = 5;
	
	
	public SwingUsuarioHome(ControleUsuarioHome controleUsuarioHome) {
		setResizable(false);
	
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
		
		JPanel paneExames = new JPanel();
		tabbedPane.addTab("Exames", null, paneExames, null);
		
		JScrollPane scrollPaneExames = new JScrollPane();
		
		JButton btnRealizarAvaliacoes = new JButton("Realizar avaliações");
		btnRealizarAvaliacoes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EventoTO eventoTO = new EventoTO();
				eventoTO.setNome(listaExames.getSelectedValue());
				controleUsuarioHome.acaoRealizarAvliacoes(eventoTO);
			}
		});
		
		GroupLayout gl_paneExames = new GroupLayout(paneExames);
		gl_paneExames.setHorizontalGroup(
			gl_paneExames.createParallelGroup(Alignment.LEADING)
				.addGap(0, 793, Short.MAX_VALUE)
				.addGroup(gl_paneExames.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_paneExames.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneExames, GroupLayout.PREFERRED_SIZE, 723, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRealizarAvaliacoes))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_paneExames.setVerticalGroup(
			gl_paneExames.createParallelGroup(Alignment.LEADING)
				.addGap(0, 533, Short.MAX_VALUE)
				.addGroup(gl_paneExames.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPaneExames, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRealizarAvaliacoes)
					.addGap(24))
		);
		
		listaExames = new JList<String> ();
		scrollPaneExames.setViewportView(listaExames);
		paneExames.setLayout(gl_paneExames);
		
		JPanel paneAdministracao = new JPanel();
		tabbedPane.addTab("Administração", null, paneAdministracao, null);
		
		JScrollPane scrollPaneAdministracao = new JScrollPane();
		
		JButton btnGerenciarBancasExaminadoras = new JButton("Gerenciar bancas examinadoras");
		btnGerenciarBancasExaminadoras.addMouseListener(mouseAdapterGerenciarBancasExaminadoras);
		
		JButton btnConcederPrivilegios = new JButton("Conceder privilégios");
		btnConcederPrivilegios.addMouseListener(mouseAdapterConcederPrivilegios);
		
		GroupLayout gl_paneAdministracao = new GroupLayout(paneAdministracao);
		gl_paneAdministracao.setHorizontalGroup(
			gl_paneAdministracao.createParallelGroup(Alignment.LEADING)
				.addGap(0, 793, Short.MAX_VALUE)
				.addGroup(gl_paneAdministracao.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_paneAdministracao.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneAdministracao, GroupLayout.PREFERRED_SIZE, 723, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_paneAdministracao.createSequentialGroup()
							.addComponent(btnGerenciarBancasExaminadoras, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnConcederPrivilegios)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_paneAdministracao.setVerticalGroup(
			gl_paneAdministracao.createParallelGroup(Alignment.LEADING)
				.addGap(0, 533, Short.MAX_VALUE)
				.addGroup(gl_paneAdministracao.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPaneAdministracao, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_paneAdministracao.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConcederPrivilegios)
						.addComponent(btnGerenciarBancasExaminadoras))
					.addGap(24))
		);
		
		listaAdministracao = new JList<String>();
		listaAdministracao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneAdministracao.setViewportView(listaAdministracao);
		paneAdministracao.setLayout(gl_paneAdministracao);
		
		JPanel paneMeusEventos = new JPanel();
		tabbedPane.addTab("Meus Eventos", null, paneMeusEventos, null);
		
		JScrollPane scrollPaneMeusEventos = new JScrollPane();
		
		JButton btnCriarEvento = new JButton("Criar evento");
		btnCriarEvento.addMouseListener(mouseAdapterCriarEvento);
		GroupLayout gl_paneMeusEventos = new GroupLayout(paneMeusEventos);
		gl_paneMeusEventos.setHorizontalGroup(
			gl_paneMeusEventos.createParallelGroup(Alignment.LEADING)
				.addGap(0, 793, Short.MAX_VALUE)
				.addGroup(gl_paneMeusEventos.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_paneMeusEventos.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneMeusEventos, GroupLayout.PREFERRED_SIZE, 723, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCriarEvento))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_paneMeusEventos.setVerticalGroup(
			gl_paneMeusEventos.createParallelGroup(Alignment.LEADING)
				.addGap(0, 533, Short.MAX_VALUE)
				.addGroup(gl_paneMeusEventos.createSequentialGroup()
					.addGap(29)
					.addComponent(scrollPaneMeusEventos, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnCriarEvento)
					.addGap(24))
		);
		
		tabelMeusEventos = new JTable();
		tabelMeusEventos.setRowSelectionAllowed(false);
		scrollPaneMeusEventos.setViewportView(tabelMeusEventos);
		paneMeusEventos.setLayout(gl_paneMeusEventos);
		
		JPanel panePerfil = new JPanel();
		tabbedPane.addTab("Perfil", null, panePerfil, null);
		
		JButton btnDeslogar = new JButton("Deslogar");
		btnDeslogar.addMouseListener(mouseAdapterDeslogar);
		GroupLayout gl_panePerfil = new GroupLayout(panePerfil);
		gl_panePerfil.setHorizontalGroup(
			gl_panePerfil.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panePerfil.createSequentialGroup()
					.addContainerGap(664, Short.MAX_VALUE)
					.addComponent(btnDeslogar)
					.addContainerGap())
		);
		gl_panePerfil.setVerticalGroup(
			gl_panePerfil.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panePerfil.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDeslogar)
					.addContainerGap(496, Short.MAX_VALUE))
		);
		panePerfil.setLayout(gl_panePerfil);
		
		tabbedPane.addChangeListener(changeListenerAbas);
	}
	
	private ChangeListener changeListenerAbas = new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			atualizarExibicaoSelecionada();

		}
	};
	
	@Override
	public void atualizarExibicaoSelecionada() {
		switch (tabbedPane.getSelectedIndex()) {
		case ABA_EVENTOS_DISPONIVEIS: 
			controleUsuarioHome.exibirEventosDisponiveis();
			break;
		case ABA_PARTICIPACAO:
			controleUsuarioHome.exibirParticipacao();
			break;
		case ABA_EXAMES:
			controleUsuarioHome.exibirExames();
			break;
		case ABA_ADMINISTRACAO:
			controleUsuarioHome.exibirAdministracao();
			break;
		case ABA_MEUS_EVENTOS:
			controleUsuarioHome.exibirMeusEventos();
			break;
		case ABA_PERFIL:
			controleUsuarioHome.exibirPerfil();
			break;
		}
	}
	
	private MouseAdapter mouseAdapterRealizarInscricao = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaEventosDisponiveis.getSelectedIndex();
		      controleUsuarioHome.acaoRealizarInscricaoEmEvento(eventosArray[row]);		    
		  }
	};
	
	
	private MouseAdapter mouseAdapterSubmeterTrabalho = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaParticipacao.getSelectedIndex();
		      controleUsuarioHome.acaoSubmeterTrabalhos(eventosArray[row]);		    
		  }
	};
	
	private MouseAdapter mouseAdapterGerenciarTrabalhoSubmetidos = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaParticipacao.getSelectedIndex();
			  controleUsuarioHome.acaoGerenciarTrabalhosSubmetidos(eventosArray[row]);
			    
		  }
	};
	
	private MouseAdapter mouseAdapterGerenciarBancasExaminadoras = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaAdministracao.getSelectedIndex();
			  controleUsuarioHome.acaoGerenciarBancasExaminadoras(eventosArray[row]);
				    
		  }
	};
	
	private MouseAdapter mouseAdapterConcederPrivilegios = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaAdministracao.getSelectedIndex();
			  controleUsuarioHome.acaoConcederPrivilegios(eventosArray[row]);
			    
		  }
	};
	
	private MouseAdapter mouseAdapterCriarEvento = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  controleUsuarioHome.acaoCriarEvento();    
		  }
	};
	
	private MouseAdapter mouseAdapterDeslogar = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
		      controleUsuarioHome.acaoDeslogar();	    
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
		atualizarListaEventos(listaEventosDisponiveis, eventosDisponiveis);
		
	}
	@Override
	public void atualizarListaParticipacao(
			Collection<EventoTO> eventosInscritos) {
		atualizarListaEventos(listaParticipacao, eventosInscritos);
	}
	
	@Override
	public void atualizarListaAdministracao(
			Collection<EventoTO> eventosComPerfilDeChair) {
		atualizarListaEventos(listaAdministracao, eventosComPerfilDeChair);
	}
	
	@Override
	public void atualizarListaExames(
			Collection<EventoTO> eventosComPerfilDeExaminador) {
		atualizarListaEventos(listaExames, eventosComPerfilDeExaminador);
	}

	@Override
	public void atualizarListaMeusEventos(Collection<EventoTO> meusEventos) {
		atualizarTabelaMeusEventos(meusEventos);
	}
	
	private void atualizarTabelaMeusEventos(Collection<EventoTO> meusEventos) {
		Iterator<EventoTO> iteradorMeusEventos = meusEventos.iterator();
		
		String[] colunas = {"Evento","Instituicao","Estado"};
		String[][] dados = new String[meusEventos.size()][3];
		for (int i = 0; i < meusEventos.size(); i++) {
			EventoTO evento = iteradorMeusEventos.next();
			dados[i][0] = evento.getNome();
			dados[i][1] = evento.getInstituicao().getSigla() + " - " + evento.getInstituicao().getNome();
			dados[i][2] = evento.getEstado().toString();
		}
		
		tabelMeusEventos.setModel(new DefaultTableModel(dados,colunas));
		
	}

	@Override
	public void atualizarPerfil(UsuarioTO suarioLogado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tornarVisivel() {
		this.setVisible(true);
	}

	@Override
	public void tornarInvisivel() {
		this.setVisible(false);
	}

	@Override
	public void bloquear() {
		this.setEnabled(false);
	}

	@Override
	public void desbloquear() {
		this.setEnabled(true);
		
	}
}
