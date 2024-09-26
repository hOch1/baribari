package community.baribari.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswer is a Querydsl query type for Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = 1676718452L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswer answer = new QAnswer("answer");

    public final BooleanPath accepted = createBoolean("accepted");

    public final ListPath<community.baribari.entity.star.AnswerStar, community.baribari.entity.star.QAnswerStar> answerStars = this.<community.baribari.entity.star.AnswerStar, community.baribari.entity.star.QAnswerStar>createList("answerStars", community.baribari.entity.star.AnswerStar.class, community.baribari.entity.star.QAnswerStar.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final community.baribari.entity.member.QMember member;

    public final QQnABoard qnaBoard;

    public QAnswer(String variable) {
        this(Answer.class, forVariable(variable), INITS);
    }

    public QAnswer(Path<? extends Answer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswer(PathMetadata metadata, PathInits inits) {
        this(Answer.class, metadata, inits);
    }

    public QAnswer(Class<? extends Answer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new community.baribari.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
        this.qnaBoard = inits.isInitialized("qnaBoard") ? new QQnABoard(forProperty("qnaBoard"), inits.get("qnaBoard")) : null;
    }

}

