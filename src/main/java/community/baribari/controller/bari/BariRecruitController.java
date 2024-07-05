package community.baribari.controller.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariRecruitDto;
import community.baribari.service.bari.BariRecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String recruit(Model model,
                          @RequestParam(defaultValue = "0") int page) {

        Page<BariRecruitDto> list = bariRecruitService.list(PageRequest.of(page, 10));
        model.addAttribute("bariRecruits", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("totalElements", list.getTotalElements());
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

    @PostMapping("/star/{id}")
    public String boardStar (@PathVariable Long id,
                             @AuthenticationPrincipal PrincipalDetail principalDetail){
        bariRecruitService.starCountUp(id, principalDetail);
        return "redirect:/bari-recruit/detail/" + id;
    }

}
