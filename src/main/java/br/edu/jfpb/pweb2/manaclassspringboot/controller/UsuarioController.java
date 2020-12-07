package br.edu.jfpb.pweb2.manaclassspringboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.jfpb.pweb2.manaclassspringboot.model.Usuario;
import br.edu.jfpb.pweb2.manaclassspringboot.service.UsuarioService;
import br.edu.jfpb.pweb2.manaclassspringboot.util.PasswordUtil;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/cadastro")
	public ModelAndView getForm(HttpSession session, ModelAndView modelAndView) {
		modelAndView.setViewName("/usuarios/cadastro");
		
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado != null) {
			Usuario usuario = usuarioService.findByLogin(usuarioLogado.getEmail()) ;
			
			modelAndView.addObject("usuario", usuario);
		} else {
			modelAndView.addObject("usuario", new Usuario());			
		}
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastrar(Usuario usuario, ModelAndView modelAndView, HttpSession session, RedirectAttributes flash) {
		Usuario u = usuarioService.findByLogin(usuario.getEmail());
		if(u != null) {
			flash.addFlashAttribute("mensagem", "Email já cadastrado");
			modelAndView.setViewName("redirect:usuarios/cadastro");
			return modelAndView;
		}
		
		modelAndView.setViewName("redirect:/login");
		
		String senha = PasswordUtil.hashPassword(usuario.getSenha());
		usuario.setSenha(senha);
		
		String msg;
		
		if (usuario.getId() != null) {
			msg = "atualiza";
		} else {
			msg = "cadastra";
		}
		
		try {
			usuarioService.saveUsuario(usuario);
			flash.addFlashAttribute("mensagem", "Usuário " + msg + "do");
		} catch (Exception e) {
			flash.addFlashAttribute("mensagem", "Erro ao " + msg + "r usuário");
		}
		
		return modelAndView;
	}
	
}
