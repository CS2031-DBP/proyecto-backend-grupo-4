package com.proyecto.utec_roomie.request;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendRegisterMessage(String emailto, String asunto, String nombre, String apellido,String role){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'a las' HH:mm 'hrs'", new Locale("es", "PE"));
        String formattedDate = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Lima")).format(formatter);
        Context context = new Context();


        context.setVariable("date", formattedDate);
        context.setVariable("nombre", nombre);
        context.setVariable("apellido", apellido);
        context.setVariable("role",role);

        String body = templateEngine.process("BienvenidaMail",  context);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("milton.cordova@utec.edu.pe");
            messageHelper.setTo(emailto);
            messageHelper.setSubject(asunto);
            messageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendSolicitudMessage(String emailto, String asunto, String nombre, String apellido, Date fechaInicio, Date fechaFin, String mensaje){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'a las' HH:mm 'hrs'", new Locale("es", "PE"));
        String formattedDate = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Lima")).format(formatter);
        Context context = new Context();


        context.setVariable("date", formattedDate);
        context.setVariable("nombre", nombre);
        context.setVariable("apellido", apellido);
        context.setVariable("fechaInicio", fechaInicio);
        context.setVariable("fechaFin", fechaFin);
        context.setVariable("mensaje", mensaje);

        String body = templateEngine.process("SolicitudMail",  context);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("milton.cordova@utec.edu.pe");
            messageHelper.setTo(emailto);
            messageHelper.setSubject(asunto);
            messageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendArrendamientoAnfitrionMessage(String emailto, String asunto, String nombre, String apellido, Date fechaInicio, Date fechaFin, String mensaje){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy 'a las' HH:mm 'hrs'", new Locale("es", "PE"));
        String formattedDate = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Lima")).format(formatter);
        Context context = new Context();


        context.setVariable("date", formattedDate);
        context.setVariable("nombre", nombre);
        context.setVariable("apellido", apellido);
        context.setVariable("fechaInicio", fechaInicio);
        context.setVariable("fechaFin", fechaFin);
        context.setVariable("mensaje", mensaje);

        String body = templateEngine.process("ArrendamientoAnfitrionMail",  context);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("milton.cordova@utec.edu.pe");
            messageHelper.setTo(emailto);
            messageHelper.setSubject(asunto);
            messageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}