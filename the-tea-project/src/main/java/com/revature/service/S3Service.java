package com.revature.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.revature.repository.S3Repository;

@Service
public class S3Service implements S3Repository {
	
	@Autowired
	private AmazonS3 s3Client;
	
	private String bucketName = "thetea";
	
	private String endpoint;
	
	private String objectKey;
	
	@Override
	public ByteArrayOutputStream downloadFile(String keyName) {
		S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, keyName));
		InputStream is = s3object.getObjectContent();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len;
		byte[] buffer = new byte[4096];
		try {
			while((len = is.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, len);
			}
			return baos;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void uploadFile(String keyName, MultipartFile file) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		try {
			s3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (SdkClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		endpoint = "https://thetea.s3.us-east-2.amazonaws.com" + "/" + keyName;
		objectKey = keyName;
		System.out.println("Endpoint in s3service: " + endpoint);
		System.out.println("objectkey in s3service: " + objectKey);
		
	}

	@Override
	public List<String> listFiles() {
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);
		List<String> keys = new ArrayList<>();
		ObjectListing objects = s3Client.listObjects(listObjectsRequest);
		while(true) {
			List<S3ObjectSummary> summaries = objects.getObjectSummaries();
			if(summaries.size() < 1) {
				break;
			}
			for(S3ObjectSummary item: summaries) {
				if(!item.getKey().endsWith("/")) {
					keys.add(item.getKey());
				}
			}
			objects = s3Client.listNextBatchOfObjects(objects);
		}
		return keys;
	}
	
	

}
