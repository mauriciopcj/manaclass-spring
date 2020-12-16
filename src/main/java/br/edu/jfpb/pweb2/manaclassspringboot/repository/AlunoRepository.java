package br.edu.jfpb.pweb2.manaclassspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.jfpb.pweb2.manaclassspringboot.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	
	public List<Aluno> findAllByOrderByNome();

}
