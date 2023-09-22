package ru.goloviy.emailsenderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "mail_message")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email_from")
    private String from;
    @Column(name = "email_to")
    private String to;
    @Column(name = "text")
    private String text;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public Email(){
        timestamp = LocalDateTime.now();
    }
}
