package br.edu.jfpb.pweb2.manaclassspringboot.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "alunos")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Column(name = "nota_1")
	private BigDecimal nota1;
	
	@Column(name = "nota_2")
	private BigDecimal nota2;
	
	@Column(name = "nota_3")
	private BigDecimal nota3;
	
	@Column(name = "nota_final")
	private BigDecimal notaFinal;
	
	private String situacao;
	
	// CONSTRUTOR
	
	public Aluno() { }
	
	// GETs and SETs

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public BigDecimal getNota1() {
		return nota1;
	}

	public void setNota1(BigDecimal nota1) {
		this.nota1 = nota1;
	}

	public BigDecimal getNota2() {
		return nota2;
	}

	public void setNota2(BigDecimal nota2) {
		this.nota2 = nota2;
	}

	public BigDecimal getNota3() {
		return nota3;
	}

	public void setNota3(BigDecimal nota3) {
		this.nota3 = nota3;
	}

	public BigDecimal getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(BigDecimal notaFinal) {
		this.notaFinal = notaFinal;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
