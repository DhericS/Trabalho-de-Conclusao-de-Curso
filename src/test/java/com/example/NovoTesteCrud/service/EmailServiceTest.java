package com.example.NovoTesteCrud.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveEnviarEmailComSucesso() throws MessagingException {

        String destinatario = "teste@gmail.com";
        String assunto = "Testando a Service de E-mail";
        String conteudo = "<h1>Seja Bem Vindo ao Teste</h1>";

        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendEmail(destinatario, assunto, conteudo);

        verify(mailSender, times(1)).send(mimeMessage);
    }
}