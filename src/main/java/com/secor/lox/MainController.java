package com.secor.lox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    CredentialRepository credentialRepository;

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password)
    {
        Credential credential = new Credential();
        credential.setUsername(username);
        credential.setPassword(password);

        credentialRepository.save(credential);

        return "redirect:/index.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model)
    {
        Credential credential = credentialRepository.findById(username).orElse(null);
        if (credential != null && credential.getPassword().equals(password))
        {
            model.addAttribute("username", username);
            return "dashboard";
        }
        else
        {
            return "redirect:/index.html";
        }
    }

}
