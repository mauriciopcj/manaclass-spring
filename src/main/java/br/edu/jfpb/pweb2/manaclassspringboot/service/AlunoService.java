package br.edu.jfpb.pweb2.manaclassspringboot.service;

import java.util.Date;
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
		return alunoRepository.findAllByOrderByNome();
	}

	@Transactional
	public void deleteById(Integer id) {
		alunoRepository.deleteById(id);
	}
	
	public void insertAutoSave() {
		Aluno aluno = new Aluno();
				
		aluno.setNome("Joseph Adrian Almeida dos Santos");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);
		
		aluno = new Aluno();
		aluno.setNome("Mauricio Pereira da Costa Junior");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("Karoline Andrade da Silva Lima");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("Erick Cristhian Moura da Silva");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("Rafael da Silva Marinho");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("Mayara Gomes Pereira");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("Francisco Icaro Cipriano Silva");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("George Lucas Gomes de Queiroz");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("José Ronaldo de Souza Junior");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("Lucas Sales da Silva");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("Taysa Samara Mendes Pinheiro");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);

		aluno = new Aluno();
		aluno.setNome("Jailson Silva de França");
		aluno.setDataNascimento(new Date());
		this.saveAluno(aluno);
	}
}
