package io.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="domande")
public class Domanda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int idDomanda;
  @Column(nullable = false,length =200)
  private String testo;
  
  @OneToMany(mappedBy = "domanda",fetch = FetchType.EAGER)
  private List<Risposta> risposte;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idcategoria")
  private TipoQuiz tipoquiz;
  
  
public TipoQuiz getTipoquiz() {
	return tipoquiz;
}
public void setTipoquiz(TipoQuiz tipoquiz) {
	this.tipoquiz = tipoquiz;
}
public int getIdDomanda() {
	return idDomanda;
}
public void setIdDomanda(int idDomanda) {
	this.idDomanda = idDomanda;
}
public String getTesto() {
	return testo;
}
public void setTesto(String testo) {
	this.testo = testo;
}
public List<Risposta> getRisposte() {
	return risposte;
}
public void setRisposte(List<Risposta> risposte) {
	this.risposte = risposte;
}

    @Override
    public String toString() {
        return "Domanda{" + "idDomanda=" + idDomanda + ", testo=" + testo + ", risposte=" + risposte + '}';
    }
  
 
  
}
