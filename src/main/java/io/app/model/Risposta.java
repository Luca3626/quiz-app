package io.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="risposte")
public class Risposta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int idRisposta;
  
  @Column(nullable = false,length =200)
  private String testo;
  
  @Column(nullable=false)
  private boolean flag;
  
  @ManyToOne(fetch =FetchType.LAZY)
  @JoinColumn(name = "id_domanda")
  private Domanda domanda;

  @Transient
  private int dettagli;
  
    public Risposta(String testo, boolean flag,Domanda domanda) {
        this.testo = testo;
        this.flag = flag;
        this.domanda=domanda;
       
    }

    public Risposta() { }

    
    public int getDettagli() {
		return dettagli;
	}

	public void setDettagli(int dettagli) {
		this.dettagli = dettagli;
	}

	public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
  
public int getIdRisposta() {
	return idRisposta;
}

public void setIdRisposta(int idRisposta) {
	this.idRisposta = idRisposta;
}

public String getTesto() {
	return testo;
}

public void setTesto(String testo) {
	this.testo = testo;
}

public Domanda getDomanda() {
	return domanda;
}

public void setDomanda(Domanda domanda) {
	this.domanda = domanda;
}

    @Override
    public String toString() {
        return "Risposta{" + "idRisposta=" + idRisposta + ", testo=" + testo + ", flag=" + flag+"}";
    }

}
