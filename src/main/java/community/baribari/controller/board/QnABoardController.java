package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.QnABoardDto;
import community.baribari.entity.board.Category;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.exception.IsDeletedException;
import community.baribari.service.board.extend.QnABoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
                        @RequestParam(defaultValue = "0") int page){

        Page<QnABoardDto> list = qnABoardService.list(Category.QNA, PageRequest.of(page, 10));

        model.addAttribute("board", list);
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
    public String write(@ModelAttribute QnABoardDto qnABoardDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        qnABoardService.save(qnABoardDto, principalDetail);
        return "redirect:/qna-board";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        try {
            qnABoardService.viewCountUp(id);
            model.addAttribute("qnaBoard", qnABoardService.detail(id));
        } catch (BoardNotFoundException | IsDeletedException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/qna-board/";
        }
        return "board/detail/qna-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("board", qnABoardService.detail(id));
        return "board/update/qna-update";
    }

    @PostMapping("/update.do")
    public String update(@ModelAttribute QnABoardDto qnABoardDto, RedirectAttributes redirectAttributes){
        qnABoardService.update(qnABoardDto);
        redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
        return "redirect:/qna-board/detail/"+qnABoardDto.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        qnABoardService.delete(id);
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/qna-board";
    }
}
