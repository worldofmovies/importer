package se.david.moviesimporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.entities.LanguageEntity;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, String> {
}
