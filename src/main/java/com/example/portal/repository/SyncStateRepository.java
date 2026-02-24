package com.example.portal.repository;


import com.example.portal.entity.SyncState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyncStateRepository extends JpaRepository<SyncState, Long> {
    SyncState findFirstByOrderByLastSyncTimeDesc();

}
