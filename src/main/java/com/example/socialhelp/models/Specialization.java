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
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "specialization")
    private List<AdditionalInfoForSpecialist> additionalInfoForSpecialists;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



    private String specializationType;

    public enum SpecializationType {
        Pediatrician, Therapist, Gynecologist, Dermatologist, Gastroenterologist, Psychologist, Cosmetologist, Urologist, Neurologist, Venereologist, Veterinarian, Cardiologist, LOR, Ophthalmologist, Oncologist, Traumatologist, Dentist, AllergistImmunologist, Endocrinologist
    }


}
