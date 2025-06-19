package Classi;

public enum tipologia {
        Abbigliamento,
        Musica,
        Maradona_e_Napoli,
        Libri,
        Beauty_e_Benessere,
        Altro;

        //Questo metodo seerve per eguagliare la scrittura di alcuni caratteri, non permessi in Java.
        public static tipologia parseTipologia(String valore) {
                if (valore == null) return Altro;

                // Normalizza il valore dal DB
                String normalizzato = valore
                        .trim()
                        .replace(" & ", "_")
                        .replace(" e ", "_")
                        .replace(" ", "_");
                String formattato = normalizzato.substring(0, 1).toUpperCase() + normalizzato.substring(1).toLowerCase();
                try {
                        return tipologia.valueOf(formattato);
                } catch (IllegalArgumentException e) {
                        return Altro;
                }
        }
}