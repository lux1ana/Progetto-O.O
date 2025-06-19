package Classi;

import C.DAO.Database_DAO;
import Classi.Merce;
import Classi.Immagazzinata;
import C.DAO.Merce_DAO;
import C.DAO.Immagazzinata_DAO;

//Questa è una classe DTO che ho usato come appoggio per il controller nuovespedizioni, vista la necessità di utilizzare
//entrambe le classi Merci e Immagazzinata
public class ProdottoTabella {
    private String Nome_prodotto;
    private int quantita;
    private String Codice_Prodotto;

    public ProdottoTabella(String Nome_prodotto, int quantita, String Codice_Prodotto) {
        this.Nome_prodotto =Nome_prodotto;
        this.quantita = quantita;
        this.Codice_Prodotto = Codice_Prodotto;

    }

    public String getNome_prodotto() {
        return Nome_prodotto;
    }

    public void setNome_prodotto(String Nome_prodotto) {
        this.Nome_prodotto = Nome_prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getCodice_Prodotto() {
        return Codice_Prodotto;
    }

    public void setCodice_Prodotto(String codice_Prodotto) {
        this.Codice_Prodotto = codice_Prodotto;
    }

}
