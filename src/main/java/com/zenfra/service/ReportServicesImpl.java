package com.zenfra.service;

import com.zenfra.dto.CreateColumns;
import com.zenfra.dto.ResponseDto;
import com.zenfra.model.ReportColumns;
import com.zenfra.repository.ReportColumnsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportServicesImpl implements ReportServices{

    @Autowired
    private ReportColumnsRepository repository;

//    @Autowired
//    private ResponseDto dto;

    @Override
    public ResponseEntity<ReportColumns> createReport(CreateColumns create) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ReportColumns columns = mapper.map(create, ReportColumns.class);
        //repository.save(columns);
        return new ResponseEntity<>(repository.save(columns),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> getReportById(UUID uuid) throws Exception {
        Optional<ReportColumns> columns = repository.findByUuid(uuid);
        if (columns.isPresent()) {
            ResponseDto response = ResponseDto.builder()
                    .uuid(columns.get().getUuid())
                    .message("Response Success")
                    .reportColumns(null)
                    .reportColumnsList(null)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            throw new Exception();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> listOfRecords() {
        List<ReportColumns> list=repository.findAll();
        ResponseDto responseDto= ResponseDto.builder()
                .uuid(list.get(0).getUuid())
                .reportName(null)
                .reportColumnsList(list)
                .message("All Reports Are: ")
                .build();

        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> editReport(CreateColumns columns) {
        List<ReportColumns> reportColumns = repository.findAll();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ReportColumns columns1 = mapper.map(columns,ReportColumns.class);
        repository.save(columns1);
        ResponseDto responseDto =ResponseDto.builder()
                .uuid(columns.getUuid())
                .reportName(columns.getReportName())
                .message("Updated")
                .reportColumnsList(reportColumns)
                .build();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteReportById(UUID uuid) {
         repository.deleteByUuid(uuid);
        return new ResponseEntity<>(ResponseDto.builder().build(),HttpStatus.OK);
    }
}
