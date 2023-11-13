package ru.ansvistunov.rtkit_intership_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Сущность, представляющая учебный план (Curriculum) для определенной учебной группы.
 */
@Entity
@Table(name = "curricula")
@Data
@NoArgsConstructor
public class CurriculumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curricula_id")
    private Integer id;

    @Column(name = "subject_name")
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private StudyGroupEntity group;
}
