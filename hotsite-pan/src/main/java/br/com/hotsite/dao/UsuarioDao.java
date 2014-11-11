package br.com.hotsite.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.hotsite.modelo.Usuario;
import br.com.hotsite.util.JPAUtil;

public class UsuarioDao {
	
	private EntityManager em;
	
	
	public UsuarioDao() {
		this.em = JPAUtil.getEntityManager();
	}
	
	public Usuario findByCpf(String cpf) {
		Usuario usuario = em.find(Usuario.class, cpf);
		return usuario;
	}
	
	private void insere(Usuario usuario) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(usuario);
		tx.commit();
		em.close();
	}
	
	public void persiste(Usuario usuario) {
		Usuario usuarioDB = findByCpf(usuario.getCpf());
		if (usuarioDB != null) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(usuario);
			tx.commit();
			em.close();
		} else {
			insere(usuario);
		}
		
	}
	
	public void removeByCpf(String cpf) {
		Usuario usuario = findByCpf(cpf);
		remove(usuario);
	}
	
	public void remove(Usuario usuario) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(usuario);
		tx.commit();
		em.close();
	}
}
