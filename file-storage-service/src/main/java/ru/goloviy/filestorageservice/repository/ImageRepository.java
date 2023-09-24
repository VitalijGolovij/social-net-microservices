package ru.goloviy.filestorageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.filestorageservice.model.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findById(Long id);
    Optional<Image> findByName(String name);
}
