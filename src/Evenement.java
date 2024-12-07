import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Evenement {
    private String nom;
    private LocalDateTime dateHeure;
    private Bibliotheque lieu;
    private List<Auteur> auteursInvites;
    private List<Ouvrage> ouvragesConcernes;

    public Evenement(String nom, LocalDateTime dateHeure, Bibliotheque lieu) {
        this.nom = nom;
        this.dateHeure = dateHeure;
        this.lieu = lieu;
        this.auteursInvites = new ArrayList<>();
        this.ouvragesConcernes = new ArrayList<>();
    }

    public void ajouterAuteurInvite(Auteur auteur) {
        auteursInvites.add(auteur);
    }
    public void supprimerAuteurInvite(Auteur auteur) {
        auteursInvites.remove(auteur);
    }

    public void ajouterOuvrageConcerne(Ouvrage ouvrage) {
        ouvragesConcernes.add(ouvrage);
    }
    public void supprimerOuvrageConcerne(Ouvrage ouvrage) {
        ouvragesConcernes.remove(ouvrage);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Bibliotheque getLieu() {
        return lieu;
    }

    public void setLieu(Bibliotheque lieu) {
        this.lieu = lieu;
    }

    public List<Auteur> getAuteursInvites() {
        return auteursInvites;
    }

    public List<Ouvrage> getOuvragesConcernes() {
        return ouvragesConcernes;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "nom='" + nom + '\'' +
                ", dateHeure=" + dateHeure +
                ", lieu=" + lieu +
                ", nombre d'auteurs invités=" + auteursInvites.size() +
                ", nombre d'ouvrages concernés=" + ouvragesConcernes.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evenement evenement = (Evenement) o;
        return nom.equals(evenement.nom) && 
               dateHeure.equals(evenement.dateHeure) &&
               lieu.equals(evenement.lieu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, dateHeure, lieu);
    }
}
