public class Avis {
    private Lecteur lecteur;
    private Ouvrage ouvrage;
    private int note;
    private String commentaire;

    public Avis(Lecteur lecteur, Ouvrage ouvrage, int note, String commentaire) {
        this.lecteur = lecteur;
        this.ouvrage = ouvrage;
        this.note = note;
        this.commentaire = commentaire;
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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "lecteur=" + lecteur +
                ", ouvrage=" + ouvrage +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avis avis = (Avis) o;
        return lecteur.equals(avis.lecteur) && ouvrage.equals(avis.ouvrage);
    }

    @Override
    public int hashCode() {
        return 31 * lecteur.hashCode() + ouvrage.hashCode();
    }
}
