package com.example.On_class.adapters.driven.jpa.mysql.repository;

import com.example.On_class.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITechnologyRepository extends JpaRepository<TechnologyEntity, Long>{
    @Query("SELECT t FROM TechnologyEntity t WHERE LOWER(TRIM(t.name)) = LOWER(TRIM(:name))")
    Optional<TechnologyEntity> findByName(String name);
}
