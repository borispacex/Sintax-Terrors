package com.dharbor.sintaxterrors.service;

import com.dharbor.sintaxterrors.dto.email.EmailRequestDto;
import com.dharbor.sintaxterrors.dto.email.EmailResponseDto;
import com.dharbor.sintaxterrors.exception.EmailSendException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Qualifier("gmailSender") // default
    private final JavaMailSender gmailSender;

    @Qualifier("outlookSender")
    private final JavaMailSender outlookSender;

    public EmailService(JavaMailSender gmailSender, JavaMailSender outlookSender) {
        this.gmailSender = gmailSender;
        this.outlookSender = outlookSender;
    }

    public EmailResponseDto sendEmail(EmailRequestDto dto) {
        try {
            String provider = dto.getProvider();
            if (provider == null || provider.isBlank()) {
                provider = "gmail";
            }

            JavaMailSender selectedSender = provider.equalsIgnoreCase("outlook")
                    ? outlookSender
                    : gmailSender;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(dto.getTo());
            message.setSubject(dto.getSubject());
            message.setText("Hola, este es un mensaje dinámico.");
            selectedSender.send(message);

            return new EmailResponseDto("Correo enviado con éxito", true);
        } catch (Exception e) {
            throw new EmailSendException("No se pudo enviar el correo: " + e.getMessage(), e);
        }
    }

}
