package br.com.hotsite.managedbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "usuario")
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;
	private String telefone;
	private String celular;
	private String email;
	private String unidade;
	private String regional;
	private String cidade;
	private String estado;
	private String gostoMusical;
	private int status;
	private Map<String, Integer> comboStatus;
	
	@PostConstruct
	public void init() {
		comboStatus = new HashMap<String, Integer>();
		comboStatus.put("Desativado", 0);
		comboStatus.put("Ativo", 1);
		comboStatus.put("Cancelado", 2);
		
		status = 2;
	}

	@Override
	public String toString() {
		return "UsuarioBean [nome=" + nome + ", cpf=" + cpf + ", telefone="
				+ telefone + ", celular=" + celular + ", email=" + email
				+ ", unidade=" + unidade + ", regional=" + regional
				+ ", cidade=" + cidade + ", estado=" + estado
				+ ", gostoMusical=" + gostoMusical + ", status=" + status;
	}
	
	public String salvar() {
		System.out.println(this.toString());
		return "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getGostoMusical() {
		return gostoMusical;
	}

	public void setGostoMusical(String gostoMusical) {
		this.gostoMusical = gostoMusical;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, Integer> getComboStatus() {
		return comboStatus;
	}
	
}
