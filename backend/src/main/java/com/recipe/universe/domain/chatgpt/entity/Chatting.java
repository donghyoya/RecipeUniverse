package com.recipe.universe.domain.chatgpt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatInfoId", nullable = false)
    private ChatInfo chatInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "sender")
    private Sender sender;
    private String message;
}
