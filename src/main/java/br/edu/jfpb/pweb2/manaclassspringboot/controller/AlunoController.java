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
import br.edu.jfpb.pweb2.manaclassspringboot.util.enums.SituacaoEnum;

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
			try {
				if (aluno.getId() != null) {
					modelAndView.setViewName("redirect:/aluno");
					flash.addFlashAttribute("mensagem", "Aluno atualizado com sucesso!");
					flash.addFlashAttribute("typeMessage", "primary");
				} else {
					modelAndView.setViewName("redirect:/aluno/cadastro");
					flash.addFlashAttribute("mensagem", "Aluno cadastrado com sucesso!");
					flash.addFlashAttribute("typeMessage", "primary");
				}
				
				alunoService.saveAluno(aluno);				
			} catch (Exception e) {
				flash.addFlashAttribute("mensagem", "Ocorreu um erro ao cadastrar o usuário!");	
				flash.addFlashAttribute("typeMessage", "danger");
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
	public ModelAndView deletePorId(@PathVariable("id") Integer id, ModelAndView modelAndView , RedirectAttributes flash) {
		alunoService.deleteById(id);
		modelAndView.setViewName("redirect:/aluno");
		flash.addFlashAttribute("mensagem", "Aluno excluído!");
		flash.addFlashAttribute("typeMessage", "warning");
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
				} else {
					aluno.setSituacao(SituacaoEnum.MATRICULADO);
				}
				
				alunoService.saveAluno(aluno);
				flash.addFlashAttribute("mensagem", "Notas atualizadas com sucesso!");
				flash.addFlashAttribute("typeMessage", "primary");
			} catch (Exception e) {
				flash.addFlashAttribute("mensagem", "Ocorreu um erro atualizando o aluno!");
				flash.addFlashAttribute("typeMessage", "danger");
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
				flash.addFlashAttribute("mensagem", "Faltas atualizadas com sucesso!");
				flash.addFlashAttribute("typeMessage", "primary");
			} catch (Exception e) {
				flash.addFlashAttribute("mensagem", "Ocorreu um erro atualizando o aluno!");
				flash.addFlashAttribute("typeMessage", "danger");
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

	@RequestMapping("relatorio")
	public ModelAndView getRelatorio(ModelAndView modelAndView, HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("aluno/relatorio");
			boolean finalizado = true;
			try {
				List<Aluno> alunos = alunoService.findAll();
				
				for(Aluno a: alunos) {
					if (a.getSituacao().getSigla() == "MT" || a.getSituacao().getSigla() == "FN") {
						finalizado = false;
					}
				}
				modelAndView.addObject("alunos", alunos);
				modelAndView.addObject("finalizado", finalizado);
			} catch (CadernetaException e) {
				e.printStackTrace();
			}
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/{id}/update")
	public ModelAndView updateUser(@PathVariable("id") Integer id, ModelAndView modelAndView, HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			Aluno aluno = alunoService.findById(id).get();
			modelAndView.setViewName("aluno/cadastro");
			modelAndView.addObject("aluno", aluno);
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/insert-auto")
	public ModelAndView insertAuto(ModelAndView modelAndView, HttpSession session, RedirectAttributes flash) {
		if (session.getAttribute("usuarioLogado") != null) {
			modelAndView.setViewName("redirect:/");
			
			try {
				List<Aluno> alunos = alunoService.findAll();
				if (alunos.size() > 0) {
					flash.addFlashAttribute("mensagem", "A tabela já possui alunos cadastrados.");
					flash.addFlashAttribute("typeMessage", "danger");
				} else {
					alunoService.insertAutoSave();
					flash.addFlashAttribute("mensagem", "Alunos cadastrados com sucesso!");
					flash.addFlashAttribute("typeMessage", "primary");					
				}
			} catch (CadernetaException e) {
				flash.addFlashAttribute("mensagem", "Ocorreu um erro ao inserir os dados.");
				flash.addFlashAttribute("typeMessage", "danger");
			}
			
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}


}
