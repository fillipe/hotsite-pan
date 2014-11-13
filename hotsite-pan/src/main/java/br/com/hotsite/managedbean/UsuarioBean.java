package br.com.hotsite.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.hotsite.modelo.Usuario;
import br.com.hotsite.service.EmailService;
import br.com.hotsite.service.EmailServiceImpl;
import br.com.hotsite.service.ParticipanteServiceImpl;
import br.com.hotsite.service.UsuarioService;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	
	public UsuarioBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		LoginBean loginBean = (LoginBean) session.getAttribute("login");
		usuario.setCpf(loginBean.getCpf());
	}
	
	
	public String confirmaCadastro() {
		return "confirmaCadastro";
	}
	
	public String voltar() {
		return "cadastroParticipante";
	}
	
	public String salvar() {
		UsuarioService usuarioService = new ParticipanteServiceImpl();
		usuario.setPresencaConfirmada(true);
		usuarioService.salva(usuario);
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("usuarioBean", null);
		
		EmailService emailService = new EmailServiceImpl();
//		emailService.enviaEmailConfirmacaoCadastroParticipante(usuario.getEmail().trim());
		
		return "paginaInstitucional1";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
}
