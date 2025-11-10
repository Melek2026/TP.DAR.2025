package corbaClient;

import corbaBanque.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.Properties;

public class BanqueClient {

    public static void main(String[] args) {
        try {
            System.out.println("=== DÉMARRAGE CLIENT CORBA ===");

            // 1. Initialisation ORB
            System.out.println("1. Initialisation ORB...");
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "900");
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            ORB orb = ORB.init(args, props);

            // 2. Connexion à l'annuaire
            System.out.println("2. Connexion à l'annuaire...");
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // 3. Récupération du service
            System.out.println("3. Recherche du service 'BanqueService'...");
            IBanqueRemote banqueService = IBanqueRemoteHelper.narrow(ncRef.resolve_str("BanqueService"));

            // 4. TESTS
            System.out.println("4. Test des opérations...");

            // Test Conversion
            System.out.println("\n📊 TEST CONVERSION:");
            double dt = banqueService.conversion(50.0f);
            System.out.println("50.0 € = " + dt + " DT");

            // Test Création Compte
            System.out.println("\n📝 TEST CRÉATION COMPTE:");
            Compte nouveauCompte = new Compte();
            nouveauCompte.code = 1001;
            nouveauCompte.solde = 1000.0f;
            banqueService.creerCompte(nouveauCompte);
            System.out.println("Compte 1001 créé avec 1000.0 €");

            // Test Consultation
            System.out.println("\n👀 TEST CONSULTATION:");
            Compte compte = banqueService.getCompte(1001);
            System.out.println("Compte " + compte.code + " : " + compte.solde + " €");

            // Test Versement
            System.out.println("\n💰 TEST VERSEMENT:");
            banqueService.verser(500.0f, 1001);
            compte = banqueService.getCompte(1001);
            System.out.println("Après versement : " + compte.solde + " €");

            System.out.println("\n✅ TESTS TERMINÉS AVEC SUCCÈS !");

        } catch (Exception e) {
            System.err.println("❌ ERREUR CLIENT: " + e);
            e.printStackTrace();
        }
    }
}
