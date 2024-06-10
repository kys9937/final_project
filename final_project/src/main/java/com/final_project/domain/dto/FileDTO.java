package com.final_project.domain.dto;

import lombok.Data;

@Data
public class FileDTO {
    private String ub_systemname;  // 카멜 케이스로 변경
    private String ub_orgname;     // 카멜 케이스로 변경
    private long boardnum;
}