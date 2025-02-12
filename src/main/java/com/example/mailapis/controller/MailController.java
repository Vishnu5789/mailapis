package com.example.mailapis.controller;


import com.example.mailapis.request.MailRequest;
import com.example.mailapis.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping(value = "/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> sendMail(  @RequestParam("to") String to,
                                             @RequestParam("subject") String subject,
                                             @RequestParam("body") String body,@RequestParam(value = "passportSizePhoto", required = false) MultipartFile photo
                            ,@RequestParam(value = "tenthMarks", required = false) MultipartFile tenthMarks
    ,@RequestParam(value = "interMarks", required = false) MultipartFile interMarks,
    @RequestParam(value = "diplomaMarks", required = false) MultipartFile diplomaMarks
    ,@RequestParam(value = "degreeMarks", required = false) MultipartFile degreeMarks ) throws MessagingException, IOException {
        try {
            String message = mailService.sendMailWIthAttachment(to,subject, body, photo,tenthMarks,interMarks,diplomaMarks,degreeMarks
            );
            return ResponseEntity.ok(message);
        } catch (MessagingException | IOException e) {
            return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
        }
    }
}

