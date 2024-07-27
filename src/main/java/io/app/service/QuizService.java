package io.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.app.model.Domanda;
import io.app.model.TipoQuiz;
import io.app.repository.QuizRepository;

@Service
public class QuizService {
   
	@Autowired
	private QuizRepository quizRepository;
	
	public List<TipoQuiz> getQuiz()
	{
		return quizRepository.findAll();
	}
	public List<Domanda> getDomandeById(int idquiz)
	{
		return quizRepository.findByIdquiz(idquiz).getDomande();
	}
}
