package com.example.socialhelp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties("specialization")
@Table(name = "additional_info_for_specialist")
public class AdditionalInfoForSpecialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isConfirmedOnPlatform;
    private String qualification;
    private String education;
    private String qualification_improvement;
    private int experience;

    @OneToOne
    @JoinColumn(name = "specialist_id")
    private User user;
    @ManyToOne
    private Specialization specialization;
    @ManyToOne
    private PhotoDocuments photoDocuments;
//    @OneToOne(mappedBy = "additionalInfoForSpecialists")
//    private File file;
}
