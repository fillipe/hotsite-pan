package br.com.hotsite.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
		if (usuario != null) {
			loggedIn = true;
			return "participante/cadastroParticipante?faces-redirect=true";
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

}
