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
}
