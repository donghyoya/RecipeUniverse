package com.recipe.universe.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false)
    private String userId;
    private String pwd;
    private String email;
    private String provider;

    protected User(String userId, String pwd, String email, String provider) {
        this.userId = userId;
        this.pwd = pwd;
        this.email = email;
        this.provider = provider;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String userId;
        private String pwd;
        private String email;
        private String provider;

        public User build(){
            return new User(userId, pwd, email, provider);
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder pwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }
    }
}
