package community.baribari.controller.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariRecruitDto;
import community.baribari.service.bari.BariRecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bari-recruit")
@RequiredArgsConstructor
public class BariRecruitController {

    private final BariRecruitService bariRecruitService;

    @GetMapping(value = {"", "/"})
    public String recruit(Model model) {
        model.addAttribute("bariRecruits", bariRecruitService.list());
        return "bari/bari-recruit";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new BariRecruitDto());
        return "bari/write/recruit-write";
    }

    @PostMapping("/write.do")
    public String write(@ModelAttribute BariRecruitDto recruitDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        bariRecruitService.save(recruitDto, principalDetail);
        return "redirect:/bari-recruit";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        bariRecruitService.viewCountUp(id);
        model.addAttribute("bariRecruit", bariRecruitService.detail(id));
        return "bari/detail/bari-recruit-detail";
    }

}
