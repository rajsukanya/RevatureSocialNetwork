package com.revature.controller;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Service
public class AWSController {
	private AmazonS3 s3Client;
	private String objectKey;
	String clientRegion = "us-east-2";
    String bucketName = "thetea";
    String accesskey = "AKIAV2C67LKNXDBLCP6V";
    String secretkey = "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";
    
    @PostConstruct 
    private void initializeAmazon() {
    	AWSCredentials credentials = new BasicAWSCredentials(this.accesskey, this.secretkey);
    	this.s3Client = new AmazonS3Client(credentials);
    }
    
    @Bean
    public AmazonS3 s3Client() {
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(this.accesskey, this.secretkey);
    	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(clientRegion)).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
    	return s3Client;
    }
}
