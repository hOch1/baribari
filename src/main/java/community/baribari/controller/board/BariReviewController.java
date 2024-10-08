package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariReviewDto;
import community.baribari.dto.comment.CommentDto;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.service.board.extend.BariReviewService;
import community.baribari.service.comment.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bari-review")
public class BariReviewController {

    private final BariReviewService bariReviewService;
    private final CommentService commentService;

    @GetMapping(value = {"", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "0", value = "page") int page) {
        model.addAttribute("bariReviews", bariReviewService.list(PageRequest.of(page, 10)));
        return "board/review/index";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new BariReviewDto());
        return "board/review/write";
    }

    @PostMapping("/write.do")
    public String write(@ModelAttribute @Valid BariReviewDto bariReviewDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail) {
        bariReviewService.save(bariReviewDto, principalDetail);
        return "redirect:/bari-review";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        bariReviewService.viewCountUp(id);
        model.addAttribute("board", bariReviewService.detail(id));
        model.addAttribute("comments", commentService.list(id));
        model.addAttribute("comment", new CommentDto());

        return "board/review/detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model,
                         @AuthenticationPrincipal PrincipalDetail principalDetail){
        BariReviewDto dto = bariReviewService.detail(id);

        if (!principalDetail.getMember().getId().equals(dto.getMember().getId()))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        model.addAttribute("board", dto);
        return "board/review/update";
    }

    @PostMapping("/update.do")
    public String update(@ModelAttribute @Valid BariReviewDto bariReviewDto, RedirectAttributes redirectAttributes){
        bariReviewService.update(bariReviewDto);
        redirectAttributes.addFlashAttribute("message", "수정되었습니다");
        return "redirect:/bari-review/detail/"+bariReviewDto.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        bariReviewService.delete(id);
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/bari-review";
    }
}
