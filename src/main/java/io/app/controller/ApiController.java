package io.app.controller;

import io.app.model.Domanda;
import io.app.model.Risposta;
import io.app.service.DomandaService;
import io.app.service.QuizService;
import io.app.service.RispostaService;

import java.io.IOException;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/api")
@Controller
public class ApiController {

	@Autowired
	private DomandaService domandaService;
	@Autowired
	private QuizService quizService;
		
	private int[] arr=new int[2];
	private List<Integer> risposte=new ArrayList<Integer>();

	@Autowired
	private RispostaService rispostaService;

	@GetMapping("/ins")
	public String formDomanda(Model model) {
		model.addAttribute("categorie", quizService.getQuiz());
		return "ins-domande";
	}

	@PostMapping()
	public String save(@ModelAttribute Domanda domanda, String[] risposta, int check) {
		Domanda d1 = domandaService.saveDomanda(domanda);
		List<Risposta> risposte = Arrays.stream(risposta).filter(r->!r.equals("")  ).map(r -> new Risposta(r, false, d1)).toList();
		risposte.get(check).setFlag(true);
		rispostaService.saveRisposta(risposte);
		return "index";
	}

	@GetMapping("/categoria")
	public ModelAndView loadCategorie() {
		ModelAndView model = new ModelAndView("quiz");
		model.addObject("categories", quizService.getQuiz());
		model.addObject("countDomanda", 0);
		
		return model;
	}

	@GetMapping("/quiz")
	/*
	 * btn name del bottono selezionato check name della checkbox selezionata
	 * dall'utente
	 */
	public ModelAndView startQuiz(String btn, String check, String category) throws IOException {

		Domanda d;
		ModelAndView model = loadCategorie();
		int idquiz = Integer.parseInt(category);
		var lista = quizService.getDomandeById(idquiz);
		var count = lista.size();
		if (count == 0)
			return model;
		if(btn==null)
		  reset();
       		
		if(btn!=null && btn.equals("dettagli")) {
			for(Domanda dom:lista)
			{
				Risposta risp=dom.getRisposte().stream().filter(r->r.isFlag()).findFirst().get();
				risp.setDettagli(1);
				dom.getRisposte().stream().filter(r->risposte.contains(r.getIdRisposta())).forEach(r->r.setDettagli(2));
				
				}
			
			Map<String, Object> map = Stream.of(new AbstractMap.SimpleEntry<>("numDomande", arr[0]-1),
					new AbstractMap.SimpleEntry<>("numRisposte",arr[1]),
					new AbstractMap.SimpleEntry<>("domande", lista)).
	     			collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		  return new ModelAndView("dettagli", map);
		}
		int indice=arr[0];
		int numRisposte = arr[1];
		/*
		 * se l'utente ha selezionato il bottone di reload allora l'indice viene
		 * inizializzato a 0 insieme al numero delle risposte e viene caricata la prima
		 * domanda
		 */
		if (btn != null && btn.equals("reload")) {
			indice = 0;
			numRisposte = 0;
			d = lista.get(0);
			reset();
		} else {
			/*
			 * nel caso in cui indice è uguale a 0 viene caricata la prima domanda
			 * altrimenti viene caricata la domanda corrente e viene verificato se la
			 * risposta è esatta
			 */
			if (indice == 0)
				d = lista.get(0);

			else {
              
				d = lista.get(indice - 1);
				if (d.getRisposte().stream().anyMatch(r -> r.isFlag() && r.getIdRisposta() == Integer.parseInt(check)))
				  numRisposte++;
					
				
				else
				  risposte.add(Integer.parseInt(check));
					
										
				if (indice < count)
					d = lista.get(indice);
				else {
					d = lista.get(count - 1);
					model.addObject("risposte_esatte",
							"Hai indovinato " + numRisposte + " risposte su " + count + " domande!");
					
				}
			}

		}
		Map<String, Object> map = Stream.of(new AbstractMap.SimpleEntry<>("fine", (indice == count) ? false : true),
				new AbstractMap.SimpleEntry<>("indiceDomanda", (indice == count) ? indice : indice + 1),
				new AbstractMap.SimpleEntry<>("testoDomanda", d.getTesto()),
				new AbstractMap.SimpleEntry<>("countDomanda", count),
				new AbstractMap.SimpleEntry<>("risposte", d.getRisposte())).
     			collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		    model.addAllObjects(map);

		arr[0]=indice+1;
		arr[1]=numRisposte;
		model.addObject("idquiz", idquiz);
		return model;
	}
	private void reset()
	{
		arr[0]=0;
		arr[1]=0;
		risposte.clear();
	}
}
