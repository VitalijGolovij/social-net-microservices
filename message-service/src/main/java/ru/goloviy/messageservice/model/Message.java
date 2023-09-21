package ru.goloviy.messageservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@Setter
@Entity
@Table(name = "message")
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User owner;

    public Message(User owner, String text){
        this.owner = owner;
        this.message = text;
        timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @JsonBackReference
    public Chat getChat() {
        return chat;
    }

    public User getOwner() {
        return owner;
    }
}
