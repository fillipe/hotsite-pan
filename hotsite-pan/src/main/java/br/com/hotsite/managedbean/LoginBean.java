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
	private boolean presencaConfirmada;

	private String cpf;
	
	public String doLogin() {
		UsuarioService service = new ParticipanteServiceImpl();
		String cpfSemMask = cpf.replaceAll("[.]", "");
		cpfSemMask = cpfSemMask.replaceAll("[-]", "");
		Usuario usuario = service.login(cpfSemMask);
		if (usuario != null && !isPresencaConfirmada(usuario)) {
			loggedIn = true;
			return "/pages/participante/cadastroParticipante";
		} else if (usuario != null && isPresencaConfirmada(usuario)){
			loggedIn = true;
			presencaConfirmada = true;
			return "/pages/participante/paginaInstitucional1";
		}
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Usuário não existente");
		context.addMessage("cpfMessage", mensagem);
		return "";
	}
	
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/login";
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

	public boolean isPresencaConfirmada() {
		return presencaConfirmada;
	}

	public void setPresencaConfirmada(boolean presencaConfirmada) {
		this.presencaConfirmada = presencaConfirmada;
	}

}
