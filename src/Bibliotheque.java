import java.util.*;
import java.time.LocalDate;

public class Bibliotheque {
    private String nom;
    private Map<String, Ouvrage> stock; // Use Map to store Ouvrages by their ISBN
    private List<Emprunt> emprunts;
    private List<Commande> commandes;
    private List<Evenement> evenements;

    public Bibliotheque(String nom) {
        this.nom = nom;
        this.stock = new HashMap<>();
        this.emprunts = new ArrayList<>();
        this.commandes = new ArrayList<>();
        this.evenements = new ArrayList<>();
    }

    public void ajouterOuvrage(Ouvrage ouvrage) {
        stock.put(ouvrage.getIsbn(), ouvrage);
    }

    public void supprimerOuvrage(Ouvrage ouvrage) {
        stock.remove(ouvrage.getIsbn());
    }

    public Emprunt emprunterOuvrage(Lecteur lecteur, Ouvrage ouvrage) throws EmpruntException {
        if (!stock.containsKey(ouvrage.getIsbn())) {
            throw new EmpruntException("L'ouvrage n'est pas disponible dans cette bibliothèque.");
        }
        Emprunt emprunt = new Emprunt(lecteur, ouvrage);
        emprunts.add(emprunt);
        stock.remove(ouvrage.getIsbn());
        return emprunt;
    }

    public void retournerOuvrage(Emprunt emprunt) throws RetourException {
        if (!emprunts.contains(emprunt)) {
            throw new RetourException("Cet emprunt n'est pas enregistré dans cette bibliothèque.");
        }
        emprunts.remove(emprunt);
        stock.put(emprunt.getOuvrage().getIsbn(), emprunt.getOuvrage());
        if (emprunt.estEnRetard()) {
            appliquerPenalite(emprunt.getLecteur(), emprunt.calculerPenalite());
        }
    }

    public void passerCommande(Commande commande) throws CommandeException {
        if (commandeExistePourAujourdhui(commande.getOuvrage())) {
            throw new CommandeException("Une commande pour cet ouvrage a déjà été passée aujourd'hui.");
        }
        commandes.add(commande);
    }

    private boolean commandeExistePourAujourdhui(Ouvrage ouvrage) {
        return commandes.stream()
                .anyMatch(c -> c.getOuvrage().equals(ouvrage) && c.getDateCommande().equals(LocalDate.now()));
    }

    public void organiserEvenement(Evenement evenement) {
        evenements.add(evenement);
    }

    public void annulerEvenement(Evenement evenement) {
        evenements.remove(evenement);
    }

    private void appliquerPenalite(Lecteur lecteur, double montant) {
        if (lecteur == null) {
            throw new IllegalArgumentException("Le lecteur ne peut pas être null");
        }
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant de la pénalité doit être positif");
        }

        lecteur.ajouterPenalite(montant);

        // Enregistrer la pénalité dans l'historique du lecteur
        lecteur.ajouterCommentaire("Pénalité de " + montant + "TND appliquée le " + LocalDate.now());

        // Si le lecteur a trop de pénalités, bloquer temporairement son compte
        if (lecteur.getTotalPenalites() > 50) {
            lecteur.setBloquer(true);
            lecteur.ajouterCommentaire("Compte bloqué pour cause de pénalités excessives");
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Ouvrage> getStock() {
        return new ArrayList<>(stock.values());
    }

    public List<Emprunt> getEmprunts() {
        return new ArrayList<>(emprunts);
    }

    public List<Commande> getCommandes() {
        return new ArrayList<>(commandes);
    }

    public List<Evenement> getEvenements() {
        return new ArrayList<>(evenements);
    }

    public Ouvrage rechercherOuvrage(String critere) {
        // Normalize the criteria
        String normalizedCritere = critere.trim().toLowerCase();

        // Check if the criterion matches an ISBN
        Ouvrage ouvrage = stock.get(normalizedCritere);
        Ouvrage result=null;
//        System.out.println("ouvrage de test: "+ouvrage);
//        System.out.println(stock);
        if (ouvrage != null) {
            result=ouvrage;
        }else{

        // If not found by ISBN, search by title


        for (Ouvrage o : stock.values()) {
            if (o.getTitre().toLowerCase().equals(normalizedCritere)) {
                result=o;
                break;
            }
        }
        }
        return result;
    }

    public List<Emprunt> getEmpruntsPourLecteur(Lecteur lecteur) {
        return emprunts.stream()
                .filter(e -> e.getLecteur().equals(lecteur))
                .toList();
    }

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "nom='" + nom + '\'' +
                ", nombre d'ouvrages=" + stock.size() +
                ", nombre d'emprunts=" + emprunts.size() +
                ", nombre de commandes=" + commandes.size() +
                ", nombre d'événements=" + evenements.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bibliotheque that = (Bibliotheque) o;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
