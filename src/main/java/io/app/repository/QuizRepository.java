package io.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.app.model.TipoQuiz;
@Repository
public interface QuizRepository extends JpaRepository<TipoQuiz, Integer> {
	
	TipoQuiz findByIdquiz(int id);

}
