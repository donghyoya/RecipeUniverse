package com.recipe.universe.domain.chatgpt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatInfo is a Querydsl query type for ChatInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatInfo extends EntityPathBase<ChatInfo> {

    private static final long serialVersionUID = 1379106210L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatInfo chatInfo = new QChatInfo("chatInfo");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    public final NumberPath<Integer> chatCnt = createNumber("chatCnt", Integer.class);

    public final ListPath<Chatting, QChatting> chattingList = this.<Chatting, QChatting>createList("chattingList", Chatting.class, QChatting.class, PathInits.DIRECT2);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> latestChat = createDateTime("latestChat", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> remainValue = createNumber("remainValue", Integer.class);

    public final com.recipe.universe.domain.user.user.entity.QUser user;

    public QChatInfo(String variable) {
        this(ChatInfo.class, forVariable(variable), INITS);
    }

    public QChatInfo(Path<? extends ChatInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatInfo(PathMetadata metadata, PathInits inits) {
        this(ChatInfo.class, metadata, inits);
    }

    public QChatInfo(Class<? extends ChatInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.recipe.universe.domain.user.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

