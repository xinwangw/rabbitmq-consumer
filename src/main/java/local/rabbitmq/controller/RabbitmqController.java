package local.rabbitmq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RabbitmqController {
	@RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("name", "aaa");
        return "index";
    }
	
	@RequestMapping("/audit-log")
    public String auditLog() {
        return "auditlog";
    }
}
