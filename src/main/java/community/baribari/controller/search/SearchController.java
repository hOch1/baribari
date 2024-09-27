package community.baribari.controller.search;

import community.baribari.dto.search.SearchRequest;
import community.baribari.service.search.SearchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/all")
    public String searchAll(@ModelAttribute @Valid SearchRequest searchRequest,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            Model model) {
        model.addAttribute("results", searchService.searchAll(searchRequest, PageRequest.of(page, 10)));
        return "search/all";
    }
}
