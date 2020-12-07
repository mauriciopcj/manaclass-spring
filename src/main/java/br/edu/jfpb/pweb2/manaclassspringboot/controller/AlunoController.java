package br.edu.jfpb.pweb2.manaclassspringboot.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.jfpb.pweb2.manaclassspringboot.exception.CadernetaException;
import br.edu.jfpb.pweb2.manaclassspringboot.model.Aluno;
import br.edu.jfpb.pweb2.manaclassspringboot.service.AlunoService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;

	@RequestMapping("/cadastro")
	public ModelAndView cadastro(ModelAndView modelAndView, HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("aluno/cadastro");
			modelAndView.addObject("aluno", new Aluno());
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar(ModelAndView modelAndView, HttpSession session, Aluno aluno, RedirectAttributes flash) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("redirect:/aluno/cadastro");
			try {
				alunoService.saveAluno(aluno);
				flash.addFlashAttribute("mensagem", "Aluno cadastrado com sucesso!");				
			} catch (Exception e) {
				flash.addFlashAttribute("mensagem", "Ocorreu um erro ao cadastrar o usuário!");				
			}
			flash.addFlashAttribute("aluno", new Aluno());
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getNotas(ModelAndView modelAndView, HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("aluno/list");
			try {
				List<Aluno> alunos = alunoService.findAll();
				modelAndView.addObject("alunos", alunos);
			} catch (CadernetaException e) {
				e.printStackTrace();
			}
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/{id}/delete")
	public ModelAndView deletePorId(@PathVariable("id") Integer id, ModelAndView modelAndView , RedirectAttributes attr) {
		alunoService.deleteById(id);
		modelAndView.setViewName("redirect:/aluno");
		attr.addFlashAttribute("mensagem", "Aluno excluído!");
		return modelAndView;
	}

	@RequestMapping("/{id}/notas")
	public ModelAndView notas(@PathVariable("id") Integer id, ModelAndView modelAndView, HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			Aluno aluno = alunoService.findById(id).get();
			modelAndView.setViewName("aluno/notas");
			modelAndView.addObject("aluno", aluno);
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/{id}/atualizar-notas-aluno")
	public ModelAndView cadastrarNotas(@PathVariable("id") Integer id,
									   ModelAndView modelAndView, HttpSession session, Aluno alunoNotas, RedirectAttributes flash) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("redirect:/aluno");
			try {
				Aluno aluno = alunoService.findById(id).get();
				aluno.setNota1(alunoNotas.getNota1());
				aluno.setNota2(alunoNotas.getNota2());
				aluno.setNota3(alunoNotas.getNota3());
				
				// Calcular situação do aluno
				if(aluno.calculateSituacao() != null) {
					aluno.setSituacao(aluno.calculateSituacao());
				}
				
				alunoService.saveAluno(aluno);
				flash.addFlashAttribute("mensagem", "Cadastro do aluno atualizado com sucesso!");
			} catch (Exception e) {
				flash.addFlashAttribute("mensagem", "Ocorreu um erro atualizando o aluno!");
			}
			flash.addFlashAttribute("aluno", new Aluno());
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/{id}/faltas")
	public ModelAndView faltas(@PathVariable("id") Integer id, ModelAndView modelAndView, HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			Aluno aluno = alunoService.findById(id).get();
			modelAndView.setViewName("aluno/faltas");
			modelAndView.addObject("aluno", aluno);
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/{id}/atualizar-faltas-aluno")
	public ModelAndView cadastrarFaltas(@PathVariable("id") Integer id,
									   ModelAndView modelAndView, HttpSession session, Aluno alunoFaltas, RedirectAttributes flash) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("redirect:/aluno");
			try {
				Aluno aluno = alunoService.findById(id).get();
				aluno.setFaltas(alunoFaltas.getFaltas());
				
				// Calcular situação do aluno
				if(aluno.calculateSituacao() != null) {
					aluno.setSituacao(aluno.calculateSituacao());
				}
				
				alunoService.saveAluno(aluno);
				flash.addFlashAttribute("mensagem", "Cadastro do aluno atualizado com sucesso!");
			} catch (Exception e) {
				flash.addFlashAttribute("mensagem", "Ocorreu um erro atualizando o aluno!");
			}
			flash.addFlashAttribute("aluno", new Aluno());
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/{id}/final")
	public ModelAndView notaFinal(@PathVariable("id") Integer id, ModelAndView modelAndView, HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			Aluno aluno = alunoService.findById(id).get();
			modelAndView.setViewName("aluno/final");
			modelAndView.addObject("aluno", aluno);
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/{id}/atualizar-final-aluno")
	public ModelAndView cadastrarNotaFinal(@PathVariable("id") Integer id,
										ModelAndView modelAndView, HttpSession session, Aluno alunoNotaFinal, RedirectAttributes flash) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("redirect:/aluno");
			try {
				Aluno aluno = alunoService.findById(id).get();
				aluno.setNotaFinal(alunoNotaFinal.getNotaFinal());
				
				// Calcular situação final do aluno
				if(aluno.calculateSituacaoFinal() != null) {
					aluno.setSituacao(aluno.calculateSituacaoFinal());					
				}
				
				alunoService.saveAluno(aluno);
				flash.addFlashAttribute("mensagem", "Cadastro do aluno atualizado com sucesso!");
			} catch (Exception e) {
				flash.addFlashAttribute("mensagem", "Ocorreu um erro atualizando o aluno!");
			}
			flash.addFlashAttribute("aluno", new Aluno());
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("relatorio")
	public ModelAndView getRelatorio(ModelAndView modelAndView, HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("aluno/relatorio");
			try {
				List<Aluno> alunos = alunoService.findAll();
				modelAndView.addObject("alunos", alunos);
			} catch (CadernetaException e) {
				e.printStackTrace();
			}
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}


}
