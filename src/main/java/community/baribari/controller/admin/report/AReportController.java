package community.baribari.controller.admin.report;

import community.baribari.service.report.ReportService;
import community.baribari.service.report.extend.BoardReportService;
import community.baribari.service.report.extend.CommentReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/report")
@RequiredArgsConstructor
public class AReportController {

    private final CommentReportService commentReportService;
    private final BoardReportService boardReportService;

    @GetMapping(value = {"", "/"})
    public String index(){
        return "admin/report/index";
    }
}
