import java.time.LocalDate;

public class Edition {
    private Ouvrage ouvrage;
    private Editeur editeur;
    private LocalDate datePublication;
    private int tirage;

    public Edition(Ouvrage ouvrage, Editeur editeur, LocalDate datePublication, int tirage) {
        this.ouvrage = ouvrage;
        this.editeur = editeur;
        this.datePublication = datePublication;
        this.tirage = tirage;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public int getTirage() {
        return tirage;
    }

    public void setTirage(int tirage) {
        this.tirage = tirage;
    }

    @Override
    public String toString() {
        return "Edition{" +
                "ouvrage=" + ouvrage +
                ", editeur=" + editeur +
                ", datePublication=" + datePublication +
                ", tirage=" + tirage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edition edition = (Edition) o;
        return ouvrage.equals(edition.ouvrage) && 
               editeur.equals(edition.editeur) &&
               datePublication.equals(edition.datePublication);
    }

    @Override
    public int hashCode() {
        return ouvrage.hashCode() + editeur.hashCode() + datePublication.hashCode();
    }
}
