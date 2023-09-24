package ru.goloviy.filestorageservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "data", length = 1024)
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
}
