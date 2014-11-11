package br.com.hotsite.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.hotsite.modelo.Usuario;
import br.com.hotsite.service.ParticipanteServiceImpl;
import br.com.hotsite.service.UsuarioService;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {
	
	private boolean loggedIn;

	private String cpf;
	
	public String doLogin() {
		UsuarioService service = new ParticipanteServiceImpl();
		cpf = cpf.replaceAll("[.]", "");
		cpf = cpf.replaceAll("[-]", "");
		Usuario usuario = service.login(cpf);
		if (usuario != null && !isPresencaConfirmada(usuario)) {
			loggedIn = true;
			return "participante/cadastroParticipante?faces-redirect=true";
		} else if (usuario != null && isPresencaConfirmada(usuario)){
			loggedIn = true;
			return "participante/paginaInstitucional1?faces-redirect=true";
		}
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Usuário não existente");
		context.addMessage("cpfMessage", mensagem);
		return "";
	}
	
	public String doLogout() {
		loggedIn = false;
		return "/pages/login?faces-redirect=true";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}
	

	private boolean isPresencaConfirmada(Usuario usuario) {
		if (usuario.getPresencaConfirmada() == null) {
			return false;
		} else {
			return usuario.getPresencaConfirmada();
		}
	}

}
