package ru.goloviy.filestorageservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @OneToMany(mappedBy = "owner")
    private List<Image> images = new ArrayList<>();
}
