package Classi;

import javafx.beans.property.*;
import java.util.Date;

public class Ordine {
    public StringProperty codice_ordine;
    public ObjectProperty<Date> data_ordine;
    public StringProperty nomeVia;
    public IntegerProperty Numero;
    public StringProperty città;
    public StringProperty Provincia;
    public StringProperty Stato;
    public BooleanProperty Ordine_Effettuato;
    public FloatProperty Peso;
    public DoubleProperty Costo;
    public ObjectProperty<Persona> cod_fiscale_cliente;
    public ObjectProperty<Spedizione> codice_spedizione;
    public IntegerProperty  num_prodotti;


    public Ordine(String codice_ordine, Date data_ordine, String Nome_Via, int Numero, String città,
                  String Provincia, String Stato, boolean Ordine_Effettuato, float Peso, double Costo,
                  Persona cod_fiscale_cliente, Spedizione codice_spedizione, Integer  num_prodotti) {

        this.codice_ordine = new SimpleStringProperty(codice_ordine);
        this.data_ordine = new SimpleObjectProperty<>(data_ordine);
        this.nomeVia = new SimpleStringProperty(Nome_Via);
        this.Numero = new SimpleIntegerProperty(Numero);
        this.città = new SimpleStringProperty(città);
        this.Provincia = new SimpleStringProperty(Provincia);
        this.Stato = new SimpleStringProperty(Stato);
        this.Ordine_Effettuato = new SimpleBooleanProperty(Ordine_Effettuato);
        this.Peso = new SimpleFloatProperty(Peso);
        this.Costo = new SimpleDoubleProperty(Costo);
        this.cod_fiscale_cliente = new SimpleObjectProperty<>(cod_fiscale_cliente);
        this.codice_spedizione = new SimpleObjectProperty<>(codice_spedizione);
        this.num_prodotti = new SimpleIntegerProperty(num_prodotti);
    }

    //costruttore vuoto
    public Ordine() {
        this("", null, "", 0, "", "", "", false,
                0f, 0.0, null, null, 0);
    }
    @Override
    public String toString() {
        return "Ordine #" + codice_ordine + " - " + città + ", " + data_ordine;
    }


    public String getCodice_ordine() {
        return codice_ordine.get();
    }

    public void setCodice_ordine(String Codice_Ordine) {
        this.codice_ordine.set(Codice_Ordine);
    }

    public Date getData_ordine() {
        return data_ordine.get();
    }

    public void setData_ordine(Date Data_Ordine) {
        this.data_ordine.set(Data_Ordine);
    }

    public String getNome_Via() {
        return nomeVia.get();
    }

    public void setNome_Via(String Nome_Via) {
        this.nomeVia.set(Nome_Via);
    }

    public int getNumero() {
        return Numero.get();
    }

    public void setNumero(int Numero) {
        this.Numero.set(Numero);
    }

    public String getCittà() {
        return città.get();
    }

    public void setCittà(String Citta) {
        this.città.set(Citta);
    }

    public String getProvincia() {
        return Provincia.get();
    }

    public void setProvincia(String Provincia) {
        this.Provincia.set(Provincia);
    }

    public String getStato() { return Stato.get();}

    public void setStato(String Stato) {
        this.Stato.set(Stato);
    }

    public boolean isOrdine_Effettuato() {
        return Ordine_Effettuato.get();
    }

    public void setOrdine_Effettuato(boolean Ordine_Effettuato) {
        this.Ordine_Effettuato.set(Ordine_Effettuato);
    }

    public float getPeso() {
        return Peso.get();
    }

    public void setPeso(float Peso) {
        this.Peso.set(Peso);
    }

    public double getCosto() {
        return Costo.get();
    }

    public void setCosto(double Costo) {
        this.Costo.set(Costo);
    }

    public Persona getCod_fiscale_cliente() {
        return cod_fiscale_cliente.get();
    }

    public void setCod_fiscale_cliente(Persona Codice_FiscaleCliente) {
        this.cod_fiscale_cliente.set(Codice_FiscaleCliente);
    }

    public Spedizione getCodice_spedizione() {
        return codice_spedizione.get();
    }

    public void setCodice_spedizione(Spedizione Codice_Spedizione) {
        this.codice_spedizione.set(Codice_Spedizione);
    }

    public Integer  getNum_prodotti(){
        return num_prodotti.get();
    }
    public void setNum_prodotti(int Num_prodotti){
        this.num_prodotti.set(Num_prodotti);
    }

    public StringProperty Codice_OrdineProperty() {
        return codice_ordine;
    }

    public ObjectProperty<Date> Data_OrdineProperty() {
        return data_ordine;
    }

    public StringProperty Nome_ViaProperty() {
        return nomeVia;
    }

    public IntegerProperty NumeroProperty() {
        return Numero;
    }

    public StringProperty CittaProperty() {
        return città;
    }

    public StringProperty ProvinciaProperty() {
        return Provincia;
    }

    public StringProperty StatoProperty() {
        return Stato;
    }

    public BooleanProperty Ordine_EffettuatoProperty() {
        return Ordine_Effettuato;
    }

    public FloatProperty PesoProperty() {
        return Peso;
    }

    public DoubleProperty CostoProperty() {
        return Costo;
    }

    public ObjectProperty<Persona> Codice_FiscaleClienteProperty() {
        return cod_fiscale_cliente;
    }

    public ObjectProperty<Spedizione> Codice_SpedizioneProperty() {
        return codice_spedizione;
    }
}