import java.time.LocalDate;

public class Commande {
    private static int compteur = 0;
    private int numero;
    private LocalDate dateCommande;
    private Ouvrage ouvrage;
    private int quantite;

    public Commande(Ouvrage ouvrage, int quantite) {
        this.numero = ++compteur;
        this.dateCommande = LocalDate.now();
        this.ouvrage = ouvrage;
        this.quantite = quantite;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "numero=" + numero +
                ", dateCommande=" + dateCommande +
                ", ouvrage=" + ouvrage +
                ", quantite=" + quantite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return numero == commande.numero;
    }

    @Override
    public int hashCode() {
        return numero;
    }
}
