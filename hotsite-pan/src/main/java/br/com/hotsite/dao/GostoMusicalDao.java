package br.com.hotsite.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.hotsite.modelo.GostoMusical;
import br.com.hotsite.util.JPAUtil;

public class GostoMusicalDao {

private EntityManager em;
	
	
	public GostoMusicalDao() {
		this.em = JPAUtil.getEntityManager();
	}
	
	public GostoMusical findById(Integer id) {
		GostoMusical gostoMusical = em.find(GostoMusical.class, id);
		return gostoMusical;
	}
	
	public List<GostoMusical> getAllGostosMusicais() {
		List<GostoMusical> gostosMusicais;
		Query query = em.createQuery("select gm from GostoMusical gm", GostoMusical.class);
		gostosMusicais = query.getResultList();
		return gostosMusicais;
	}
	
	private void insere(GostoMusical gostoMusical) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(gostoMusical);
		tx.commit();
		em.close();
	}
	
	public void persiste(GostoMusical gostoMusical) {
		GostoMusical gostoMusicalDB = findById(gostoMusical.getId());
		if (gostoMusicalDB != null) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(gostoMusical);
			tx.commit();
			em.close();
		} else {
			insere(gostoMusical);
		}
		
	}
	
	public void removeById(Integer id) {
		GostoMusical gostoMusical = findById(id);
		remove(gostoMusical);
	}
	
	public void remove(GostoMusical gostoMusical) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(gostoMusical);
		tx.commit();
		em.close();
	}
}
