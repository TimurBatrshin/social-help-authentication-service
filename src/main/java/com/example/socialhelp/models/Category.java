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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private CategoryType categoryType;
    public enum CategoryType {
        medicine
    }

    @OneToMany(mappedBy = "category")
    private List<Specialization> specializations;

}
