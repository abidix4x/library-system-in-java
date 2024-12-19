import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        printHeader("Initialisation de la bibliothèque");
        Bibliotheque biblio = new Bibliotheque("Bibliothèque Nationale");
        System.out.println("📚 Bibliothèque créée : " + biblio.getNom() + "\n");
        
        printHeader("Création des acteurs");
        Auteur hugo = new Auteur("A001", "Hugo", "Victor");
        Auteur zola = new Auteur("A002", "Zola", "Émile");
        Auteur camus = new Auteur("A003", "Camus", "Albert");
        Editeur gallimard = new Editeur("E001", "Gallimard");
        Editeur flammarion = new Editeur("E002", "Flammarion");
        System.out.println("👤 Auteurs créés :");
        System.out.println("   • " + hugo.getPrenom() + " " + hugo.getNom());
        System.out.println("   • " + zola.getPrenom() + " " + zola.getNom());
        System.out.println("   • " + camus.getPrenom() + " " + camus.getNom());
        System.out.println("\n📖 Éditeurs créés :");
        System.out.println("   • " + gallimard.getNom());
        System.out.println("   • " + flammarion.getNom() + "\n");

        printHeader("Création des ouvrages");
        Ouvrage miserables = new Ouvrage("978-2-07-040089-6", "Les Misérables", "Français", hugo, gallimard);
        Ouvrage germinal = new Ouvrage("978-2-08-070394-1", "Germinal", "Français", zola, flammarion);
        Ouvrage peste = new Ouvrage("978-2-07-040089-7", "La Peste", "Français", camus, gallimard);
        System.out.println("📚 Ouvrages créés :");
        System.out.println("   • " + miserables.getTitre() + " (ISBN: " + miserables.getIsbn() + ")");
        System.out.println("   • " + germinal.getTitre() + " (ISBN: " + germinal.getIsbn() + ")");
        System.out.println("   • " + peste.getTitre() + " (ISBN: " + peste.getIsbn() + ")\n");

        printHeader("Test des contrats d'édition");
        ContratEdition contratHugo = new ContratEdition(hugo, gallimard, miserables, 
            LocalDate.now().minusYears(1), LocalDate.now().plusYears(5), 0.15);
        ContratEdition contratZola = new ContratEdition(zola, flammarion, germinal,
            LocalDate.now(), LocalDate.now().plusYears(3), 0.12);
        ContratEdition contratCamus = new ContratEdition(camus, gallimard, peste,
            LocalDate.now().minusYears(2), LocalDate.now().minusMonths(1), 0.10);
        System.out.println("📝 État des contrats :");
        System.out.println("   • Contrat Hugo : " + (contratHugo.estValide() ? "✅ Valide" : "❌ Invalid"));
        System.out.println("   • Contrat Zola : " + (contratZola.estValide() ? "✅ Valide" : "❌ Invalid"));
        System.out.println("   • Contrat Camus : " + (contratCamus.estValide() ? "✅ Valide" : "❌ Invalid") + "\n");

        printHeader("Gestion du stock");
        biblio.ajouterOuvrage(miserables);
        biblio.ajouterOuvrage(germinal);
        biblio.ajouterOuvrage(peste);
        System.out.println("📦 État initial du stock : " + biblio.getStock().size() + " ouvrages");
        
        biblio.supprimerOuvrage(peste);
        System.out.println("📦 État après suppression : " + biblio.getStock().size() + " ouvrages");
        System.out.println("   • Ouvrages disponibles :");
        biblio.getStock().forEach(ouvrage -> 
            System.out.println("     - " + ouvrage.getTitre()));

        printHeader("Test des emprunts");
        Lecteur lecteur1 = new Lecteur("L001", "Dupont", "Jean");
        Lecteur lecteur2 = new Lecteur("L002", "Martin", "Sophie");
        Lecteur lecteur3 = new Lecteur("L003", "Bernard", "Marie");
        
        Emprunt empruntMiserables = null;
        Emprunt empruntGerminal = null;
        try {
            empruntMiserables = biblio.emprunterOuvrage(lecteur1, miserables);
            System.out.println("📚 Emprunt des Misérables réussi pour " + lecteur1.getNom());
            
            empruntGerminal = biblio.emprunterOuvrage(lecteur2, germinal);
            System.out.println("📚 Emprunt de Germinal réussi pour " + lecteur2.getNom());
            
            // Test d'emprunt d'un livre déjà emprunté
            biblio.emprunterOuvrage(lecteur3, miserables);
        } catch (EmpruntException e) {
            System.out.println("⚠️ Exception attendue: " + e.getMessage());
        }

        System.out.println("\n📊 État des emprunts :");
        System.out.println("   • Nombre total d'emprunts : " + biblio.getEmprunts().size());
        System.out.println("   • Emprunts de " + lecteur1.getNom() + " : " + 
            biblio.getEmpruntsPourLecteur(lecteur1).size());

        try {
            biblio.retournerOuvrage(empruntMiserables);
            System.out.println("\n✅ Retour des Misérables effectué avec succès par " + lecteur1.getNom());
            System.out.println("📚 Nombre d'ouvrages en stock après retour : " + biblio.getStock().size());
        } catch (RetourException e) {
            System.out.println("❌ Erreur lors du retour: " + e.getMessage());
        }

        printHeader("Test des commandes");
        try {
            Commande commande1 = new Commande(germinal, 3);
            biblio.passerCommande(commande1);
            System.out.println("📚 Commande passée: " + commande1.getQuantite() + " exemplaires de " + germinal.getTitre());
            
            // Attente artificielle pour éviter le conflit de date
            Thread.sleep(100);
            
            Commande commande2 = new Commande(miserables, 2);
            biblio.passerCommande(commande2);
            System.out.println("📚 Commande passée: " + commande2.getQuantite() + " exemplaires de " + miserables.getTitre());
            
            // Test de la règle: pas deux commandes du même ouvrage le même jour
            Commande commande3 = new Commande(germinal, 1);
            biblio.passerCommande(commande3);
        } catch (CommandeException | InterruptedException e) {
            System.out.println("⚠️ Exception attendue: " + e.getMessage());
        }

        System.out.println("\n📊 État des commandes :");
        System.out.println("   • Nombre total de commandes : " + biblio.getCommandes().size());

        printHeader("Test des avis");
        Avis avis1 = new Avis(lecteur1, germinal, 5, "Chef d'œuvre du naturalisme!");
        Avis avis2 = new Avis(lecteur2, germinal, 4, "Une fresque sociale puissante");
        Avis avis3 = new Avis(lecteur3, germinal, 3, "Un peu long mais intéressant");
        germinal.ajouterAvis(avis1);
        germinal.ajouterAvis(avis2);
        germinal.ajouterAvis(avis3);
        System.out.println("📊 Statistiques pour Germinal:");
        System.out.println("   • Note moyenne : " + germinal.getNoteMoyenne());
        System.out.println("   • Nombre d'avis : " + germinal.getAvis().size());

        printHeader("Test des événements");
        Evenement evenement1 = new Evenement("Café littéraire: Les grands classiques", 
            LocalDateTime.now().plusDays(15), biblio);
        evenement1.ajouterAuteurInvite(hugo);
        evenement1.ajouterAuteurInvite(zola);
        evenement1.ajouterOuvrageConcerne(miserables);
        evenement1.ajouterOuvrageConcerne(germinal);
        
        Evenement evenement2 = new Evenement("Lecture: La Peste", 
            LocalDateTime.now().plusDays(30), biblio);
        evenement2.ajouterAuteurInvite(camus);
        evenement2.ajouterOuvrageConcerne(peste);
        
        biblio.organiserEvenement(evenement1);
        biblio.organiserEvenement(evenement2);
        System.out.println("📅 Événements prévus : " + biblio.getEvenements().size());
        biblio.getEvenements().forEach(evt -> 
            System.out.println("   • " + evt.getNom() + " (" + 
                evt.getAuteursInvites().size() + " auteurs, " + 
                evt.getOuvragesConcernes().size() + " ouvrages)"));

        printHeader("Test de recherche");
        System.out.println("🔍 Recherche par ISBN '978-2-07-040089-6': " +
                (biblio.rechercherOuvrage("978-2-07-040089-6") != null ? "Trouvé" : "Non trouvé"));
        System.out.println("🔍 Recherche par titre 'Germinal': " +
                (biblio.rechercherOuvrage("Germinal") != null ? "Trouvé" : "Non trouvé"));
        System.out.println("🔍 Recherche d'un ouvrage inexistant: " +
                (biblio.rechercherOuvrage("Inexistant") != null ? "Trouvé" : "Non trouvé"));

        printHeader("Résumé final");
        System.out.println(biblio.toString());
    }

    private static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("    " + title);
        System.out.println("=".repeat(50));
    }
}
