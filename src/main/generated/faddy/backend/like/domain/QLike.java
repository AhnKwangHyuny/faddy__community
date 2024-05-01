package faddy.backend.like.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLike is a Querydsl query type for Like
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLike extends EntityPathBase<Like> {

    private static final long serialVersionUID = -709854860L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLike like = new QLike("like1");

    public final faddy.backend.global.QBaseEntity _super = new faddy.backend.global.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final faddy.backend.snap.domain.QSnap snap;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public final faddy.backend.user.domain.QUser user;

    public QLike(String variable) {
        this(Like.class, forVariable(variable), INITS);
    }

    public QLike(Path<? extends Like> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLike(PathMetadata metadata, PathInits inits) {
        this(Like.class, metadata, inits);
    }

    public QLike(Class<? extends Like> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.snap = inits.isInitialized("snap") ? new faddy.backend.snap.domain.QSnap(forProperty("snap"), inits.get("snap")) : null;
        this.user = inits.isInitialized("user") ? new faddy.backend.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

