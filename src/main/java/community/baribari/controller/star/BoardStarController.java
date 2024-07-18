package community.baribari.controller.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.service.star.BoardStarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/star")
@RequiredArgsConstructor
public class BoardStarController {

    private final BoardStarService boardStarService;

    @PostMapping("/add/{id}")
    public String boardStar (@PathVariable Long id,
                             @RequestParam String boardName,
                             @AuthenticationPrincipal PrincipalDetail principalDetail,
                             RedirectAttributes redirectAttributes){
        try {
            boardStarService.starCountUp(id, principalDetail);
        }catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }catch (BoardNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/"+boardName;
        }
        return "redirect:/"+boardName+"/detail/" + id;
    }
}
