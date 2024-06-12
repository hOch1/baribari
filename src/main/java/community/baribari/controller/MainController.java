package community.baribari.controller;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.sign.LoginDto;
import community.baribari.dto.sign.SignUpDto;
import community.baribari.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SignService signService;


    @GetMapping(value = {"/", ""})
    public String main(Model model, Authentication authentication){
        if (authentication != null) {
            PrincipalDetail principalDetail = (PrincipalDetail) authentication.getPrincipal();
            System.out.println(principalDetail);
            model.addAttribute("auth", principalDetail);
        }else
            model.addAttribute("auth", null);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("login", new LoginDto());
        return "sign/login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("signup", new SignUpDto());
        return "sign/signup";
    }

    @PostMapping("/signup.do")
    public String signup(@ModelAttribute SignUpDto signUpDto, Model model){
        signService.signup(signUpDto);
        return "redirect:/login";
    }
}
