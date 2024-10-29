package ks52team02.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	@GetMapping("/info")
	public String getInfo(Model model) {
		
		model.addAttribute("activeMenu", "info");
		
		return "member/commonInfo";
	}

}
