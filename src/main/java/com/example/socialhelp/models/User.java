package com.example.socialhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String age;

    private String hashPassword;

    private String confirmCode;

    @ManyToMany(mappedBy = "users")
    private List<Token> tokens;

    private double rating;

    private String gender;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN, MODER, EXPERT
    }
    public enum Confirm{
        CONFIRM, NOT_CONFIRM
    }

    @Enumerated(value = EnumType.STRING)
    private Confirm confirm;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

}
