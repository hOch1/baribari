package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.AnswerDto;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.service.board.QnABoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna-board")
public class QnABoardController {

    private final QnABoardService qnABoardService;

    @GetMapping(value = {"", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page){

        Page<QnABoardDto> list = qnABoardService.list(PageRequest.of(page, 10));

        model.addAttribute("qnaBoards", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("totalElements", list.getTotalElements());
        return "board/qna-board";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new QnABoardDto());
        return "board/write/qna-write";
    }

    @PostMapping("/write.do")
    public String write(QnABoardDto qnABoardDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        qnABoardService.save(qnABoardDto, principalDetail);
        return "redirect:/qna-board";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        qnABoardService.viewCountUp(id);
        model.addAttribute("qnaBoard", qnABoardService.detail(id));
        return "board/detail/qna-detail";
    }

    @PostMapping("/answer/{questionId}/write.do")
    public String answerWrite(@PathVariable Long questionId,
                              AnswerDto answerDto,
                              @AuthenticationPrincipal PrincipalDetail principalDetail){
        qnABoardService.writeAnswer(questionId, principalDetail, answerDto);

        return "redirect:/qna-board/detail/" + questionId;
    }

    @PostMapping("/star/{id}")
    public String boardStar (@PathVariable Long id,
                             @AuthenticationPrincipal PrincipalDetail principalDetail){
        qnABoardService.starCountUp(id, principalDetail);
        return "redirect:/qna-board/detail/" + id;
    }
}
