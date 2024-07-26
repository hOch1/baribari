package community.baribari.controller.board;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.board.AnswerDto;
import community.baribari.exception.CustomException;
import community.baribari.service.board.AnswerService;
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
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/{questionId}/write.do")
    public String answerWrite(@PathVariable("questionId") Long questionId,
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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes){
        try {
            answerService.delete(id);
            redirectAttributes.addFlashAttribute("message", "답변이 삭제되었습니다.");
        } catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute AnswerDto answerDto,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes){
        try {
            answerService.update(id, answerDto);
            redirectAttributes.addFlashAttribute("message", "답변이 수정되었습니다.");
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @PostMapping("/accept/{id}")
    public String accept(@PathVariable("id") Long id,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes){
        try {
            answerService.accept(id);
            redirectAttributes.addFlashAttribute("message", "답변이 채택되었습니다.");
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
