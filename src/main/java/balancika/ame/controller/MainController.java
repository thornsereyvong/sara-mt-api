package balancika.ame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String index(ModelMap model){
		model.addAttribute("title", "Dashboard");
		model.addAttribute("mDashboardAct", "active");
		
		return "index";
	}
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request){
		model.addAttribute("title", "Login Account");
		model.addAttribute("msg", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		return "login";
	}
	
	@RequestMapping(value = {"/authorization/group"}, method = RequestMethod.GET)
	public String group(ModelMap model, HttpServletRequest request){
		model.addAttribute("title", "Group");
		model.addAttribute("mAuthoriAct", "active");
		model.addAttribute("mAuthorOpen","open");
		model.addAttribute("mAuthorGroup","active");
		return "authorization/group";
	}
	
	@RequestMapping(value = {"/authorization"}, method = RequestMethod.GET)
	public String Authorizatoingroup(ModelMap model, HttpServletRequest request){
		model.addAttribute("title", "Authorization");
		model.addAttribute("mAuthoriAct", "active");
		model.addAttribute("mAuthorOpen","open");
		model.addAttribute("mAuthor","active");
		return "authorization/authorization";
	}
	
	@RequestMapping(value = {"/post-transaction"}, method = RequestMethod.GET)
	public String postTransaction(ModelMap model, HttpServletRequest request){
		model.addAttribute("title", "Post Transaction");
		//model.addAttribute("mDashboardAct", "active");
		//model.addAttribute("mAM","open");
		return "post-transaction";
	}
	
	
	
	
	// start layout	
	@RequestMapping("head")
	public String head(ModelMap model, HttpServletRequest request) {
		request.getSession().setAttribute("usernamedb", "posadmin");
		request.getSession().setAttribute("passworddb", "Pa$$w0rd");
		request.getSession().setAttribute("ip", "192.168.123.3");
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
	private String getErrorMessage(HttpServletRequest request, String key){
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = exception.getMessage();
		}
		return error;
	}
}
