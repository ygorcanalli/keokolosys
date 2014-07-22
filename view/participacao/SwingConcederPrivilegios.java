package participacao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import transferobject.EventoTO;
import transferobject.UsuarioTO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class SwingConcederPrivilegios extends JFrame implements
		AbstractGUIConcederPrivilegios {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControleConcederPrivilegios controleConcederPrivilegios;
	private EventoTO eventoTO;
	/**
	 * @wbp.nonvisual location=23,-27
	 */

	public SwingConcederPrivilegios(ControleConcederPrivilegios controleConcederPrivilegios, EventoTO eventoTO) {
		setTitle("Conceder privilégios");
		this.controleConcederPrivilegios = controleConcederPrivilegios;
		this.eventoTO = eventoTO;
		
		
		JPanel paneConcederPrivilegios = new JPanel();
		getContentPane().add(paneConcederPrivilegios, BorderLayout.CENTER);
		
		JScrollPane scrollPaneUsuarios = new JScrollPane();
		GroupLayout gl_paneConcederPrivilegios = new GroupLayout(paneConcederPrivilegios);
		gl_paneConcederPrivilegios.setHorizontalGroup(
			gl_paneConcederPrivilegios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneConcederPrivilegios.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneUsuarios, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(463, Short.MAX_VALUE))
		);
		gl_paneConcederPrivilegios.setVerticalGroup(
			gl_paneConcederPrivilegios.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_paneConcederPrivilegios.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneUsuarios, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JList<String> listaUsuarios = new JList<String>();
		scrollPaneUsuarios.setViewportView(listaUsuarios);
		
		JLabel lblUsuarios = new JLabel("Usuários");
		scrollPaneUsuarios.setColumnHeaderView(lblUsuarios);
		paneConcederPrivilegios.setLayout(gl_paneConcederPrivilegios);
	}
	
	@Override
	public void inicializar() {
		inicializarFrame();
	}

	private void inicializarFrame() {
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				fechar();
			}
		});
		
	}
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

	}

	@Override
	public void atualizarListaDeExaminadores(Collection<UsuarioTO> usuariosTO) {

	}
	
	

	@Override
	public void atualizarListaDeChairs(Collection<UsuarioTO> usuariosTO) {


	}
	
	private void fechar() {
		controleConcederPrivilegios.acaoFechar();
	}
}
