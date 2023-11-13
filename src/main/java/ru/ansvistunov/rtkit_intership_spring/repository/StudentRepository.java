package ru.ansvistunov.rtkit_intership_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ansvistunov.rtkit_intership_spring.entity.StudentEntity;
import ru.ansvistunov.rtkit_intership_spring.service.dto.StudentAndAverageGradeDto;

import java.util.List;

/**
 * Репозиторий для работы со студентами.
 */
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    /**
     * Находит средние оценки студентов в указанной группе.
     *
     * @param groupNumber Номер группы.
     * @return Список DTO, представляющих студента и среднюю оценку в указанной группе.
     */
    @Query(value = "SELECT NEW ru.ansvistunov.rtkit_intership_spring.service.dto.StudentAndAverageGradeDto(p.name, p.familyName, p.age, sg.groupName, AVG(g.grade)) " +
            "FROM StudentEntity p " +
            "JOIN p.group sg " +
            "LEFT JOIN p.grades g " +
            "WHERE sg.groupName = :groupNumber " +
            "GROUP BY p.id, p.name, p.familyName, p.age, sg.groupName")
    List<StudentAndAverageGradeDto> findAverageGradeInClass(@Param("groupNumber") int groupNumber);
}
