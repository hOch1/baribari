package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariRecruitDto;
import community.baribari.dto.comment.CommentDto;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.service.board.BariRecruitService;
import community.baribari.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bari-recruit")
@RequiredArgsConstructor
public class BariRecruitController {

    private final BariRecruitService bariRecruitService;
    private final CommentService commentService;

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
    public String write(@ModelAttribute BariRecruitDto bariRecruitDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        bariRecruitService.save(bariRecruitDto, principalDetail);
        return "redirect:/bari-recruit";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        try {
            bariRecruitService.viewCountUp(id);
            model.addAttribute("bariRecruit", bariRecruitService.detail(id));
            model.addAttribute("comments", commentService.list(id));
            model.addAttribute("comment", new CommentDto());
        }catch (BoardNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/bari-recruit";
        }
        return "bari/detail/bari-recruit-detail";
    }
}
