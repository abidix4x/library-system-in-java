import java.util.List;
import java.util.ArrayList;

public class Ouvrage {
    private String isbn;
    private String titre;
    private String langue;
    private Auteur auteurOriginal;
    private List<Auteur> traducteurs;
    private Editeur editeurActuel;
    private List<Edition> editions;
    private List<Avis> avis;

    public Ouvrage(String isbn, String titre, String langue, Auteur auteurOriginal, Editeur editeurActuel) {
        this.isbn = isbn;
        this.titre = titre;
        this.langue = langue;
        this.auteurOriginal = auteurOriginal;
        this.editeurActuel = editeurActuel;
        this.traducteurs = new ArrayList<>();
        this.editions = new ArrayList<>();
        this.avis = new ArrayList<>();
    }

    public void ajouterTraducteur(Auteur traducteur) {
        traducteurs.add(traducteur);
    }
    public void supprimerTraducteur(Auteur traducteur) {
        traducteurs.remove(traducteur);
    }

    public void ajouterEdition(Edition edition) {
        editions.add(edition);
    }
    public void supprimerEdition(Edition edition) {
        editions.remove(edition);
    }

    public void ajouterAvis(Avis avis) {
        this.avis.add(avis);
    }
    public void supprimerAvis(Avis avis) {
        this.avis.remove(avis);
    }

    public double getNoteMoyenne() {
        if (avis.isEmpty()) {
            return 0;
        }
        return avis.stream().mapToInt(Avis::getNote).average().orElse(0);
    }

    //getters & setters

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public Auteur getAuteurOriginal() {
        return auteurOriginal;
    }

    public void setAuteurOriginal(Auteur auteurOriginal) {
        this.auteurOriginal = auteurOriginal;
    }

    public List<Auteur> getTraducteurs() {
        return traducteurs;
    }

    public Editeur getEditeurActuel() {
        return editeurActuel;
    }

    public void setEditeurActuel(Editeur editeurActuel) {
        this.editeurActuel = editeurActuel;
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    //toString

    @Override
    public String toString() {
        return "Ouvrage{" +
                "isbn='" + isbn + '\'' +
                ", titre='" + titre + '\'' +
                ", langue='" + langue + '\'' +
                ", auteurOriginal=" + auteurOriginal +
                ", nombre de traducteurs=" + traducteurs.size() +
                ", editeurActuel=" + editeurActuel +
                ", nombre d'éditions=" + editions.size() +
                ", note moyenne=" + getNoteMoyenne() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ouvrage ouvrage = (Ouvrage) o;
        return isbn.equals(ouvrage.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
