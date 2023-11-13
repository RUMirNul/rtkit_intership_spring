package ru.ansvistunov.rtkit_intership_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая учебную группу в системе.
 */
@Entity
@Table(name = "studygroups")
@Data
@NoArgsConstructor
public class StudyGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer id;

    @Column(name = "group_name")
    private Integer groupName;
}
