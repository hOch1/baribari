package community.baribari.entity.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private boolean postVisibility = true;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private boolean commentVisibility = true;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private boolean profileVisibility = true;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}