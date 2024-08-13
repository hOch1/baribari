package community.baribari.controller.report;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.BoardDto;
import community.baribari.dto.report.BoardReportDto;
import community.baribari.entity.board.Board;
import community.baribari.entity.report.BoardReport;
import community.baribari.service.board.BoardService;
import community.baribari.service.report.ReportService;
import community.baribari.service.report.extend.BoardReportService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/report/board")
@RequiredArgsConstructor
public class BoardReportController {

    private final BoardReportService boardReportService;

    @GetMapping("/write/{id}")
    public String write(@PathVariable("id") Long id,
                        Model model) {
        model.addAttribute("report", new BoardReportDto());
        model.addAttribute("id", id);
        return "report/boardReport";
    }

    @PostMapping("/add/{id}")
    public String report(@PathVariable("id") Long id,
                         @ModelAttribute BoardReportDto boardReportDto,
                         @AuthenticationPrincipal PrincipalDetail principalDetail,
                         RedirectAttributes redirectAttributes) throws Throwable {

        boardReportService.save(boardReportDto, principalDetail, id);
        redirectAttributes.addAttribute("message", "신고가 접수되었습니다.");
        return "redirect:/";
    }
}
