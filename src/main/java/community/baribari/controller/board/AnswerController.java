package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.AnswerDto;
import community.baribari.exception.CustomException;
import community.baribari.service.board.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/{questionId}/write.do")
    public String answerWrite(@PathVariable Long questionId,
                              AnswerDto answerDto,
                              @AuthenticationPrincipal PrincipalDetail principalDetail,
                              RedirectAttributes redirectAttributes){
        try {
            answerService.writeAnswer(questionId, principalDetail, answerDto);
        } catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/qna-board/";
        }

        return "redirect:/qna-board/detail/" + questionId;
    }
}
