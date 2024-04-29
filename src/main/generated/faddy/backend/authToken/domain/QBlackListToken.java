package faddy.backend.authToken.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlackListToken is a Querydsl query type for BlackListToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlackListToken extends EntityPathBase<BlackListToken> {

    private static final long serialVersionUID = -764745633L;

    public static final QBlackListToken blackListToken = new QBlackListToken("blackListToken");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath invalidRefreshToken = createString("invalidRefreshToken");

    public QBlackListToken(String variable) {
        super(BlackListToken.class, forVariable(variable));
    }

    public QBlackListToken(Path<? extends BlackListToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlackListToken(PathMetadata metadata) {
        super(BlackListToken.class, metadata);
    }

}

