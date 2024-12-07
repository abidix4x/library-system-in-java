import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Emprunt {
    private Lecteur lecteur;
    private Ouvrage ouvrage;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;

    public Emprunt(Lecteur lecteur, Ouvrage ouvrage) {
        this.lecteur = lecteur;
        this.ouvrage = ouvrage;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevue = dateEmprunt.plusDays(14); // Par exemple, 14 jours d'emprunt
    }

    public boolean estEnRetard() {
        return LocalDate.now().isAfter(dateRetourPrevue);
    }

    public double calculerPenalite() {
        if (!estEnRetard()) {
            return 0.0;
        }
        long joursRetard = ChronoUnit.DAYS.between(dateRetourPrevue, LocalDate.now());
        return joursRetard * 1.5; // 1.5 TND par jour de retard
    }

    public Lecteur getLecteur() {
        return lecteur;
    }

    public void setLecteur(Lecteur lecteur) {
        this.lecteur = lecteur;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "lecteur=" + lecteur +
                ", ouvrage=" + ouvrage +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetourPrevue=" + dateRetourPrevue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprunt emprunt = (Emprunt) o;
        return lecteur.equals(emprunt.lecteur) &&
                ouvrage.equals(emprunt.ouvrage) &&
                dateEmprunt.equals(emprunt.dateEmprunt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecteur, ouvrage, dateEmprunt);
    }
}
