package br.edu.jfpb.pweb2.manaclassspringboot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.jfpb.pweb2.manaclassspringboot.exception.CadernetaException;
import br.edu.jfpb.pweb2.manaclassspringboot.model.Aluno;
import br.edu.jfpb.pweb2.manaclassspringboot.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Transactional
	public void saveAluno(Aluno aluno) {
		alunoRepository.save(aluno);
	}
	
	public Optional<Aluno> findById(Integer id) {
		return alunoRepository.findById(id);
	}

	public List<Aluno> findAll() throws CadernetaException {
		return alunoRepository.findAll();
	}

	@Transactional
	public void deleteById(Integer id) {
		alunoRepository.deleteById(id);
	}
}
