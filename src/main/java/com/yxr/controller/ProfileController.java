package com.yxr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @GetMapping("/profile/{section}")
    public String toProfile(@PathVariable(name="section") String section, Model model){
        if("questions".equals(section)){
            model.addAttribute("section",section);
            model.addAttribute("sectionName","questions");
        }
        return  "/profile";
    }
}
