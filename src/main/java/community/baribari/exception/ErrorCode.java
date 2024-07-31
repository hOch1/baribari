package community.baribari.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // NOTFOUND
    BOARD_NOT_FOUND("해당 게시물을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND("해당 댓글을 찾을 수 없습니다."),
    MEMBER_NOT_FOUND("해당 회원을 찾을 수 없습니다."),
    ANSWER_NOT_FOUND("해당 답변을 찾을 수 없습니다."),
    NOTE_NOT_FOUND("해당 쪽지를 찾을 수 없습니다."),

    // ANSWER
    ANSWER_ACCEPTED("채택된 답변은 수정하거나, 삭제할 수 없습니다."),
    ANSWER_ALREADY_ACCEPTED("이미 채택된 답변이 있습니다."),

    // STAR
    ALREADY_STARRED_BOARD("이미 추천한 게시물입니다."),
    ALREADY_STARRED_COMMENT("이미 추천한 댓글입니다."),
    ALREADY_STARRED_ANSWER("이미 추천한 답변입니다."),

    // DELETE
    DELETED_BOARD("삭제된 게시물입니다."),
    DELETED_COMMENT("삭제된 댓글입니다."),
    DELETED_MEMBER("삭제된 회원입니다."),

    // SIGNUP
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다."),
    ALREADY_EXIST_NICKNAME("이미 존재하는 닉네임입니다."),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),

    // AUTH
    UNAUTHORIZED("권한이 없습니다."),

    // BLOCK
    NOTE_BLOCK("쪽지를 차단한 회원입니다."),

    INVALID_INPUT("올바르지 않은 입력입니다."),
    INTERNAL_SERVER_ERROR("서버 오류입니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}

