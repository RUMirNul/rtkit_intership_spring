package ru.ansvistunov.rtkit_intership_spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ansvistunov.rtkit_intership_spring.entity.GradeEntity;
import ru.ansvistunov.rtkit_intership_spring.service.dto.GradeDto;
import ru.ansvistunov.rtkit_intership_spring.service.dto.UpdateGradeDto;
import ru.ansvistunov.rtkit_intership_spring.web.request.UpdateGradeRequest;

@Mapper(componentModel = "spring", uses = {GradeMapperUtil.class})
public interface GradeMapper {

//    StudentUpdateGradeDto studentUpdateGradeRequestToStudentUpdateGradeDto(StudentUpdateGradeRequest source);

    @Mapping(target = "studentId", source = "source.student.id")
    @Mapping(target = "curriculumId", source = "source.curriculum.id")
    GradeDto gradeEntityToGradeDto(GradeEntity source);

    @Mapping(target = "student", source = "studentId", qualifiedBy = GradeMapperUtil.StudentEntityByStudentId.class)
    @Mapping(target = "curriculum", source = "curriculumId", qualifiedBy = GradeMapperUtil.CurriculumEntityByCurriculumId.class)
    GradeEntity gradeDtoToGradeEntity(GradeDto source);

    UpdateGradeDto updateGradeRequestToUpdateGradeDto(UpdateGradeRequest source);
}
