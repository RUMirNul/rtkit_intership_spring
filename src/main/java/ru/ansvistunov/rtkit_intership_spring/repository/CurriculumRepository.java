package ru.ansvistunov.rtkit_intership_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ansvistunov.rtkit_intership_spring.entity.CurriculumEntity;

import java.util.List;

/**
 * Репозиторий для работы с учебными программами (учебными планами).
 */
public interface CurriculumRepository extends JpaRepository<CurriculumEntity, Integer> {
    /**
     * Находит все учебные программы для указанной группы.
     *
     * @param groupId Идентификатор группы.
     * @return Список учебных программ для указанной группы.
     */
    List<CurriculumEntity> findAllCurriculumByGroupId(int groupId);
}
