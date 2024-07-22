package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BariReviewDto;
import community.baribari.dto.comment.CommentDto;
import community.baribari.entity.board.Category;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.exception.IsDeletedException;
import community.baribari.service.board.extend.BariReviewService;
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
@RequiredArgsConstructor
@RequestMapping("/bari-review")
public class BariReviewController {

    private final BariReviewService bariReviewService;
    private final CommentService commentService;

    @GetMapping(value = {"", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page) {

        Page<BariReviewDto> list = bariReviewService.list(Category.REVIEW, PageRequest.of(page, 10));

        model.addAttribute("bariReviews", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("totalElements", list.getTotalElements());
        return "bari/bari-review";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new BariReviewDto());
        return "bari/write/review-write";
    }

    @PostMapping("/write.do")
    public String write(@ModelAttribute BariReviewDto bariReviewDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail) {
        bariReviewService.save(bariReviewDto, principalDetail);
        return "redirect:/bari-review";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            bariReviewService.viewCountUp(id);
            model.addAttribute("board", bariReviewService.detail(id));
            model.addAttribute("comments", commentService.list(id));
            model.addAttribute("comment", new CommentDto());
        }catch (BoardNotFoundException | IsDeletedException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/bari-review";
        }
        return "bari/detail/bari-review-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("board", bariReviewService.detail(id));
        return "bari/update/review-update";
    }

    @PostMapping("/update.do")
    public String update(@ModelAttribute BariReviewDto bariReviewDto, RedirectAttributes redirectAttributes){
        bariReviewService.update(bariReviewDto);
        redirectAttributes.addFlashAttribute("message", "수정되었습니다");
        return "redirect:/bari-review/detail/"+bariReviewDto.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        bariReviewService.delete(id);
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/bari-review";
    }
}
