package com.example.portal.util;

import com.example.portal.dto.GoogleSheetRow;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class RowMapper {

    public GoogleSheetRow mapRow(List<Object> row) {
        GoogleSheetRow dto = new GoogleSheetRow();

        try {
            dto.setTimestamp(parseTimestamp(getString(row, 0)));
            dto.setFullName(getString(row, 1));
            dto.setMobileNumber(getString(row, 2));
            dto.setEmailId(getString(row, 3));
            dto.setApplyingForPosition(getString(row, 4));
            dto.setState(getString(row, 5));
            dto.setCity(getString(row, 6));
            dto.setLocation(getString(row, 7));
            dto.setDateOfBirth(parseDate(getString(row, 8)));
            dto.setAge(parseInteger(getString(row, 9)));
            dto.setGender(getString(row, 10));
            dto.setTotalExperience(getString(row, 11));
            dto.setRelevantExperience(getString(row, 12));
            dto.setCurrentSalary(getString(row, 13));
            dto.setExpectedSalary(getString(row, 14));
            dto.setQualification(getString(row, 15));
            dto.setNoticePeriod(getString(row, 16));
            dto.setResumeLink(getString(row, 17));
        } catch (Exception e) {
            // Log error but continue with partial data
        }

        return dto;
    }

    private String getString(List<Object> row, int index) {
        if (index < row.size() && row.get(index) != null) {
            return row.get(index).toString().trim();
        }
        return "";
    }

    private LocalDateTime parseTimestamp(String value) {
        if (value.isEmpty()) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm:ss");
            return LocalDateTime.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate parseDate(String value) {
        if (value.isEmpty()) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    private Integer parseInteger(String value) {
        if (value.isEmpty()) return null;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
