package Classi;

import java.util.Date;

public class Trasporto {
    public String marca;
    public Date anno_immatricolazione;
    public tipologia_trasporto tipologia_trasporto;
    public float peso_max_trasportabile_kg;
    public String targa;
    public boolean disponibilità;

  public Trasporto (String marga, Date anno_immatricolazione, tipologia_trasporto tipologia_trasporto, float peso_max_trasportabile_kg, String marca, boolean disponibilità) {
      this.marca = marga;
      this.anno_immatricolazione = anno_immatricolazione;
      this.tipologia_trasporto = tipologia_trasporto;
      this.peso_max_trasportabile_kg = peso_max_trasportabile_kg;
      this.targa = marca;
      this.disponibilità = disponibilità;
  }
    @Override
    public String toString() {
        return targa;
    }

}
