package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.dto.comment.CommentDto;
import community.baribari.exception.CustomException;
import community.baribari.exception.ErrorCode;
import community.baribari.service.comment.CommentService;
import community.baribari.service.board.extend.FreeBoardService;
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
@RequestMapping("/free-board")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;
    private final CommentService commentService;

    @GetMapping(value = {"", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "0", value = "page") int page){
        model.addAttribute("freeBoards", freeBoardService.list(PageRequest.of(page, 10)));
        return "board/free-board";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new FreeBoardDto());
        return "board/write/free-write";
    }

    @PostMapping("/write.do")
    public String write(@ModelAttribute @Valid FreeBoardDto freeBoardDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        freeBoardService.save(freeBoardDto, principalDetail);
        return "redirect:/free-board";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        freeBoardService.viewCountUp(id);
        model.addAttribute("board", freeBoardService.detail(id));
        model.addAttribute("comments", commentService.list(id));
        model.addAttribute("comment", new CommentDto());
        return "board/detail/free-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model,
                         @AuthenticationPrincipal PrincipalDetail principalDetail){

        FreeBoardDto dto = freeBoardService.detail(id);

        if (!principalDetail.getMember().getId().equals(dto.getMember().getId()))
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        model.addAttribute("board", dto);
        return "board/update/free-update";
    }

    @PostMapping("/update.do")
    public String update(@ModelAttribute @Valid FreeBoardDto freeBoardDto,
                         RedirectAttributes redirectAttributes){
        freeBoardService.update(freeBoardDto);
        redirectAttributes.addFlashAttribute("message", "수정되었습니다.");
        return "redirect:/free-board/detail/"+freeBoardDto.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes){
        freeBoardService.delete(id);
        redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        return "redirect:/free-board";
    }
}
