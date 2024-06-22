package community.baribari.controller.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.Member;
import community.baribari.service.BariService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bari")
@RequiredArgsConstructor
public class BariController {

    private final BariService bariService;

    @GetMapping("/recruit")
    public String recruit(Model model) {
        return "bari/bari-recruit";
    }

    @GetMapping("/review")
    public String review(Model model) {
        return "bari/bari-review";
    }

    @PostMapping("/write.do")
    public String write(@RequestParam String title, @RequestParam String content,
                        @AuthenticationPrincipal PrincipalDetail principalDetail) {
        bariService.save(title, content, principalDetail);
        return "redirect:/";
    }
}
