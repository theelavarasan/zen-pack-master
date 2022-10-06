package com.zenfra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zenfra.model.ReportColumns;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportColumnsRepository extends JpaRepository<ReportColumns, String> {

    Optional<ReportColumns> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
