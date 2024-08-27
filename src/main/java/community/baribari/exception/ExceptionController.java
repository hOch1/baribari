package community.baribari.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public String customException(CustomException e,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletRequest request){
        redirectAttributes.addFlashAttribute("message", e.getMessage());
        return "redirect:"+request.getHeader("Referer");
    }

    /**
     * @Valid 유효성 검사 실패 시 발생하는 예외 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(MethodArgumentNotValidException e,
                                                  RedirectAttributes redirectAttributes,
                                                  HttpServletRequest request){
        redirectAttributes.addFlashAttribute("message", e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage());
        return "redirect:"+request.getHeader("Referer");
    }

    /**
     * @Validated 유효성 검사 실패 시 발생하는 예외 처리
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public String handlerMethodValidationException(HandlerMethodValidationException e,
                                                   RedirectAttributes redirectAttributes,
                                                   HttpServletRequest request){
        redirectAttributes.addFlashAttribute("message", e.getAllErrors()
                .get(0)
                .getDefaultMessage());
        return "redirect:"+request.getHeader("Referer");
    }

}
