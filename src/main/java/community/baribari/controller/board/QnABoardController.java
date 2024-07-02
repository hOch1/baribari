package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.service.board.QnABoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna-board")
public class QnABoardController {

    private final QnABoardService qnABoardService;

    @GetMapping(value = {"", "/"})
    public String index(Model model){
        model.addAttribute("qnaBoards", qnABoardService.list());
        return "board/qna-board";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new QnABoardDto());
        return "board/write/qna-write";
    }

    @PostMapping("/write.do")
    public String write(QnABoardDto qnABoardDto, PrincipalDetail principalDetail){
        qnABoardService.save(qnABoardDto, principalDetail);
        return "redirect:/qna-board";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        qnABoardService.viewCountUp(id);
        model.addAttribute("qnaBoard", qnABoardService.detail(id));
        return "board/detail/qna-detail";
    }
}
