package ru.ansvistunov.rtkit_intership_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ansvistunov.rtkit_intership_spring.entity.StudyGroupEntity;

/**
 * Репозиторий для работы с учебными группами.
 */
public interface StudyGroupRepository extends JpaRepository<StudyGroupEntity, Integer> {

}
