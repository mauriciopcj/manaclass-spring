package br.edu.ifpb.pweb2.caderneta.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.caderneta.business.model.Usuario;
import br.edu.ifpb.pweb2.caderneta.business.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
		
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getForm(ModelAndView modelAndView) {
		modelAndView.setViewName("/login/form");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView autentique(Usuario usuario, HttpSession session, ModelAndView modelAndView, RedirectAttributes flash) {
		if (loginService.isValido(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			flash.addFlashAttribute("mensagem", "Login efetuado com sucesso");
			modelAndView.setViewName("redirect:/home");
		} else {
			flash.addFlashAttribute("mensagem", "Usuário e senha inválidos!");
			modelAndView.setViewName("redirect:/login");
			flash.addFlashAttribute("usuario", new Usuario());
		}
		return modelAndView;
	}
	
	@RequestMapping("/out")
	public ModelAndView logout(HttpSession session, ModelAndView modelAndView) {
		session.invalidate();
		modelAndView.setViewName("redirect:/login");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}

}
