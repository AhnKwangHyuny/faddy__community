package faddy.backend.auth.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSocialLogin is a Querydsl query type for SocialLogin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSocialLogin extends EntityPathBase<SocialLogin> {

    private static final long serialVersionUID = -1136876082L;

    public static final QSocialLogin socialLogin = new QSocialLogin("socialLogin");

    public final faddy.backend.global.QBaseEntity _super = new faddy.backend.global.QBaseEntity(this);

    public final StringPath accessToken = createString("accessToken");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    public final StringPath externalId = createString("externalId");

    public final NumberPath<Byte> socialCode = createNumber("socialCode", Byte.class);

    public final NumberPath<Integer> socialLoginId = createNumber("socialLoginId", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updated_at = _super.updated_at;

    public QSocialLogin(String variable) {
        super(SocialLogin.class, forVariable(variable));
    }

    public QSocialLogin(Path<? extends SocialLogin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSocialLogin(PathMetadata metadata) {
        super(SocialLogin.class, metadata);
    }

}

