package kr.co.thesmc.controller.adm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/adm_super")
public class IndexController {

	
	@RequestMapping(value={"","/"}, method = RequestMethod.GET)
	public String login(Model model) throws Exception {
		return "adm_super/login";
	}

}
