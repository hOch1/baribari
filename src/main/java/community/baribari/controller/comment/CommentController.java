package community.baribari.controller.comment;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.comment.CommentDto;
import community.baribari.entity.comment.Comment;
import community.baribari.exception.CustomException;
import community.baribari.service.comment.CommentService;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/{boardId}/writeComment.do")
    public String writeComment(@PathVariable(value = "boardId") Long boardId,
                               @ModelAttribute CommentDto commentDto,
                               @AuthenticationPrincipal PrincipalDetail principalDetail,
                               RedirectAttributes redirectAttributes,
                               HttpServletRequest request) {

        try {
            commentService.addComment(commentDto, principalDetail, boardId);
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id,
                         RedirectAttributes redirectAttributes,
                         HttpServletRequest request) {
        try {
            Comment comment = commentService.delete(id);
            redirectAttributes.addFlashAttribute("message", " 삭제되었습니다.");
            String referer = request.getHeader("Referer");
            return "redirect:"+referer;
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/{commentId}/writeReply.do")
    public String writeReply(@ModelAttribute CommentDto commentDto,
                             @PathVariable(value = "commentId") Long commentId,
                             @AuthenticationPrincipal PrincipalDetail principalDetail,
                             HttpServletRequest request){
        commentService.addReply(commentDto, commentId, principalDetail);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

}
