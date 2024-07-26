package community.baribari.controller.member;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.board.Category;
import community.baribari.service.board.BoardService;
import community.baribari.service.board.extend.BariRecruitService;
import community.baribari.service.board.extend.BariReviewService;
import community.baribari.service.board.extend.FreeBoardService;
import community.baribari.service.board.extend.QnABoardService;
import community.baribari.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final FreeBoardService freeBoardService;
    private final QnABoardService qnABoardService;
    private final BariReviewService bariReviewService;
    private final BariRecruitService bariRecruitService;

    @GetMapping("/myPage")
    public String myPage(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("member", principalDetail.getMember());
        return "member/myPage";
    }

    @GetMapping("/{id}/posts")
    public String posts(@PathVariable("id") Long id, Model model){
        model.addAttribute("bariRecruits", bariRecruitService.myList(Category.RECRUIT, id));
        model.addAttribute("bariReviews", bariReviewService.myList(Category.REVIEW, id));
        model.addAttribute("freeBoards", freeBoardService.myList(Category.FREE, id));
        model.addAttribute("qnaBoards", qnABoardService.myList(Category.QNA, id));

        return "member/posts";
    }
}
