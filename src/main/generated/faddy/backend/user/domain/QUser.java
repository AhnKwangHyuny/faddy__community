package faddy.backend.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 400311260L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final faddy.backend.global.QBaseEntity _super = new faddy.backend.global.QBaseEntity(this);

    public final EnumPath<Authority> authority = createEnum("authority", Authority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final StringPath email = createString("email");

    public final ListPath<faddy.backend.follows.domain.Follow, faddy.backend.follows.domain.QFollow> followers = this.<faddy.backend.follows.domain.Follow, faddy.backend.follows.domain.QFollow>createList("followers", faddy.backend.follows.domain.Follow.class, faddy.backend.follows.domain.QFollow.class, PathInits.DIRECT2);

    public final ListPath<faddy.backend.follows.domain.Follow, faddy.backend.follows.domain.QFollow> followings = this.<faddy.backend.follows.domain.Follow, faddy.backend.follows.domain.QFollow>createList("followings", faddy.backend.follows.domain.Follow.class, faddy.backend.follows.domain.QFollow.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final QProfile profile;

    public final ListPath<faddy.backend.snap.domain.Snap, faddy.backend.snap.domain.QSnap> snaps = this.<faddy.backend.snap.domain.Snap, faddy.backend.snap.domain.QSnap>createList("snaps", faddy.backend.snap.domain.Snap.class, faddy.backend.snap.domain.QSnap.class, PathInits.DIRECT2);

    public final EnumPath<UserStatus> status = createEnum("status", UserStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public final StringPath username = createString("username");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile")) : null;
    }

}

