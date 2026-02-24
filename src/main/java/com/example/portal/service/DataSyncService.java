package com.example.portal.service;


import com.example.portal.dto.FormDataDto;
import com.example.portal.dto.GoogleSheetRow;
import com.example.portal.entity.FormData;
import com.example.portal.entity.SyncState;
import com.example.portal.repository.FormDataRepository;
import com.example.portal.repository.SyncStateRepository;
import com.example.portal.util.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataSyncService {

    @Autowired
    private GoogleSheetsService googleSheetsService;

    @Autowired
    private FormDataRepository formDataRepository;

    @Autowired
    private SyncStateRepository syncStateRepository;

    @Autowired
    private RowMapper rowMapper;

    @Scheduled(cron = "0 */1 * * * *")
    public void syncData() {
        System.out.println("Starting sync at: " + LocalDateTime.now());

        try {
            SyncState lastSync = syncStateRepository.findFirstByOrderByLastSyncTimeDesc();

            int currentRow = (lastSync != null && lastSync.getLastRowProcessed() != null)
                    ? lastSync.getLastRowProcessed()
                    : 1;

            List<List<Object>> rows = googleSheetsService.fetchData();

            if (rows == null || rows.isEmpty()) {
                System.out.println("No data found");
                return;
            }

            int processed = 0;
            for (int i = currentRow; i < rows.size(); i++) {
                List<Object> row = rows.get(i);
                GoogleSheetRow dto = rowMapper.mapRow(row);


                FormData entity = FormDataDto.toFormData(dto);
                formDataRepository.save(entity);
                processed++;
            }

            SyncState newState = new SyncState();
            newState.setLastRowProcessed(rows.size());
            newState.setLastSyncTime(LocalDateTime.now());
            newState.setSyncStatus("SUCCESS");
            syncStateRepository.save(newState);

            System.out.println("Sync completed. Processed: " + processed + " rows");

        } catch (Exception e) {
            System.err.println("Sync failed: " + e.getMessage());
            e.printStackTrace();
            saveFailedState(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    private void saveFailedState(String error) {
        SyncState state = new SyncState();
        state.setLastSyncTime(LocalDateTime.now());
        state.setSyncStatus("FAILED: " + error);
        state.setLastRowProcessed(0);
        syncStateRepository.save(state);
    }
}