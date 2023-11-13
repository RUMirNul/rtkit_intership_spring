package ru.ansvistunov.rtkit_intership_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ansvistunov.rtkit_intership_spring.service.dto.StudentAndAverageGradeDto;
import ru.ansvistunov.rtkit_intership_spring.entity.StudentEntity;
import ru.ansvistunov.rtkit_intership_spring.entity.StudyGroupEntity;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    List<StudentEntity> findByGroup(StudyGroupEntity group);
    List<StudentEntity> findByNameAndFamilyNameAndGroup(String name, String familyName, StudyGroupEntity group);

    @Query(value = "SELECT NEW ru.ansvistunov.rtkit_intership_spring.service.dto.StudentAndAverageGradeDto(p.name, p.familyName, p.age, sg.groupName, AVG(g.grade)) " +
            "FROM StudentEntity p " +
            "JOIN p.group sg " +
            "LEFT JOIN p.grades g " +
            "WHERE sg.groupName = :groupNumber " +
            "GROUP BY p.id, p.name, p.familyName, p.age, sg.groupName")
    List<StudentAndAverageGradeDto> findAverageGradeInClass(@Param("groupNumber") int groupNumber);
}
