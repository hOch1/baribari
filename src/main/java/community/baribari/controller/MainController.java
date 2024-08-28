package community.baribari.controller;

import community.baribari.entity.board.Category;
import community.baribari.service.board.extend.BariRecruitService;
import community.baribari.service.board.extend.BariReviewService;
import community.baribari.service.board.extend.FreeBoardService;
import community.baribari.service.board.extend.QnABoardService;
import community.baribari.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BariRecruitService bariRecruitService;
    private final BariReviewService bariReviewService;
    private final FreeBoardService freeBoardService;
    private final QnABoardService qnABoardService;
    private final NoticeService noticeService;

    @GetMapping(value = {"/", ""})
    public String index(Model model){
        model.addAttribute("bariRecruits", bariRecruitService.mainList());
        model.addAttribute("bariReviews", bariReviewService.mainList());
        model.addAttribute("freeBoards", freeBoardService.mainList());
        model.addAttribute("qnaBoards", qnABoardService.mainList());
        model.addAttribute("notices", noticeService.mainList());

        return "home";
    }

    @GetMapping("/notice")
    public String notice(@RequestParam("page") int page,
                         Model model){
        model.addAttribute("notices", noticeService.noticePage(PageRequest.of(page, 10)));
        return "notice/index";
    }
}
