package com.recipe.universe.domain.user.user.entity;

import com.recipe.universe.domain.user.role.entity.Role;
import com.recipe.universe.domain.user.role.entity.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Table(name = "users")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles;

    public void addRole(UserRole userRole){
        roles.add(userRole);
    }

    /* 생성 관련 */

    protected User(String userId, String pwd, String email, String provider) {
        this.userId = userId;
        this.pwd = pwd;
        this.email = email;
        this.provider = provider;
        this.roles = new ArrayList<>();
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