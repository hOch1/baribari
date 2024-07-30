package community.baribari.controller.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.exception.CustomException;
import community.baribari.service.star.BoardStarService;
import jakarta.servlet.http.HttpServletRequest;
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
                             @AuthenticationPrincipal PrincipalDetail principalDetail,
                             HttpServletRequest request){
        boardStarService.starCountUp(id, principalDetail);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }
}
