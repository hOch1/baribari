package community.baribari.controller.star;

import community.baribari.config.PrincipalDetail;
import community.baribari.entity.star.CommentStar;
import community.baribari.exception.CustomException;
import community.baribari.service.star.CommentStarService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment/star")
public class CommentStarController {

    private final CommentStarService commentStarService;

    @PostMapping("/add/{id}")
    public String star(@PathVariable(value = "id") Long id,
                       @AuthenticationPrincipal PrincipalDetail principalDetail,
                       RedirectAttributes redirectAttributes,
                       HttpServletRequest request) {
        try {
            commentStarService.starCountUp(id, principalDetail);
            String referer = request.getHeader("Referer");
            return "redirect:"+referer;
        }catch (CustomException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }
}
