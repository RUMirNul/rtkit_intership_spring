package ru.ansvistunov.rtkit.intership.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ansvistunov.rtkit.intership.spring.entity.StudyGroupEntity;

/**
 * Репозиторий для работы с учебными группами.
 */
public interface StudyGroupRepository extends JpaRepository<StudyGroupEntity, Integer> {

}
