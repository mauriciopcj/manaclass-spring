package br.edu.ifpb.pweb2.caderneta.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.caderneta.business.model.Aluno;

@Repository
public class AlunoDAO extends GenericDAO<Aluno, Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

}
