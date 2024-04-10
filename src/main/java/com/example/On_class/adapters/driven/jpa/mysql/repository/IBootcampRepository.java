package com.example.On_class.adapters.driven.jpa.mysql.repository;

import com.example.On_class.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IBootcampRepository extends JpaRepository<BootcampEntity, Long> {
    @Query("SELECT c FROM CapacityEntity c WHERE LOWER(TRIM(c.name)) = LOWER(TRIM(:name))")
    Optional<BootcampEntity> findByName(String name);
}
