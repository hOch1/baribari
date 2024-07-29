package community.baribari.controller.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.exception.CustomException;
import community.baribari.service.star.AnswerStarService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/answer/star")
@RequiredArgsConstructor
public class AnswerStarController {

    private final AnswerStarService answerStarService;

    @PostMapping("/add/{id}")
    public String answerStar (@PathVariable("id") Long id,
                             @AuthenticationPrincipal PrincipalDetail principalDetail,
                             HttpServletRequest request){

        answerStarService.starCountUp(id, principalDetail);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }
}
