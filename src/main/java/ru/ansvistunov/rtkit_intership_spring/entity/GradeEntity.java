package ru.ansvistunov.rtkit_intership_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "grades")
@Data
@NoArgsConstructor
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Integer id;

    @Column(name = "grade")
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "curricula_id")
    @ToString.Exclude
    private CurriculumEntity curriculum;
}
