package community.baribari.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping(value = {"/", ""})
    public String main(){
        return "home";
    }

    @GetMapping("/write")
    public String write(){
        return "board-free-write";
    }


}
