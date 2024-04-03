package com.example.on_class.adapters.driven.jpa.mysql.repository;

import com.example.on_class.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IBootcampRepository extends JpaRepository<BootcampEntity, Long> {
    @Query("SELECT c FROM CapacityEntity c WHERE LOWER(TRIM(c.name)) = LOWER(TRIM(:name))")
    Optional<BootcampEntity> findByName(String name);

    @Query("SELECT b FROM BootcampEntity b LEFT JOIN b.capacities c GROUP BY b " +
            "ORDER BY CASE WHEN :ascendingFlag = true THEN SIZE(b.capacities) END ASC, " +
            "CASE WHEN :ascendingFlag = false THEN SIZE(b.capacities) END DESC")
    Page<BootcampEntity> findAllOrderByCapacities(Pageable pagination, boolean ascendingFlag);

}
