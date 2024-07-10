package community.baribari.controller.comment;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.comment.CommentDto;
import community.baribari.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardName}/{boardId}/write.do")
    public String writeComment(@PathVariable(value = "boardId") Long boardId,
                               @PathVariable(value = "boardName") String boardName,
                               @ModelAttribute CommentDto commentDto,
                               @AuthenticationPrincipal PrincipalDetail principalDetail) {

        commentService.addComment(commentDto, principalDetail, boardId);
        return "redirect:/"+boardName+"/detail/"+boardId;
    }

    @PostMapping("/star/{boardName}/{boardId}/{id}")
    public String star(@PathVariable(value = "id") Long id,
                       @PathVariable(value = "boardName") String boardName,
                       @PathVariable(value = "boardId") Long boardId,
                       @AuthenticationPrincipal PrincipalDetail principalDetail) {

        commentService.starCountUp(id, principalDetail);
        return "redirect:/"+boardName+"/detail/"+boardId;
    }
}
