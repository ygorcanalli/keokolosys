package participacao;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import transferobject.EventoTO;
import transferobject.UsuarioTO;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.ListSelectionModel;

import java.awt.Dimension;

public class SwingConcederPrivilegios extends JFrame implements
		AbstractGUIConcederPrivilegios {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleConcederPrivilegios controleConcederPrivilegios;
	private EventoTO eventoTO;
	private UsuarioTO[] usuariosArray;
	private JList<String> listaChairs;
	private JList<String> listaExaminadores;
	private JList<String> listaUsuarios;
	/**
	 * @wbp.nonvisual location=23,-27
	 */

	public SwingConcederPrivilegios(ControleConcederPrivilegios controleConcederPrivilegios, EventoTO eventoTO) {
		this.controleConcederPrivilegios = controleConcederPrivilegios;
		this.eventoTO = eventoTO;

	}
	
	@Override
	public void inicializar() {
		inicializarFrame();
	}

	private void inicializarFrame() {
		setSize(new Dimension(756, 654));
		setResizable(false);
		setTitle("Conceder privilégios");
		
		JPanel paneConcederPrivilegios = new JPanel();
		getContentPane().add(paneConcederPrivilegios, BorderLayout.CENTER);
		
		JScrollPane scrollPaneUsuarios = new JScrollPane();
		scrollPaneUsuarios.setBounds(12, 52, 342, 484);
		
		JScrollPane scrollPaneExaminadores = new JScrollPane();
		scrollPaneExaminadores.setBounds(366, 52, 376, 268);
		
		listaExaminadores = new JList<String>();
		listaExaminadores.setEnabled(false);
		scrollPaneExaminadores.setViewportView(listaExaminadores);
		
		JLabel lblExaminadores = new JLabel("Examinadores");
		scrollPaneExaminadores.setColumnHeaderView(lblExaminadores);
		paneConcederPrivilegios.setLayout(null);
		
		listaUsuarios = new JList<String>();
		listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaUsuarios.setSelectedIndex(1);
		scrollPaneUsuarios.setViewportView(listaUsuarios);
		
		JLabel lblUsuarios = new JLabel("Usuários");
		scrollPaneUsuarios.setColumnHeaderView(lblUsuarios);
		paneConcederPrivilegios.add(scrollPaneUsuarios);
		paneConcederPrivilegios.add(scrollPaneExaminadores);
		
		JScrollPane scrollPaneChairs = new JScrollPane();
		scrollPaneChairs.setBounds(366, 332, 376, 272);
		
		listaChairs = new JList<String>();
		listaChairs.setEnabled(false);
		scrollPaneChairs.setViewportView(listaChairs);
		
		JLabel lblChairs = new JLabel("Chairs");
		scrollPaneChairs.setColumnHeaderView(lblChairs);
		paneConcederPrivilegios.add(scrollPaneChairs);
		
		JButton btnConcederPrivilgioDeExaminador = new JButton("Conceder privilégio de Examinador");
		btnConcederPrivilgioDeExaminador.setBounds(42, 548, 291, 25);
		btnConcederPrivilgioDeExaminador.addMouseListener(mouseAdapterConcederPrivilegioDeExaminado);
		paneConcederPrivilegios.add(btnConcederPrivilgioDeExaminador);
		
		JButton btnConcederPrivilegioDeChair = new JButton("Conceder privilégio de Chair");
		btnConcederPrivilegioDeChair.addMouseListener(mouseAdapterConcederPrivilegioDeChair);
		btnConcederPrivilegioDeChair.setBounds(42, 579, 291, 25);
		paneConcederPrivilegios.add(btnConcederPrivilegioDeChair);
		
		JLabel lblNomeEvento = new JLabel(eventoTO.getNome());
		lblNomeEvento.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNomeEvento.setBounds(26, 19, 493, 15);
		paneConcederPrivilegios.add(lblNomeEvento);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addMouseListener(mouseAdapterFechar);
		btnFinalizar.setBounds(616, 12, 126, 28);
		paneConcederPrivilegios.add(btnFinalizar);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				fechar();
			}
		});
		
	}
	
	private MouseAdapter mouseAdapterConcederPrivilegioDeChair = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaUsuarios.getSelectedIndex();
		      controleConcederPrivilegios.acaoConcederPrivilegioDeChair(usuariosArray[row]);		    
		  }
	};
	
	private MouseAdapter mouseAdapterConcederPrivilegioDeExaminado = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  int row = listaUsuarios.getSelectedIndex();
		      controleConcederPrivilegios.acaoConcederPrivilegioDeExaminador(usuariosArray[row]);		    
		  }
	};
	
	private MouseAdapter mouseAdapterFechar = new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
			  fechar();	    
		  }
	};
	
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
	public void atualizarListaDeUsuariosSemParticipacaoNoEvento(
			Collection<UsuarioTO> usuariosTO) {
		Iterator<UsuarioTO> iteradorUsuarios = usuariosTO.iterator();
		
		usuariosArray = new UsuarioTO[usuariosTO.size()];
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < usuariosArray.length; i++) {
			usuariosArray[i] = iteradorUsuarios.next();
			listModel.add(i, usuariosArray[i].getNome() + " <" + usuariosArray[i].getEmail() + ">");
		}
		
		listaUsuarios.setModel(listModel);
		listaUsuarios.setSelectedIndex(0);

	}

	@Override
	public void atualizarListaDeExaminadores(Collection<UsuarioTO> usuariosTO) {
		Iterator<UsuarioTO> iteradorUsuarios = usuariosTO.iterator();
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < usuariosTO.size(); i++) {
			UsuarioTO usuario = iteradorUsuarios.next();
			listModel.add(i, usuario.getNome() + " <" + usuario.getEmail() + ">");
		}
		
		listaExaminadores.setModel(listModel);
	}
	

	@Override
	public void atualizarListaDeChairs(Collection<UsuarioTO> usuariosTO) {
		Iterator<UsuarioTO> iteradorUsuarios = usuariosTO.iterator();
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < usuariosTO.size(); i++) {
			UsuarioTO usuario = iteradorUsuarios.next();
			listModel.add(i, usuario.getNome() + " <" + usuario.getEmail() + ">");
		}
		
		listaChairs.setModel(listModel);

	}
	
	private void fechar() {
		controleConcederPrivilegios.acaoFechar();
	}
}
