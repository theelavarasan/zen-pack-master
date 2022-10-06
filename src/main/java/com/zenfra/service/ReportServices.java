package com.zenfra.service;

import com.zenfra.dto.CreateColumns;
import com.zenfra.dto.ResponseDto;
import com.zenfra.model.ReportColumns;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface ReportServices {
    ResponseEntity<ReportColumns> createReport(CreateColumns create);

    ResponseEntity<ResponseDto> getReportById(UUID uuid) throws Exception;

    ResponseEntity<ResponseDto> listOfRecords();

    ResponseEntity<ResponseDto> editReport(CreateColumns columns);

    ResponseEntity<ResponseDto> deleteReportById(UUID uuid);
}
