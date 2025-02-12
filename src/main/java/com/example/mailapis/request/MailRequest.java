package com.example.mailapis.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
public class MailRequest {
    private String to;
    private String subject;
    private String body;
    private MultipartFile file;
}
