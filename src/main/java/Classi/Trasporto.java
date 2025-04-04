package Classi;

import java.util.Date;

public class Trasporto {
    public String Marca;
    public Date annoImmatricolazione;
    public Tipo_Trasporto Mezzo_di_Trasporto;
    public float Peso_Massimo_Trasportabile;
    public String Targa;
    public boolean Disponibilita;

  public Trasporto (String Marca, Date annoImmatricolazione,Tipo_Trasporto Mezzo_di_Trasporto, float Peso_Massimo_Trasportabile, String Targa, boolean Disponibilita ) {
      this.Marca = Marca;
      this.annoImmatricolazione = annoImmatricolazione;
      this.Mezzo_di_Trasporto = Mezzo_di_Trasporto;
      this.Peso_Massimo_Trasportabile = Peso_Massimo_Trasportabile;
      this.Targa = Targa;
      this.Disponibilita = Disponibilita;
  }

}
