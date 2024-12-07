import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialisation de la bibliothèque
        System.out.println("=== Initialisation de la bibliothèque ===");
        Bibliotheque biblio = new Bibliotheque("Bibliothèque Nationale");
        System.out.println("Bibliothèque créée : " + biblio.getNom());

        // Création des acteurs
        System.out.println("\n=== Création des acteurs ===");
        Auteur hugo = new Auteur("A001", "Hugo", "Victor");
        Auteur zola = new Auteur("A002", "Zola", "Émile");
        Editeur gallimard = new Editeur("E001", "Gallimard");
        Editeur flammarion = new Editeur("E002", "Flammarion");
        
        // Création des ouvrages
        System.out.println("\n=== Création des ouvrages ===");
        Ouvrage miserables = new Ouvrage("978-2-07-040089-6", "Les Misérables", "Français", hugo, gallimard);
        Ouvrage germinal = new Ouvrage("978-2-08-070394-1", "Germinal", "Français", zola, flammarion);
        
        // Test des contrats d'édition
        System.out.println("\n=== Test des contrats d'édition ===");
        ContratEdition contratHugo = new ContratEdition(hugo, gallimard, miserables, 
            LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), 0.15);
        ContratEdition contratZola = new ContratEdition(zola, flammarion, germinal,
            LocalDate.now(), LocalDate.now().plusYears(3), 0.12);
        
        System.out.println("Contrat Hugo valide: " + contratHugo.estValide());
        System.out.println("Contrat Zola valide: " + contratZola.estValide());

        // Gestion du stock
        System.out.println("\n=== Gestion du stock ===");
        biblio.ajouterOuvrage(miserables);
        biblio.ajouterOuvrage(germinal);
        System.out.println("Nombre d'ouvrages en stock: " + biblio.getStock().size());

        // Création et test des lecteurs
        System.out.println("\n=== Test des emprunts ===");
        Lecteur lecteur1 = new Lecteur("L001", "Dupont", "Jean");
        Lecteur lecteur2 = new Lecteur("L002", "Martin", "Sophie");

        // Test des emprunts
        try {
            biblio.emprunterOuvrage(lecteur1, miserables);
            System.out.println("Emprunt des Misérables réussi pour " + lecteur1.getNom());
            
            // Tentative d'emprunt d'un livre déjà emprunté
            biblio.emprunterOuvrage(lecteur2, miserables);
        } catch (EmpruntException e) {
            System.out.println("Exception attendue: " + e.getMessage());
        }

        // Test des commandes
        System.out.println("\n=== Test des commandes ===");
        try {
            Commande commande1 = new Commande(germinal, 3);
            biblio.passerCommande(commande1);
            System.out.println("Commande passée: " + commande1.getQuantite() + " exemplaires de " + germinal.getTitre());
            
            // Test de la règle: pas deux commandes du même ouvrage le même jour
            Commande commande2 = new Commande(germinal, 2);
            biblio.passerCommande(commande2);
        } catch (CommandeException e) {
            System.out.println("Exception attendue: " + e.getMessage());
        }

        // Test des avis
        System.out.println("\n=== Test des avis ===");
        Avis avis1 = new Avis(lecteur1, germinal, 5, "Chef d'œuvre du naturalisme!");
        Avis avis2 = new Avis(lecteur2, germinal, 4, "Une fresque sociale puissante");
        germinal.ajouterAvis(avis1);
        germinal.ajouterAvis(avis2);
        System.out.println("Note moyenne de Germinal: " + germinal.getNoteMoyenne());

        // Test des événements
        System.out.println("\n=== Test des événements ===");
        Evenement evenement1 = new Evenement("Café littéraire: Les grands classiques", 
            LocalDateTime.now().plusDays(15), biblio);
        evenement1.ajouterAuteurInvite(hugo);
        evenement1.ajouterAuteurInvite(zola);
        evenement1.ajouterOuvrageConcerne(miserables);
        evenement1.ajouterOuvrageConcerne(germinal);
        biblio.organiserEvenement(evenement1);
        System.out.println("Événement créé: " + evenement1.getNom() + 
            " avec " + evenement1.getAuteursInvites().size() + " auteurs");

        // Test de recherche
//        System.out.println("\n=== Test de recherche ===");
//        System.out.println("Recherche 'mis': " +
//            biblio.rechercherOuvrages("mis").size() + " résultat(s)");
//        System.out.println("Recherche 'zola': " +
//            biblio.rechercherOuvrages("zola").size() + " résultat(s)");
    }
}
