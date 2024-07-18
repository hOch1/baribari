package community.baribari.controller.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.exception.CommentNotFoundException;
import community.baribari.service.star.CommentStarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment/star")
public class CommentStarController {

    private final CommentStarService commentStarService;

    @PostMapping("/add/{boardId}/{id}")
    public String star(@PathVariable(value = "id") Long id,
                       @PathVariable(value = "boardId") Long boardId,
                       @RequestParam String boardName,
                       @AuthenticationPrincipal PrincipalDetail principalDetail,
                       RedirectAttributes redirectAttributes) {

        try {
            commentStarService.starCountUp(id, principalDetail);
        }catch (CommentNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/"+boardName;
        }

        return "redirect:/"+boardName+"/detail/"+boardId;
    }
}
