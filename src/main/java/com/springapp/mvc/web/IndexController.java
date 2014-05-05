package com.springapp.mvc.web;

import com.springapp.mvc.service.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping("")
    public String redirectToIndex(ModelMap model){
        return "redirect:/index";
    }

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "index";
	}

    @RequestMapping(value = "login")
    public String shoLogIn(@RequestParam(required = false, value="error") String error,
            ModelMap model){
        if(error!=null&&!error.equals("")){
            model.addAttribute("error", error);
        }
        return "login";
    }

    @RequestMapping("/error403")
    public String eror403(ModelMap map){
        return "error403";
    }
}