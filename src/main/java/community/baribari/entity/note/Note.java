package community.baribari.entity.note;

import community.baribari.dto.note.NoteDto;
import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member send;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member receive;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private boolean isRead = false;

    public void read(){
        this.isRead = true;
    }

    public static Note toEntity(NoteDto noteDto, Member send, Member receive){
        return Note.builder()
                .title(noteDto.getTitle())
                .content(noteDto.getContent())
                .send(send)
                .receive(receive)
                .build();
    }

}
