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

    private String specializations;

    @ManyToMany(mappedBy = "specializations")
    private List<Сategory> categories;

    public enum Specializations {
        Pediatrician, Therapist, Gynecologist, Endocrinologist, Dermatologist, Allergist_Immunologist, Gastroenterologist, Psychologist, Cosmetologist, Urologist, Neurologist, Venereologist, Veterinarian, Cardiologist, ENT, Ophthalmologist, Oncologist, Traumatologist, Dentist
    }


}
