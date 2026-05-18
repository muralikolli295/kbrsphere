package com.kbrsphere.email_service.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${email.from}")
    private String fromEmail;

//    @Override
    public void sendWelcomeEmailV1(String to, String name) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String htmlContent = """
                    <html>
                        <body style="font-family: Arial, sans-serif;">
                            <h2>Welcome to KBRSphere, %s 👋</h2>
                            <p>Your account has been successfully created.</p>
                            <p>We’re excited to have you onboard 🚀</p>
                            <br/>
                            <p>Thanks,<br/>KBRSphere Team</p>
                        </body>
                    </html>
                    """.formatted(name);

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject("Welcome to KBRSphere 🚀");
            helper.setText(htmlContent, true); // true = HTML

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendWelcomeEmail(String to, String name) {
        System.out.println("Mock Email Sent To: "+ to);
        System.out.println("Name: " + name);
    }

}
