package com.example.on_class.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "version")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quota;
    private LocalDate initialDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "bootcamp_id", referencedColumnName = "id")
    private BootcampEntity bootcamp;
}
