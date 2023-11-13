package ru.ansvistunov.rtkit_intership_spring.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ansvistunov.rtkit_intership_spring.entity.StudentEntity;
import ru.ansvistunov.rtkit_intership_spring.service.dto.StudentDto;
import ru.ansvistunov.rtkit_intership_spring.service.dto.StudentUpdateStudyGroupDto;
import ru.ansvistunov.rtkit_intership_spring.web.request.StudentAddRequest;
import ru.ansvistunov.rtkit_intership_spring.web.request.StudentUpdateStudyGroupRequest;

@Mapper(componentModel = "spring", uses = {StudentMapperUtil.class})
public interface StudentMapper {

    StudentDto addStudentRequestToPersonDto(StudentAddRequest source);

    @Mapping(target = "groupId", source = "source.group.id")
    StudentDto personEntityToPersonDto(StudentEntity source);

    @Mapping(target = "group", source = "groupId", qualifiedBy = StudentMapperUtil.StudyGroupEntityByGroupId.class)
    StudentEntity personDtoToPersonEntity(StudentDto source);


//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "age", ignore = true)
//    @Mapping(target = "grades", ignore = true)
//    @Mapping(target = "group", expression = "java(studyGroupService.getStudyGroupByGroupName(source.getGroupName()))")
//    StudentDto studentUpgradeDtoToPersonDto(StudentUpdateGradeDto source);

    StudentUpdateStudyGroupDto studentUpdateStudyGroupRequestToStudentUpdateStudyGroupDto(StudentUpdateStudyGroupRequest source);
}
