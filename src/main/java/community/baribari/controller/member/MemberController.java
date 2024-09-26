package community.baribari.controller.member;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.member.AccountSettingDto;
import community.baribari.entity.board.Category;
import community.baribari.exception.CustomException;
import community.baribari.service.board.extend.BariRecruitService;
import community.baribari.service.board.extend.BariReviewService;
import community.baribari.service.board.extend.FreeBoardService;
import community.baribari.service.board.extend.QnABoardService;
import community.baribari.service.comment.CommentService;
import community.baribari.service.member.MemberService;
import community.baribari.service.report.extend.BoardReportService;
import community.baribari.service.report.extend.CommentReportService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final FreeBoardService freeBoardService;
    private final QnABoardService qnABoardService;
    private final BariReviewService bariReviewService;
    private final BariRecruitService bariRecruitService;
    private final CommentService commentService;

    @GetMapping("/profile/{id}")
    public String profile(Model model,
                         @PathVariable("id") Long id) {
        model.addAttribute("member", memberService.getMember(id));
        return "member/profile";
    }

    @GetMapping("/{id}/posts")
    public String posts(Model model,
                        @RequestParam(defaultValue = "0", value = "page") int page,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){

        model.addAttribute("bariRecruits", bariRecruitService.myList(principalDetail, PageRequest.of(page, 10)));
        model.addAttribute("bariReviews", bariReviewService.myList(principalDetail, PageRequest.of(page, 10)));
        model.addAttribute("freeBoards", freeBoardService.myList(principalDetail, PageRequest.of(page, 10)));
        model.addAttribute("qnaBoards", qnABoardService.myList(principalDetail, PageRequest.of(page, 10)));
        model.addAttribute("memberId", principalDetail.getMember().getId());
        return "member/posts";
    }

    @GetMapping("/{id}/comments")
    public String comments(@PathVariable("id") Long id, Model model,
                           @RequestParam(defaultValue = "0", value = "page") int page){
        model.addAttribute("comments", commentService.myList(id, PageRequest.of(page, 10)));
        model.addAttribute("memberId", id);
        return "member/comments";
    }

    @GetMapping("/account-setting")
    public String accountSetting(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("accountSetting", memberService.getAccountSetting(principalDetail.getMember()));
        return "member/account-setting";
    }

    @PostMapping("/edit/account-setting")
    public String editAccountSetting(@AuthenticationPrincipal PrincipalDetail principalDetail,
                                     AccountSettingDto accountSettingDto,
                                     RedirectAttributes redirectAttributes) {
        memberService.editAccountSetting(principalDetail.getMember(), accountSettingDto);
        redirectAttributes.addFlashAttribute("message", "계정 설정이 변경되었습니다.");
        return "redirect:/member/account-setting";
    }

    @PostMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetail principalDetail,
                               RedirectAttributes redirectAttributes){
        memberService.deleteMember(id, principalDetail);
        redirectAttributes.addFlashAttribute("message", "탈퇴되었습니다.");
        return "redirect:/logout";
    }
}
