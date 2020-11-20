package br.edu.ifpb.pweb2.caderneta.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDAO<T extends Serializable, FK> implements IGenericDAO<T, FK> {
	private Class<T> clazz;

	@PersistenceContext(unitName = "")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	public void setClazz(Class<T> clazzToSet) { 
		this.clazz = clazzToSet;
	}

	@Override
	public T findById(FK id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	@Override
	public void save(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public T update(T entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(FK entityId) {
		T entity = findById(entityId);
		delete(entity);
	}
	
	@Override
	public void commit() {
		this.entityManager.flush();
		this.entityManager.getTransaction().commit();
	}

	@Override
	public void rollback() {
		this.entityManager.getTransaction().rollback();		
	}
	
	@Override
	public EntityTransaction beginTransaction() {
		return this.entityManager.getTransaction();
	}
}
