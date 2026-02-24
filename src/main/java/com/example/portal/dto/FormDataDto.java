package com.example.portal.dto;
import com.example.portal.entity.FormData;
public class FormDataDto {
    public static FormData toFormData(GoogleSheetRow dto) {
        FormData entity = new FormData();
        entity.setTimestamp(dto.getTimestamp());
        entity.setFullName(dto.getFullName());
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setEmailId(dto.getEmailId());
        entity.setApplyingForPosition(dto.getApplyingForPosition());
        entity.setState(dto.getState());
        entity.setCity(dto.getCity());
        entity.setLocation(dto.getLocation());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setTotalExperience(dto.getTotalExperience());
        entity.setRelevantExperience(dto.getRelevantExperience());
        entity.setCurrentSalary(dto.getCurrentSalary());
        entity.setExpectedSalary(dto.getExpectedSalary());
        entity.setQualification(dto.getQualification());
        entity.setNoticePeriod(dto.getNoticePeriod());
        entity.setResumeLink(dto.getResumeLink());
        return entity;
    }
}
