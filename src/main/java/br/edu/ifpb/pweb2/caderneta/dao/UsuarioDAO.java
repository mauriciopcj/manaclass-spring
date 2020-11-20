package br.edu.ifpb.pweb2.caderneta.dao;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.caderneta.business.model.Usuario;

@Repository
public class UsuarioDAO extends GenericDAO<Usuario, Integer> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Usuario findByLogin(String login) {
		Usuario usuario = null;
		try {
			Query q = this.entityManager.createQuery("from Usuario u where u.email = :login");
			q.setParameter("login", login);
			usuario = (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			usuario = null;
		}
		return usuario;
	}

}
