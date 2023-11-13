package ru.ansvistunov.rtkit_intership_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ansvistunov.rtkit_intership_spring.entity.CurriculumEntity;

import java.util.List;

public interface CurriculumRepository extends JpaRepository<CurriculumEntity, Integer> {
    List<CurriculumEntity> findAllCurriculumByGroupId(int groupId);
}
