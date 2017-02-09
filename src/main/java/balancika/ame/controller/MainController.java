package balancika.ame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String index(ModelMap model){
		model.addAttribute("title", "Dashboard");
		return "index";
	}
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login(ModelMap model){
		model.addAttribute("title", "Login Account");
		return "login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// start layout	
	@RequestMapping("head")
	public String head(ModelMap model, HttpServletRequest request) {
		request.getSession().setAttribute("usernamedb", "posadmin");
		request.getSession().setAttribute("passworddb", "Pa$$w0rd");
		request.getSession().setAttribute("ip", "192.168.123.2");
		request.getSession().setAttribute("port", "3306");
		return "layout/head";
	}
	@RequestMapping("header")
	public String header(ModelMap model, HttpSession session, HttpServletRequest request) {
		//session.setAttribute("company", request.getSession().getAttribute("company"));
		//session.setAttribute("SESSION", getPrincipal());
		//session.setAttribute("users", userController.getUserById(getPrincipal(), request));
		return "layout/header";
	}	
	@RequestMapping("foot")
	public String foot(ModelMap model, HttpSession session, HttpServletRequest request) {		
		return "layout/foot";
	}
	@RequestMapping("footer")
	public String footer(ModelMap model, HttpSession session, HttpServletRequest request) {		
		return "layout/footer";
	}
	@RequestMapping("menu")
	public String menu(ModelMap model, HttpSession session, HttpServletRequest request) {		
		return "layout/menu";
	}
	// end layout
	
}
