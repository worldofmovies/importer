package se.david.moviesimporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.david.moviesimporter.domain.entities.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, String> {
}
