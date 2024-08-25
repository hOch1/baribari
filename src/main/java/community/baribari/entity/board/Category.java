package community.baribari.entity.board;

import lombok.Getter;

@Getter
public enum Category {

    FREE("/free-board"),
    QNA("/qna-board"),
    RECRUIT("/bari-recruit"),
    REVIEW("/bari-review");

    private final String path;

    Category(String path) {
        this.path = path;
    }
}
