package community.baribari.entity.star;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswerStar is a Querydsl query type for AnswerStar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswerStar extends EntityPathBase<AnswerStar> {

    private static final long serialVersionUID = 231802910L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswerStar answerStar = new QAnswerStar("answerStar");

    public final community.baribari.entity.board.QAnswer answer;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final community.baribari.entity.member.QMember member;

    public QAnswerStar(String variable) {
        this(AnswerStar.class, forVariable(variable), INITS);
    }

    public QAnswerStar(Path<? extends AnswerStar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswerStar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswerStar(PathMetadata metadata, PathInits inits) {
        this(AnswerStar.class, metadata, inits);
    }

    public QAnswerStar(Class<? extends AnswerStar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answer = inits.isInitialized("answer") ? new community.baribari.entity.board.QAnswer(forProperty("answer"), inits.get("answer")) : null;
        this.member = inits.isInitialized("member") ? new community.baribari.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

