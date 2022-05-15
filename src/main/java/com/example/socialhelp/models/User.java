package com.example.socialhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthday;
    private String city;
    private String email;
    private String gender;
    private String hashPassword;
    private boolean isEmailConfirmed;
    private double rate;
    private String avatarUrl;
    private boolean isSpecialist;

    @Column
    @Enumerated(value = EnumType.STRING)
    private BloodType bloodType;
    public enum BloodType {
        M1, P1, M2, P2, M3, P3, M4, P4
    }

    @Column
    @Enumerated(value = EnumType.STRING)
    private Role role;
    public enum Role {
        SIMPLE_USER, MODERATOR, ADMIN
    }

    @Enumerated(value = EnumType.STRING)
    private State state;
    public enum State {
        ACTIVE, BANNED
    }

    @OneToOne(mappedBy = "user")
    private AdditionalInfoForSpecialist additionalInfoForSpecialist;

    @OneToOne(mappedBy = "user")
    private Token token;

    public Boolean isActive() {
        return this.state == State.ACTIVE;
    }

}
