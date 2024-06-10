package com.final_project.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FileService {
	ResponseEntity<Resource> getThumbnailResource(String ub_systemname) throws Exception;
	ResponseEntity<Resource> downloadFile(String systemname, String orgname) throws Exception;
}
