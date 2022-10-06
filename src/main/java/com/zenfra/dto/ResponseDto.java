package com.zenfra.dto;

import java.util.List;
import java.util.UUID;

import com.zenfra.model.ReportColumns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
	private UUID uuid;
	private String reportName;
	private List<ReportColumns> reportColumnsList;
	private ReportColumns reportColumns;
	private String message;
}
