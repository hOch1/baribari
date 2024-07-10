package community.baribari.controller;

import community.baribari.service.board.BariRecruitService;
import community.baribari.service.board.BariReviewService;
import community.baribari.service.board.FreeBoardService;
import community.baribari.service.board.QnABoardService;
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
        model.addAttribute("bariRecruits", bariRecruitService.mainList());
        model.addAttribute("bariReviews", bariReviewService.mainList());
        model.addAttribute("freeBoards", freeBoardService.mainList());
        model.addAttribute("qnaBoards", qnABoardService.mainList());

        return "home";
    }


}
