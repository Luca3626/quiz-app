package io.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.app.model.Domanda;
import io.app.repository.DomandaRepository;
import java.util.List;

@Service
public class DomandaService {
  @Autowired	
   private DomandaRepository domandaRepository;

  public Domanda saveDomanda(Domanda d)
  {
	 return domandaRepository.save(d);
          
  }
 
  public List<Domanda> loadQuestion()
  {
	
       return domandaRepository.findAll();
     
  }
 

}
