package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.dto.comment.CommentDto;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.service.comment.CommentService;
import community.baribari.service.board.FreeBoardService;
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
@RequestMapping("/free-board")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;
    private final CommentService commentService;

    @GetMapping(value = {"", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page){

        Page<FreeBoardDto> list = freeBoardService.list(PageRequest.of(page, 10));

        model.addAttribute("freeBoards", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("totalElements", list.getTotalElements());
        return "board/free-board";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new FreeBoardDto());
        return "board/write/free-write";
    }

    @PostMapping("/write.do")
    public String write(@ModelAttribute FreeBoardDto freeBoardDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        freeBoardService.save(freeBoardDto, principalDetail);
        return "redirect:/free-board";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model,
                         RedirectAttributes redirectAttributes){
        try {
            freeBoardService.viewCountUp(id);
            model.addAttribute("freeBoard", freeBoardService.detail(id));
            model.addAttribute("comments", commentService.list(id));
            model.addAttribute("comment", new CommentDto());
        } catch (BoardNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/free-board/";
        }
        return "board/detail/free-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("board", freeBoardService.detail(id));
        return "board/update/free-update";
    }

    @PostMapping("/update.do")
    public String update(@ModelAttribute FreeBoardDto freeBoardDto){
        freeBoardService.update(freeBoardDto);
        return "redirect:/free-board/detail/"+freeBoardDto.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return "redirect:/free-board";
    }
}
