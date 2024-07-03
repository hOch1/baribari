package community.baribari.controller.bari;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.bari.BariReviewDto;
import community.baribari.service.bari.BariReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bari-review")
public class BariReviewController {

    private final BariReviewService bariReviewService;

    @GetMapping(value = {"", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page) {

        Page<BariReviewDto> list = bariReviewService.list(PageRequest.of(page, 10));

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
    public String detail(@PathVariable Long id, Model model) {
        bariReviewService.viewCountUp(id);
        model.addAttribute("bariReview", bariReviewService.detail(id));
        return "bari/detail/bari-review-detail";
    }
}
