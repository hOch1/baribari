package community.baribari.entity.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickname;
    private String email;
    private String password;

    @Builder.Default
    private boolean deleted = false;

    @Builder.Default
    private boolean isSocial = false;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "member")
    private AccountSetting accountSetting;

    public Member update(String name){
        this.name = name;
        return this;
    }

    public Member updateNickname(String nickname){
        this.nickname = nickname;
        return this;
    }

}
