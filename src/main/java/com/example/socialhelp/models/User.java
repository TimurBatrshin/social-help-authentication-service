package com.example.socialhelp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties("additionalInfoForSpecialist")
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
    @Column
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @OneToOne(mappedBy = "user")
    private AdditionalInfoForSpecialist additionalInfoForSpecialist;
    @OneToOne(mappedBy = "user")
    private Token token;
    @OneToMany(mappedBy = "user")
    private List<TimeTable> timeTables_user;
    @OneToMany(mappedBy = "specialist")
    private List<TimeTable> timeTables_specialist;
    @OneToOne(mappedBy = "user")
    private File file;

    public enum BloodType {
        M1, P1, M2, P2, M3, P3, M4, P4
    }

    public enum Role {
        SIMPLE_USER, MODERATOR, ADMIN
    }

    public enum State {
        ACTIVE, BANNED
    }

    public Boolean isActive() {
        return this.state == State.ACTIVE;
    }
}
