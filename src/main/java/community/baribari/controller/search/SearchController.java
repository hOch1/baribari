package community.baribari.controller.search;

import community.baribari.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/all")
    public String searchAll(@RequestParam("keyword") String keyword,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            Model model) {
        model.addAttribute("results", searchService.searchAll(keyword, PageRequest.of(page, 10)));
        return "search/all";
    }
}
