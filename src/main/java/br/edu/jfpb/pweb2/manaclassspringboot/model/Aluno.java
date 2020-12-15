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

import br.edu.jfpb.pweb2.manaclassspringboot.util.enums.SituacaoEnum;
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

	private Integer faltas = 0;
	
	@Column(name = "nota_1")
	private BigDecimal nota1;
	
	@Column(name = "nota_2")
	private BigDecimal nota2;
	
	@Column(name = "nota_3")
	private BigDecimal nota3;
	
	@Column(name = "nota_final")
	private BigDecimal notaFinal;
	
	private SituacaoEnum situacao = SituacaoEnum.MATRICULADO;

	// HELPERS
	
	public SituacaoEnum calculateSituacao() {
		if(this.getNota1() != null && this.getNota2() != null && this.getNota3() != null && this.notaFinal == null) {
			
			if(getMedia() >= 70 && this.faltas < 25) {
				return SituacaoEnum.APROVADO;
				
			} else if(getMedia() >= 40 && this.faltas < 25) {
				return SituacaoEnum.FINAL;
				
			} else if(this.faltas >= 25) {
				return SituacaoEnum.REPROVADO_FALTA;
				
			} else {
				return SituacaoEnum.REPROVADO_FINAL;
			}
		} else if(this.getNota1() != null && this.getNota2() != null && this.getNota3() != null && this.notaFinal != null) {
			
			if(((getMedia() * 60) + (this.notaFinal.doubleValue() * 40)) / 100 >= 50 && this.faltas < 25) {
				return SituacaoEnum.APROVADO;
				
			} else if(this.faltas >= 25) {
				return SituacaoEnum.REPROVADO_FALTA;
				
			} else {
				return SituacaoEnum.REPROVADO_FINAL;
			}
		}
		
		return null;
	}

	public Boolean canGoToFinal() {
		if(this.getNota1() == null || this.getNota2() == null || this.getNota3() == null) {
			return false;
		}
		
		return getMedia() >= 40 && getMedia() < 70 && getFaltas() <= 25;
	}

	public Double getMedia() {
		Double n1 = 0.0;
		Double n2 = 0.0;
		Double n3 = 0.0;
		if (this.nota1 != null) n1 = nota1.doubleValue();
		if (this.nota2 != null) n2 = nota2.doubleValue();
		if (this.nota3 != null) n3 = nota3.doubleValue();

		return (n1 + n2 + n3)/3.0;
	}

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

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) { this.faltas = faltas; }

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

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Aluno{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", dataNascimento=" + dataNascimento +
				", faltas=" + faltas +
				", nota1=" + nota1 +
				", nota2=" + nota2 +
				", nota3=" + nota3 +
				", notaFinal=" + notaFinal +
				", situacao=" + situacao +
				'}';
	}
}
