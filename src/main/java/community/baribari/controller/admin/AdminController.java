package community.baribari.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value = {"", "/"})
    public String admin(){
        return "admin/index";
    }


    @GetMapping("/board")
    public String board(){
        return "#";
    }


}
