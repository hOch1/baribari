package community.baribari.config;

import community.baribari.entity.Member;
import community.baribari.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class OAuth2Attributes {
    
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (registrationId.equals("naver"))
            return ofNaver("id", attributes);
        else if (registrationId.equals("kakao"))
            return ofKakao("id", attributes);
        else
            return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2Attributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Attributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuth2Attributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return OAuth2Attributes.builder()
                .name((String) profile.get("nickname"))
                .email((String) account.get("email"))
                .attributes(account)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .role(Role.ROLE_MEMBER)
                .build();
    }
}
