package com.example.socialhelp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storageFileName;
    private String originalFilename;
    private String type;
    private Long size;
    private String url;
//    @OneToOne
//    private User user;
//    @OneToOne
//    private AdditionalInfoForSpecialist additionalInfoForSpecialists;
}
