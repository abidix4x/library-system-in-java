import java.time.LocalDate;
import java.util.Objects;

public class ContratEdition {
    private Auteur auteur;
    private Editeur editeur;
    private Ouvrage ouvrage;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double pourcentageRoyalties;

    public ContratEdition(Auteur auteur, Editeur editeur, Ouvrage ouvrage, LocalDate dateDebut, LocalDate dateFin, double pourcentageRoyalties) {
        this.auteur = auteur;
        this.editeur = editeur;
        this.ouvrage = ouvrage;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.pourcentageRoyalties = pourcentageRoyalties;
    }

    public boolean estValide() {
        LocalDate aujourdhui = LocalDate.now();
        return !aujourdhui.isBefore(dateDebut) && !aujourdhui.isAfter(dateFin);
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getPourcentageRoyalties() {
        return pourcentageRoyalties;
    }

    public void setPourcentageRoyalties(double pourcentageRoyalties) {
        this.pourcentageRoyalties = pourcentageRoyalties;
    }

    @Override
    public String toString() {
        return "ContratEdition{" +
                "auteur=" + auteur +
                ", editeur=" + editeur +
                ", ouvrage=" + ouvrage +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", pourcentageRoyalties=" + pourcentageRoyalties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratEdition that = (ContratEdition) o;
        return Double.compare(that.pourcentageRoyalties, pourcentageRoyalties) == 0 &&
                auteur.equals(that.auteur) &&
                editeur.equals(that.editeur) &&
                ouvrage.equals(that.ouvrage) &&
                dateDebut.equals(that.dateDebut) &&
                dateFin.equals(that.dateFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auteur, editeur, ouvrage, dateDebut, dateFin, pourcentageRoyalties);
    }
}
