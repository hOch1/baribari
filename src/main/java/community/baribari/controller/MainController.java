package community.baribari.controller;

import community.baribari.entity.board.Category;
import community.baribari.service.board.extend.BariRecruitService;
import community.baribari.service.board.extend.BariReviewService;
import community.baribari.service.board.extend.FreeBoardService;
import community.baribari.service.board.extend.QnABoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BariRecruitService bariRecruitService;
    private final BariReviewService bariReviewService;
    private final FreeBoardService freeBoardService;
    private final QnABoardService qnABoardService;

    @GetMapping(value = {"/", ""})
    public String main(Model model){
        model.addAttribute("bariRecruits", bariRecruitService.mainList(Category.RECRUIT));
        model.addAttribute("bariReviews", bariReviewService.mainList(Category.REVIEW));
        model.addAttribute("freeBoards", freeBoardService.mainList(Category.FREE));
        model.addAttribute("qnaBoards", qnABoardService.mainList(Category.QNA));

        return "home";
    }
}
