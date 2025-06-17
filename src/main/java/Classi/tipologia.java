package Classi;

public enum tipologia {
        Abbigliamento,
        Musica,
        Maradona_e_Napoli,
        Libri,
        Beauty_e_Benessere,
        Altro; // fallback

        public static tipologia parseTipologia(String valore) {
                if (valore == null) return Altro;

                // Normalizza il valore dal DB
                String normalizzato = valore
                        .trim()
                        .replace(" & ", "_")
                        .replace(" e ", "_")
                        .replace(" ", "_");

                // Metti la prima lettera maiuscola e il resto minuscolo per matchare con la enum
                String formattato = normalizzato.substring(0, 1).toUpperCase() + normalizzato.substring(1).toLowerCase();

                try {
                        return tipologia.valueOf(formattato);
                } catch (IllegalArgumentException e) {
                        return Altro;
                }
        }
}
