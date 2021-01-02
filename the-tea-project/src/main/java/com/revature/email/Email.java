package com.revature.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Configurable
@SpringBootApplication
@Service
public class Email { //implements CommandLineRunner
		
    @Autowired
    private static JavaMailSender javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(Email.class, args);
    }

//    @Override
//    public void run(String... args) {
//
//        System.out.println("Sending Email...");
//
////        try {
////        	
//        	
//            sendEmail(args);
//            //sendEmailWithAttachment();
//
////        } catch (MessagingException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//        System.out.println("Done");
//
//    }

    public void sendEmail(String email) throws Exception{
    	MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        //SimpleMailMessage msg = new SimpleMailMessage();
        //String email = "sukanyaraj1014@gmail.com";
        try {
			helper.setTo(email);
			helper.setSubject("Message from The Tea");
	        helper.setText("You have requested to reset your password for your The Tea account.");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        javaMailSender.send(msg);
    }

//    void sendEmailWithAttachment() throws MessagingException, IOException {
//
//        MimeMessage msg = javaMailSender.createMimeMessage();
//
//        // true = multipart message
//        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//        helper.setTo("1@gmail.com");
//
//        helper.setSubject("Testing from Spring Boot");
//
//        // default = text/plain
//        //helper.setText("Check attachment for image!");
//
//        // true = text/html
//        helper.setText("<h1>Check attachment for image!</h1>", true);
//
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
//
//        javaMailSender.send(msg);
//
//    }
}
