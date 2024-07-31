package community.baribari.dto.note;

import community.baribari.dto.member.MemberDto;
import community.baribari.entity.note.Note;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 20, message = "제목은 20자 이내로 작성해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 100, message = "내용은 100자 이내로 작성해주세요.")
    private String content;

    private MemberDto send;
    private MemberDto receive;
    private boolean read;
    private LocalDateTime createdAt;

    public static NoteDto toDto(Note note){
        return NoteDto.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .send(MemberDto.toDto(note.getSend()))
                .receive(MemberDto.toDto(note.getReceive()))
                .read(note.isRead())
                .createdAt(note.getCreatedAt())
                .build();
    }
}
