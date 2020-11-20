package br.edu.ifpb.pweb2.caderneta.business.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.caderneta.business.exception.CadernetaException;
import br.edu.ifpb.pweb2.caderneta.business.model.Usuario;
import br.edu.ifpb.pweb2.caderneta.dao.UsuarioDAO;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Transactional
	public void saveUsuario(Usuario usuario) {
		if (usuario.getId() == null) {
			usuarioDAO.save(usuario);
		} else {
			usuarioDAO.update(usuario);
		}
		
	}
	
	public Usuario findByLogin(String login) {
		return usuarioDAO.findByLogin(login);
	}
	
	public Usuario findById(Integer id) {
		return usuarioDAO.findById(id);
	}

	public List<Usuario> findAll() throws CadernetaException {
		return usuarioDAO.findAll();
	}

	@Transactional
	public void deleteById(Integer id) {
		usuarioDAO.deleteById(id);
	}

}
