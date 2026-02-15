package com.example.portal.repository;

import com.example.portal.entity.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDataRepository extends JpaRepository<FormData,Long> {
}
