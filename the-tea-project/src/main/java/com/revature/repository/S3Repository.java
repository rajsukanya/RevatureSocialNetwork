package com.revature.repository;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

public interface S3Repository {
	public ByteArrayOutputStream downloadFile(String keyName);
	
	public void uploadFile(String keyName, MultipartFile file);
	
	public List<String> listFiles();

}
