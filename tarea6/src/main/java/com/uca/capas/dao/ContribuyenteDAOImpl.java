package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.Contribuyente;



@Repository
public class ContribuyenteDAOImpl implements ContribuyenteDAO {
	@PersistenceContext(unitName = "capas")
	EntityManager entityManager;

	@Override
	public List<Contribuyente> findAll() throws DataAccessException {
		return entityManager.createNativeQuery(new StringBuffer().append("select * from contribuyente").toString(), Contribuyente.class).getResultList();
	}

	@Override
	public Contribuyente findOne(Integer codigo) throws DataAccessException {
		return entityManager.find(Contribuyente.class, codigo);
	}
	//Registrar contribuyente
	@Override
	@Transactional
	public void save(Contribuyente c) throws DataAccessException {
		
		if(c.getCcontribuyente() == null) { 
			entityManager.persist(c); 
		}
		else { 
			entityManager.merge(c); 
		}
		
	}

}


