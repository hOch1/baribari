package community.baribari.controller.member;

import community.baribari.config.PrincipalDetail;
import community.baribari.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/myPage")
    public String myPage(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("member", principalDetail.getMember());
        return "member/myPage";
    }
}
