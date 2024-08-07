package community.baribari.controller.note;

import community.baribari.config.PrincipalDetail;
import community.baribari.dto.note.NoteDto;
import community.baribari.service.note.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping(value = {"", "/"})
    public String noteBox(@AuthenticationPrincipal PrincipalDetail principalDetail,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          Model model){
        model.addAttribute("notes", noteService.getNotes(principalDetail, PageRequest.of(page, 10)));
        return "note/note";
    }

    @GetMapping("/send/{id}")
    public String sendNote(@PathVariable("id") Long receiveId,
                           Model model){
        model.addAttribute("receive", noteService.isBlock(receiveId));
        return "note/note-write";
    }

    @PostMapping("/send.do")
    public String sendNoteDo(@AuthenticationPrincipal PrincipalDetail principalDetail,
                             @RequestParam("id") Long receiveId,
                             @ModelAttribute @Valid NoteDto noteDto,
                             RedirectAttributes redirectAttributes){
        noteService.sendNote(principalDetail, receiveId, noteDto);
        redirectAttributes.addFlashAttribute("message", "쪽지를 보냈습니다.");
        return "redirect:/note";
    }

    @GetMapping("/detail/{id}")
    public String readNote(@PathVariable("id") Long noteId,
                           Model model){
        model.addAttribute("note", noteService.readNote(noteId));
        return "note/detail";
    }

    @PostMapping("/reply/{id}")
    public String replyNote(@AuthenticationPrincipal PrincipalDetail principalDetail,
                            @PathVariable("id") Long sendId,
                            @ModelAttribute @Valid NoteDto noteDto,
                            RedirectAttributes redirectAttributes){
        noteService.sendNote(principalDetail, sendId, noteDto);
        redirectAttributes.addFlashAttribute("message", "답장을 보냈습니다.");
        return "redirect:/note";
    }

}
