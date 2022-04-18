package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Сategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface СategoryRepository extends JpaRepository<Сategory, Long> {

    List<Сategory> findAllByCategory(String name);

}
