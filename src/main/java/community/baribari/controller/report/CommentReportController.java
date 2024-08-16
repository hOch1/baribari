package community.baribari.controller.report;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.report.CommentReportDto;
import community.baribari.service.report.extend.CommentReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/report/comment")
@RequiredArgsConstructor
public class CommentReportController {

    private final CommentReportService commentReportService;

    @GetMapping("/write/{id}")
    public String write(@PathVariable("id") Long id,
                        Model model) {
        model.addAttribute("report", new CommentReportDto());
        model.addAttribute("id", id);
        return "report/commentReport";
    }

    @PostMapping("/add/{id}")
    public String report(@PathVariable("id") Long id,
                         @ModelAttribute CommentReportDto commentReportDto,
                         @AuthenticationPrincipal PrincipalDetail principalDetail,
                         RedirectAttributes redirectAttributes) throws Throwable {

        commentReportService.save(commentReportDto, principalDetail, id);
        redirectAttributes.addAttribute("message", "신고가 접수되었습니다.");
        return "redirect:/";
    }

}
