package br.edu.ifpb.pweb2.caderneta.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.caderneta.business.model.Usuario;
import br.edu.ifpb.pweb2.caderneta.dao.UsuarioDAO;
import br.edu.ifpb.pweb2.caderneta.util.PasswordUtil;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	public boolean isValido(Usuario usuario) {
		Usuario usuarioBD = usuarioDAO.findByLogin(usuario.getEmail());
		boolean valido = false;
		if (usuarioBD != null) {
			if (PasswordUtil.checkPass(usuario.getSenha(), usuarioBD.getSenha())) {
				valido = true;
			}
		} 
		return valido;
	}
	

}
