package community.baribari.controller.admin.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class ABoardController {

    @GetMapping(value = {"", "/"})
    public String index(){
        return "admin/board/index";
    }
}
