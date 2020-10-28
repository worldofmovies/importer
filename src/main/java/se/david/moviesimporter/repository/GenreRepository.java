package se.david.moviesimporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.entities.CountryEntity;
import se.david.moviesimporter.domain.entities.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
