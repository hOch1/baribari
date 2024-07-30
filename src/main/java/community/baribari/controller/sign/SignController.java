package community.baribari.controller.sign;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.sign.SignUpDto;
import community.baribari.service.sign.SignService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String signup(@ModelAttribute @Valid SignUpDto signUpDto){
        signService.signup(signUpDto);
        return "redirect:/login";
    }

    @GetMapping("/set-nickname")
    public String setNickname(){
        return "sign/set-nickname";
    }

    @PostMapping("/nickname-setup")
    public String setNickname(@RequestParam("nickname") @Valid
                                  @NotBlank(message = "닉네임을 입력해주세요.")
                                  @Size(min = 2, message = "닉네임은 2자이상 입력해주세요.")
                                  String nickname,
                              @AuthenticationPrincipal PrincipalDetail principalDetail) {

        signService.setNickname(nickname, principalDetail);
        return "redirect:/";
    }
}
