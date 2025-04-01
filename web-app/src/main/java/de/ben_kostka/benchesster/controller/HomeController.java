package de.ben_kostka.benchesster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
//@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getHome(){

        return "home";
    }

}
