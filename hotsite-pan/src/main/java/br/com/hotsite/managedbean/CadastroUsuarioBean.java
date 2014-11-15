package br.com.hotsite.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.hotsite.dao.GostoMusicalDao;
import br.com.hotsite.modelo.GostoMusical;
import br.com.hotsite.modelo.Usuario;
import br.com.hotsite.service.EmailService;
import br.com.hotsite.service.EmailServiceImpl;
import br.com.hotsite.service.ParticipanteServiceImpl;
import br.com.hotsite.service.UsuarioService;

@ManagedBean(name = "cadastroUsuario")
@SessionScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	private List<GostoMusical> gostosMusicais;
	private List<String> generos;
	private String[] selectedGeneros;
	
	
	public CadastroUsuarioBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		LoginBean loginBean = (LoginBean) session.getAttribute("login");
		usuario.setCpf(loginBean.getCpf());
		
		GostoMusicalDao gostosDao = new GostoMusicalDao();
		gostosMusicais = gostosDao.getAllGostosMusicais();
		setGeneros(new ArrayList<String>());
		for (GostoMusical gosto : gostosMusicais) {
			getGeneros().add(gosto.getGenero());
		}
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
		if (selectedGeneros != null && selectedGeneros.length > 0) {
			usuario.setGostosMusicais(obterGostosMusicaisGerenciados(Arrays.asList(selectedGeneros)));
		}
		usuarioService.salva(usuario);
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("cadastroUsuario", null);
		
		EmailService emailService = new EmailServiceImpl();
//		emailService.enviaEmailConfirmacaoCadastroParticipante(usuario.getEmail().trim());
		
		return "paginaInstitucional1";
	}
	
	private List<GostoMusical> obterGostosMusicaisGerenciados(List<String> generosSelecionados) {
		List<GostoMusical> gostosRetorno = new ArrayList<GostoMusical>();
		for (String genero : generosSelecionados) {
			for (GostoMusical gosto : gostosMusicais) {
				if (genero.equals(gosto.getGenero())) {
					gostosRetorno.add(gosto);
				}
			}
		}
		return gostosRetorno;
	}

	public String getGenerosSelecionados() {
		if (selectedGeneros != null && selectedGeneros.length > 0) {
		StringBuilder sb = new StringBuilder();
			for (String genero : selectedGeneros) {
				sb.append(genero);
				sb.append(", ");
			}
			String retorno = sb.toString().trim();
			return retorno.substring(0, retorno.length() - 1);
		} 
		return "";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String[] getSelectedGeneros() {
		return selectedGeneros;
	}

	public void setSelectedGeneros(String[] selectedGeneros) {
		this.selectedGeneros = selectedGeneros;
	}


	public List<String> getGeneros() {
		return generos;
	}


	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}	
	
}
