package com.salesianostriana.pdam.inmoboscoapi.others.MailService;

import com.salesianostriana.pdam.inmoboscoapi.user.dto.CreateUserFromAdminDTO;
import com.salesianostriana.pdam.inmoboscoapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService  {

    private final JavaMailSender mailSender;

    public void sendPasswordMail(CreateUserFromAdminDTO user) {
        String to = user.getEmail();
        String subject = "Bienvenido a inmoBosco!";
        String body = String.format("Hola %s, bienvenido a nuestra inmobiliaria. " +
                "Para acceder con tu cuenta deberás usar las siguientes credenciales:\n" +
                "Nombre de usuario: %s\n" +
                "Contraseña: %s", user.getFirstname(), user.getUsername(), user.getPassword());

        sendEmail(to,subject,body);



    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
