package community.baribari.controller.comment;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.comment.CommentDto;
import community.baribari.entity.board.Category;
import community.baribari.entity.comment.Comment;
import community.baribari.exception.BoardNotFoundException;
import community.baribari.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardName}/{boardId}/write.do")
    public String writeComment(@PathVariable(value = "boardId") Long boardId,
                               @PathVariable(value = "boardName") String boardName,
                               @ModelAttribute CommentDto commentDto,
                               @AuthenticationPrincipal PrincipalDetail principalDetail,
                               RedirectAttributes redirectAttributes) {

        try {
            commentService.addComment(commentDto, principalDetail, boardId);
        }catch (BoardNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/" + boardName + "/detail/" + boardId;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id,
                         RedirectAttributes redirectAttributes) {
        try {
            Comment comment = commentService.delete(id);
            redirectAttributes.addFlashAttribute("message", " 삭제되었습니다.");
            String boardName = getBoardName(comment.getBoard().getCategory());
            return "redirect:/" + boardName + "/detail/" + comment.getBoard().getId();
        }catch (BoardNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/{commentId}/write.do")
    public String writeReply(@ModelAttribute CommentDto commentDto,
                             @PathVariable(value = "commentId") Long commentId,
                             @AuthenticationPrincipal PrincipalDetail principalDetail){
        Comment comment = commentService.addReply(commentDto, commentId, principalDetail);
        String boardName = getBoardName(comment.getBoard().getCategory());
        return "redirect:/" + boardName + "/detail/" + comment.getBoard().getId();
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
    }

}
