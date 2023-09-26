package ru.goloviy.filestorageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.filestorageservice.model.Image;
import ru.goloviy.filestorageservice.model.User;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findById(Long id);
    Optional<Image> findByName(String name);
    List<Image> findByOwner(User owner);
}
