package balancika.ame.controller.report;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/report/account-receivable/")
public class AccountReceivableController {
	@RequestMapping(value = {"/invoice-register"}, method = RequestMethod.GET)
	public String invoiceRegister(ModelMap model, HttpServletRequest request){
		model.addAttribute("title", "Invoice Register");
		//model.addAttribute("mDashboardAct", "active");
		//model.addAttribute("mAM","open");
		return "report/account-receivable/invoice-register";
	}
	@RequestMapping(value = {"/aged-receivable"}, method = RequestMethod.GET)
	public String agedReceivable(ModelMap model, HttpServletRequest request){
		model.addAttribute("title", "Aged Receivable");
		//model.addAttribute("mDashboardAct", "active");
		//model.addAttribute("mAM","open");
		return "report/account-receivable/aged-receivable";
	}
}
