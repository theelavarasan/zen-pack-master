package com.zenfra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateColumns {

    private String deviceType;
    private UUID uuid;
    private String reportName;
    private String dataType;
    private String isSizeMetrics;
    private String seq;
    private String columnName;
    private String reportBy;
    private String dbFieldName;
    private boolean isPinned;
    private String aliasName;
    private String devices;
    private String taskListCategory;
    private Integer categorySeq;
    private Integer subCategorySeq;
    private boolean hide;
    private String taskListSubCategory;

}
