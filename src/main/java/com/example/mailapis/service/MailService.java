package com.example.mailapis.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendMailWIthAttachment(String to, String subject, String body, MultipartFile photo,MultipartFile tenthMarks,
                                         MultipartFile interMarks,MultipartFile diplomaMarks,MultipartFile degreeMarks) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true = multipart

        log.info("To address is :{}", to);
        log.info("subject is :{}", subject);
        log.info("Body is :{}", body);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        ArrayList<MultipartFile> files = new ArrayList<>();
        if (photo != null) files.add(photo);
        if (tenthMarks != null) files.add(tenthMarks);
        if (interMarks != null) files.add(interMarks);
        if (diplomaMarks != null) files.add(diplomaMarks);
        if (degreeMarks != null) files.add(degreeMarks);
        for (MultipartFile file : files) {
            extracted(file, helper);
        }
        log.info("*****Sending Mail initiated*****");
        mailSender.send(message);
        log.info("*****Sending Mail Processed*****");
        return "SUCCESS";

    }

    private void extracted(MultipartFile photo, MimeMessageHelper helper) throws IOException, MessagingException {
        InputStreamSource source = new ByteArrayResource(photo.getBytes());
        helper.addAttachment(photo.getOriginalFilename(), source);
    }
}
