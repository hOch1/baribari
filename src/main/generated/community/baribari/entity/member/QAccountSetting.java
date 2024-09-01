package community.baribari.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountSetting is a Querydsl query type for AccountSetting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccountSetting extends EntityPathBase<AccountSetting> {

    private static final long serialVersionUID = 76641161L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountSetting accountSetting = new QAccountSetting("accountSetting");

    public final BooleanPath commentVisibility = createBoolean("commentVisibility");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final BooleanPath noteBlock = createBoolean("noteBlock");

    public final BooleanPath postVisibility = createBoolean("postVisibility");

    public final BooleanPath profileVisibility = createBoolean("profileVisibility");

    public QAccountSetting(String variable) {
        this(AccountSetting.class, forVariable(variable), INITS);
    }

    public QAccountSetting(Path<? extends AccountSetting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccountSetting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccountSetting(PathMetadata metadata, PathInits inits) {
        this(AccountSetting.class, metadata, inits);
    }

    public QAccountSetting(Class<? extends AccountSetting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

