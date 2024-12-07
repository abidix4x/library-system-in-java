import java.util.ArrayList;
import java.util.List;

public class Lecteur {
    private String id;
    private String nom;
    private String prenom;
    private double totalPenalites;
    private boolean bloquer;
    private List<String> commentaires;

    public Lecteur(String id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.totalPenalites = 0;
        this.bloquer = false;
        this.commentaires = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getTotalPenalites() {
        return totalPenalites;
    }

    public void ajouterPenalite(double montant) {
        this.totalPenalites += montant;
    }

    public void setBloquer(boolean bloquer) {
        this.bloquer = bloquer;
    }

    public void ajouterCommentaire(String commentaire) {
        this.commentaires.add(commentaire);
    }

    @Override
    public String toString() {
        return "Lecteur{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecteur lecteur = (Lecteur) o;
        return id.equals(lecteur.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
