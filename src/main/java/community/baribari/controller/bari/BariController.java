package community.baribari.controller.bari;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bari")
public class BariController {

    @GetMapping("/recruit")
    public String recruit(Model model) {
        return "bari/bari-recruit";
    }

    @GetMapping("/review")
    public String review(Model model) {
        return "bari/bari-review";
    }
}
