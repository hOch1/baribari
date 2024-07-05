package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.FreeBoardDto;
import community.baribari.service.board.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/free-board")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

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
    public String write(FreeBoardDto freeBoardDto,
                        @AuthenticationPrincipal PrincipalDetail principalDetail){
        freeBoardService.save(freeBoardDto, principalDetail);
        return "redirect:/free-board";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        freeBoardService.viewCountUp(id);
        model.addAttribute("freeBoard", freeBoardService.detail(id));
        return "board/detail/free-detail";
    }

    @PostMapping("/star/{id}")
    public String star(@PathVariable Long id,
                       @AuthenticationPrincipal PrincipalDetail principalDetail){
        freeBoardService.starCountUp(id, principalDetail);

        return "redirect:/free-board/detail/" + id;
    }
}
