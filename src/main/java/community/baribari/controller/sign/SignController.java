package community.baribari.controller.sign;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.sign.SignUpDto;
import community.baribari.exception.CustomException;
import community.baribari.service.sign.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @GetMapping("/login")
    public String login(){
        return "sign/login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("signup", new SignUpDto());
        return "sign/signup";
    }

    @PostMapping("/signup.do")
    public String signup(@ModelAttribute SignUpDto signUpDto,
                         RedirectAttributes redirectAttributes){
        try {
            signService.signup(signUpDto);
            return "redirect:/login";
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/signup";
        }
    }

    @GetMapping("/set-nickname")
    public String setNickname(){
        return "sign/set-nickname";
    }

    @PostMapping("/nickname-setup")
    public String setNickname(@RequestParam("nickname") String nickname,
                              @AuthenticationPrincipal PrincipalDetail principalDetail,
                              RedirectAttributes redirectAttributes){
        try {
            signService.setNickname(nickname, principalDetail);
            return "redirect:/";
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/set-nickname";
        }
    }
}
