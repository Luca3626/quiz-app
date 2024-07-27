package io.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "quiz")
public class TipoQuiz {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idquiz;
   
   @Column(length = 50,nullable = false)
   private String categoria;
   
   @OneToMany(mappedBy = "tipoquiz",fetch = FetchType.EAGER)
   private List<Domanda> domande;

public int getIdquiz() {
	return idquiz;
}

public void setIdquiz(int idquiz) {
	this.idquiz = idquiz;
}

public String getCategoria() {
	return categoria;
}

public void setCategoria(String categoria) {
	this.categoria = categoria;
}

public List<Domanda> getDomande() {
	return domande;
}

public void setDomande(List<Domanda> domande) {
	this.domande = domande;
}
   
   
}
