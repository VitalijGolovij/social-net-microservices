package ru.goloviy.messageservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat")
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Message> messages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<User> members;

    public Chat(Set<User> members){
        this.members = members;
        messages = new ArrayList<>();
    }
    public Chat(){
        this.members = new HashSet<>();
        this.messages = new ArrayList<>();
    }
    public void addMessage(Message message){
        messages.add(message);
    }
    public void addMember(User user){
        members.add(user);
    }

    public Long getId() {
        return id;
    }
    @JsonManagedReference
    public List<Message> getMessages() {
        return messages;
    }
    public Set<User> getMembers() {
        return members;
    }
}
