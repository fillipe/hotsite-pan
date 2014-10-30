package br.com.hotsite.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.hotsite.modelo.Usuario;

public class TesteConexao {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		
		Usuario usuario = new Usuario();
		usuario.setNome("Fillipe");
		usuario.setCpf("33331527890");
		usuario.setCelular("11985381322");
		usuario.setEmail("fillipeaguiar.soares@gmail.com");
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(usuario);
//		em.remove(usuario);
		
		tx.commit();
		
		em.close();
		emf.close();
	}
}
