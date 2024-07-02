package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.service.board.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/free-board")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping(value = {"", "/"})
    public String index(Model model){
        model.addAttribute("freeBoards", freeBoardService.list());
        return "board/free-board";
    }

    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("write", new FreeBoardDto());
        return "board/write/free-write";
    }

    @PostMapping("/write.do")
    public String write(FreeBoardDto freeBoardDto, PrincipalDetail principalDetail){
        freeBoardService.save(freeBoardDto, principalDetail);
        return "redirect:/free-board";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        freeBoardService.viewCountUp(id);
        model.addAttribute("freeBoard", freeBoardService.detail(id));
        return "board/detail/free-detail";
    }
}
