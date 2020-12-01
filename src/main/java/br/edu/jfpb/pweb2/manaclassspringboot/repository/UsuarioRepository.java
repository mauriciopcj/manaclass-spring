package br.edu.jfpb.pweb2.manaclassspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.jfpb.pweb2.manaclassspringboot.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByEmail(String email);

}
