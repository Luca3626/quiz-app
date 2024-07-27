package io.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.app.model.Risposta;
import io.app.repository.RispostaRepository;
import java.util.List;

@Service
public class RispostaService {
	  @Autowired	
	   private RispostaRepository rispostaRepository;

	  public void saveRisposta(List<Risposta> risposte)
	  {
	     rispostaRepository.saveAll(risposte);
	  }
          
}
