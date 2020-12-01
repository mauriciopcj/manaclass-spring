package br.edu.jfpb.pweb2.manaclassspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.jfpb.pweb2.manaclassspringboot.model.Usuario;
import br.edu.jfpb.pweb2.manaclassspringboot.repository.UsuarioRepository;
import br.edu.jfpb.pweb2.manaclassspringboot.util.PasswordUtil;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public boolean isValido(Usuario usuario) {
		Usuario usuarioBD = usuarioRepository.findByEmail(usuario.getEmail());
		boolean valido = false;
		if (usuarioBD != null) {
			if (PasswordUtil.checkPass(usuario.getSenha(), usuarioBD.getSenha())) {
				valido = true;
			}
		} 
		return valido;
	}
	

}
