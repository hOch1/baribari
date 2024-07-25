package community.baribari.controller.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.board.Category;
import community.baribari.entity.star.CommentStar;
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
                       @AuthenticationPrincipal PrincipalDetail principalDetail,
                       RedirectAttributes redirectAttributes) {
        try {
            CommentStar star = commentStarService.starCountUp(id, principalDetail);
            String boardName = getBoardName(star.getComment().getBoard().getCategory());
            return "redirect:/"+boardName+"/detail/"+boardId;
        }catch (CommentNotFoundException | IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    public String getBoardName(Category category){
        switch (category){
            case FREE :
                return "free-board";
            case REVIEW :
                return "bari-review";
            case RECRUIT :
                return "bari-recruit";
            default:
                return "/";
        }
    }}
