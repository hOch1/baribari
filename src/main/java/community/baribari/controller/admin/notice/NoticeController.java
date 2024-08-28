package community.baribari.controller.admin.notice;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.notice.NoticeDto;
import community.baribari.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping(value = {"", "/"})
    public String index(Model model){
        model.addAttribute("notices", noticeService.list());
        return "admin/notice/index";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new NoticeDto());
        return "admin/notice/write";
    }

    @PostMapping("/write.do")
    public String write(@ModelAttribute NoticeDto noticeDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail,
                        RedirectAttributes redirectAttributes){

        noticeService.save(noticeDto, principalDetail);
        redirectAttributes.addFlashAttribute("message", "공지사항이 등록되었습니다.");
        return "redirect:/admin/notice";
    }
}
