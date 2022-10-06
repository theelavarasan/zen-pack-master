package com.zenfra.controller;

import com.zenfra.dto.CreateColumns;

import com.zenfra.dto.ResponseDto;
import com.zenfra.model.ReportColumns;
import com.zenfra.service.ReportServices;
import com.zenfra.service.ReportServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ReportController {

	@Autowired
	private ReportServicesImpl reportService;

	@PostMapping(value = "/report")
	public ResponseEntity<ReportColumns> createReport(@RequestBody CreateColumns create){
		return	reportService.createReport(create);
	}
	@GetMapping("/report/{uuid}")
	public ResponseEntity<ResponseDto> getReportById(@PathVariable UUID uuid) throws Exception {
		return reportService.getReportById(uuid);
	}
	@GetMapping("/reports")
	public ResponseEntity<ResponseDto> getListOfRecords(){
		return reportService.listOfRecords();
	}

	@PutMapping("/report")
	public ResponseEntity<ResponseDto> EditReport(@RequestBody CreateColumns columns){
		return reportService.editReport(columns);
	}

	@DeleteMapping("/reports/{uuid}")
	@Transactional
	public ResponseEntity<ResponseDto> deleteReport(@PathVariable UUID uuid){
		return reportService.deleteReportById(uuid);
	}
}
