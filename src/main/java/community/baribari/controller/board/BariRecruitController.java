package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariRecruitDto;
import community.baribari.dto.comment.CommentDto;
import community.baribari.entity.board.Category;
import community.baribari.exception.CustomException;
import community.baribari.service.board.extend.BariRecruitService;
import community.baribari.service.comment.CommentService;
import jakarta.validation.Valid;
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
                          @RequestParam(defaultValue = "0", value = "page") int page) {
        model.addAttribute("bariRecruits", bariRecruitService.list(PageRequest.of(page, 10)));
        return "board/bari-recruit";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new BariRecruitDto());
        return "board/write/recruit-write";
    }

    @PostMapping("/write.do")
    public String write(@ModelAttribute @Valid BariRecruitDto bariRecruitDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        bariRecruitService.save(bariRecruitDto, principalDetail);
        return "redirect:/bari-recruit";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes){
        try {
            bariRecruitService.viewCountUp(id);
            model.addAttribute("board", bariRecruitService.detail(id));
            model.addAttribute("comments", commentService.list(id));
            model.addAttribute("comment", new CommentDto());
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/bari-recruit";
        }
        return "board/detail/bari-recruit-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("board", bariRecruitService.detail(id));
        return "board/update/recruit-update";
    }

    @PostMapping("/update.do")
    public String update(@ModelAttribute @Valid BariRecruitDto bariRecruitDto, RedirectAttributes redirectAttributes){
        bariRecruitService.update(bariRecruitDto);
        redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
        return "redirect:/bari-recruit/detail/"+bariRecruitDto.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        bariRecruitService.delete(id);
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/bari-recruit";
    }
}
