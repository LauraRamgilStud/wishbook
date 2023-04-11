package com.wishbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private UserRepository userRepository;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("") String fname,
                               @RequestParam("") String lname,
                               @RequestParam("") String email,
                               @RequestParam("") String password, Model model){
        //Check if user with mail already exists
        if(!checkEmail(email)){
            User newUser = new User(fname, lname, email, password);

            //add user to DB
            userRepository.addUser(newUser);
        }else{
            String errorMessage = "User is already registered!";
            model.addAttribute("errorMessage", errorMessage);
        }
        return "redirect:/";
    }

}
