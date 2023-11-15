package ru.ansvistunov.rtkit.intership.spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Сущность, представляющая студента (Person) в системе.
 */
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private StudyGroupEntity group;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<GradeEntity> grades;
}
