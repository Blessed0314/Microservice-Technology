package com.example.On_class.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bootcamp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BootcampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "bootcamp_capacity",
            joinColumns = @JoinColumn(name = "bootcamp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capacity_id", referencedColumnName = "id")
    )
    private List<CapacityEntity> capacities = new ArrayList<>();
}
