package br.edu.ifpb.pweb2.caderneta.business.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.caderneta.business.exception.CadernetaException;
import br.edu.ifpb.pweb2.caderneta.business.model.Aluno;
import br.edu.ifpb.pweb2.caderneta.dao.AlunoDAO;

@Service
public class AlunoService {

	@Autowired
	private AlunoDAO alunoDAO;
	
	@Transactional
	public void saveAluno(Aluno aluno) {
		if (aluno.getId() == null) {
			alunoDAO.save(aluno);
		} else {
			alunoDAO.update(aluno);
		}
		
	}
	
	public Aluno findById(Integer id) {
		return alunoDAO.findById(id);
	}

	public List<Aluno> findAll() throws CadernetaException {
		return alunoDAO.findAll();
	}

	@Transactional
	public void deleteById(Integer id) {
		alunoDAO.deleteById(id);
	}
}
