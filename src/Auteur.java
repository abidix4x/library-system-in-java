import java.util.List;
import java.util.ArrayList;

public class Auteur {
    private String code;
    private String nom;
    private String prenom;
    private List<Ouvrage> ouvragesEcrits;
    private List<Ouvrage> ouvragesTraduits;

    public Auteur(String code, String nom, String prenom) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.ouvragesEcrits = new ArrayList<>();
        this.ouvragesTraduits = new ArrayList<>();
    }

    public void ajouterOuvrageEcrit(Ouvrage ouvrage) {
        ouvragesEcrits.add(ouvrage);
    }
    public void supprimererOuvrageEcrit(Ouvrage ouvrage) {
        ouvragesEcrits.remove(ouvrage);
    }

    public void ajouterOuvrageTraduit(Ouvrage ouvrage) {
        ouvragesTraduits.add(ouvrage);
    }
    public void supprimerOuvrageTraduit(Ouvrage ouvrage) {
        ouvragesTraduits.remove(ouvrage);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Ouvrage> getOuvragesEcrits() {
        return ouvragesEcrits;
    }

    public List<Ouvrage> getOuvragesTraduits() {
        return ouvragesTraduits;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nombre d'ouvrages écrits=" + ouvragesEcrits.size() +
                ", nombre d'ouvrages traduits=" + ouvragesTraduits.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auteur auteur = (Auteur) o;
        return code.equals(auteur.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
