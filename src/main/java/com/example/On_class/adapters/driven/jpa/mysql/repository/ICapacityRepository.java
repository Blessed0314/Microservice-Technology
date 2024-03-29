package com.example.On_class.adapters.driven.jpa.mysql.repository;

import com.example.On_class.adapters.driven.jpa.mysql.entity.CapacityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ICapacityRepository extends JpaRepository<CapacityEntity, Long> {
    @Query("SELECT c FROM CapacityEntity c WHERE LOWER(TRIM(c.name)) = LOWER(TRIM(:name))")
    Optional<CapacityEntity> findByName(String name);

    @Query("SELECT c FROM CapacityEntity c LEFT JOIN c.technologies t GROUP BY c " +
            "ORDER BY CASE WHEN :ascendingFlag = true THEN SIZE(c.technologies) END ASC, " +
            "CASE WHEN :ascendingFlag = false THEN SIZE(c.technologies) END DESC")
    Page<CapacityEntity> findAllOrderByTechnologies(Pageable pagination, boolean ascendingFlag);

}
