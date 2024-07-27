package io.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.app.model.Domanda;

public interface DomandaRepository extends JpaRepository<Domanda, Integer> {
	@Query(value = "SELECT max(idDomanda) FROM Domanda")
	int getMaxId();
	
	
}
