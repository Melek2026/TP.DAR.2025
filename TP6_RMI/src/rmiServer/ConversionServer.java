package rmiServer;

import rmiService.ConversionImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConversionServer {
    public static void main(String[] args) {
        try {
            // Créer un registre RMI sur le port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Créer l'instance du service
            ConversionImpl conversionService = new ConversionImpl();

            // Enregistrer le service dans le registre
            registry.rebind("ConversionService", conversionService);

            System.out.println("Serveur RMI prêt...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}