package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.service.board.extend.QnABoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna-board")
public class QnABoardController {

    private final QnABoardService qnABoardService;

    @GetMapping(value = {"", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "0", value = "page") int page){
        model.addAttribute("qnaBoards", qnABoardService.list(PageRequest.of(page, 10)));
        return "board/qna/index";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new QnABoardDto());
        return "update";
    }

    @PostMapping("/write.do")
    public String write(@ModelAttribute @Valid QnABoardDto qnABoardDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        qnABoardService.save(qnABoardDto, principalDetail);
        return "redirect:/qna-board";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        qnABoardService.viewCountUp(id);
        model.addAttribute("qnaBoard", qnABoardService.detail(id));
        return "board/qna/detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model,
                         @AuthenticationPrincipal PrincipalDetail principalDetail){

        QnABoardDto dto = qnABoardService.detail(id);

        if (!principalDetail.getMember().getId().equals(dto.getMember().getId()))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        model.addAttribute("board", dto);
        return "board/qna/update";
    }

    @PostMapping("/update.do")
    public String update(@ModelAttribute @Valid QnABoardDto qnABoardDto, RedirectAttributes redirectAttributes){
        qnABoardService.update(qnABoardDto);
        redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
        return "redirect:/qna-board/detail/"+qnABoardDto.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        qnABoardService.delete(id);
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/qna-board";
    }
}
