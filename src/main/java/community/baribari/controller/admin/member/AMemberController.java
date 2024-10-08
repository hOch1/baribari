package community.baribari.controller.admin.member;

import community.baribari.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class AMemberController {

    private final MemberService memberService;

    @GetMapping(value = {"", "/"})
    public String index(Model model){
        model.addAttribute("members", memberService.list());
        return "admin/member/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("member", memberService.getMember(id));
        return "admin/member/detail";
    }




}
