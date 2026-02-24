package com.example.portal.service;

import com.example.portal.dto.FormDataDto;
import com.example.portal.dto.GoogleSheetRow;
import com.example.portal.entity.FormData;
import com.example.portal.entity.SyncState;
import com.example.portal.repository.FormDataRepository;
import com.example.portal.repository.SyncStateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BatchProcessor {
    private static final int BATCH_SIZE = 10;

    @Autowired
    private FormDataRepository formDataRepository;

    @Autowired
    private SyncStateRepository syncStateRepository;

    @Transactional
    public int processBatch(List<GoogleSheetRow> rows, int startRowNumber) {
        if (rows == null || rows.isEmpty()) {
            return 0;
        }

        List<FormData> batch = new ArrayList<>();
        int processed = 0;

        for (GoogleSheetRow dto : rows) {
            FormData entity = FormDataDto.toFormData(dto);
            batch.add(entity);

            // When batch reaches size 10, save and clear
            if (batch.size() == BATCH_SIZE) {
                formDataRepository.saveAll(batch);
                processed += batch.size();
                batch.clear();
            }
        }

        // Save remaining items (less than 10)
        if (!batch.isEmpty()) {
            formDataRepository.saveAll(batch);
            processed += batch.size();
        }

        // Save checkpoint after each batch
        saveCheckpoint(startRowNumber + processed);

        return processed;
    }


    }
}
