package faddy.backend.follows.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollow is a Querydsl query type for Follow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFollow extends EntityPathBase<Follow> {

    private static final long serialVersionUID = -328018491L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollow follow = new QFollow("follow");

    public final faddy.backend.global.QBaseEntity _super = new faddy.backend.global.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final faddy.backend.user.domain.QUser followed;

    public final faddy.backend.user.domain.QUser follower;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<faddy.backend.follows.types.FollowStatus> status = createEnum("status", faddy.backend.follows.types.FollowStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public QFollow(String variable) {
        this(Follow.class, forVariable(variable), INITS);
    }

    public QFollow(Path<? extends Follow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollow(PathMetadata metadata, PathInits inits) {
        this(Follow.class, metadata, inits);
    }

    public QFollow(Class<? extends Follow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.followed = inits.isInitialized("followed") ? new faddy.backend.user.domain.QUser(forProperty("followed"), inits.get("followed")) : null;
        this.follower = inits.isInitialized("follower") ? new faddy.backend.user.domain.QUser(forProperty("follower"), inits.get("follower")) : null;
    }

}

