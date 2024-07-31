package community.baribari.dto.note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteBoxDto {

    private Page<NoteDto> allNotes;
    private Page<NoteDto> unreadNotes;
    private Page<NoteDto> receiveNotes;
    private Page<NoteDto> sendNotes;

}
