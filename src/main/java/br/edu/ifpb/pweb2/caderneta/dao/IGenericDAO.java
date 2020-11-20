package br.edu.ifpb.pweb2.caderneta.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityTransaction;

public interface IGenericDAO<T extends Serializable, FK> {
	
	T findById(FK id);
	
	List<T> findAll();
	
	void save(final T entity);
	
	T update(final T entity);
	
	void delete(final T entity);
	
	void deleteById(final FK id);
	
	EntityTransaction beginTransaction();
	
	void commit();
	
	void rollback();

}
