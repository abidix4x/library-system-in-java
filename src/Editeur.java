import java.util.List;
import java.util.ArrayList;

public class Editeur {
    private String code;
    private String nom;
    private List<Ouvrage> ouvragesPublies;
    private List<ContratEdition> contratsEdition;

    public Editeur(String code, String nom) {
        this.code = code;
        this.nom = nom;
        this.ouvragesPublies = new ArrayList<>();
        this.contratsEdition = new ArrayList<>();
    }

    public void publierOuvrage(Ouvrage ouvrage) {
        ouvragesPublies.add(ouvrage);
    }
    public void supprimerOuvrage(Ouvrage ouvrage) {
        ouvragesPublies.remove(ouvrage);
    }

    public void ajouterContratEdition(ContratEdition contrat) {
        contratsEdition.add(contrat);
    }
    public void supprimerContratEdition(ContratEdition contrat) {
        contratsEdition.remove(contrat);
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

    public List<Ouvrage> getOuvragesPublies() {
        return ouvragesPublies;
    }

    public List<ContratEdition> getContratsEdition() {
        return contratsEdition;
    }

    @Override
    public String toString() {
        return "Editeur{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", nombre d'ouvrages publiés=" + ouvragesPublies.size() +
                ", nombre de contrats=" + contratsEdition.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editeur editeur = (Editeur) o;
        return code.equals(editeur.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
