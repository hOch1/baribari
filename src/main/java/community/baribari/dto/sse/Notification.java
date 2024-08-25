package community.baribari.dto.sse;

import lombok.Getter;

@Getter
public enum Notification {

    NEW_ANSWER("새로운 답변이 등록되었습니다."),
    NEW_COMMENT("새로운 댓글이 등록되었습니다."),
    NEW_REPLY("새로운 답글이 등록되었습니다."),
    NEW_NOTE("새로운 쪽지가 도착했습니다."),

    ANSWER_ACCEPTED("답변이 채택되었습니다.");

    private final String message;

    Notification(String message) {
        this.message = message;
    }

}
