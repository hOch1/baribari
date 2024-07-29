package community.baribari.entity.note;

import community.baribari.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member send;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member receive;

    @Builder.Default
    private boolean read = false;
    
}
