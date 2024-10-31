package com.recipe.universe.domain.chatgpt.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.user.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ChatInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer chatCnt;
    private Integer remainValue;
    private LocalDateTime latestChat;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;


    @OneToMany(mappedBy = "chatInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Chatting> chattingList = new ArrayList<>();
}
