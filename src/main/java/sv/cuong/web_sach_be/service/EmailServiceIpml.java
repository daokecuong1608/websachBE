package sv.cuong.web_sach_be.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceIpml implements EmailService {
    @Autowired
    private JavaMailSender emailSenderl;

    @Override
    public void sendMessage(String from, String to, String subject, String text) {

        // MimeMailMessage : có đính kèm tệp tin hình ảnh
//        SimpleMailMessage  : có mình nội dung thông thường (text)

        MimeMessage message = emailSenderl.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        emailSenderl.send(message);
    }
}
