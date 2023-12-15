package petstore;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args)
    {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpStore"))
        {
            System.out.println("Connexion établie avec succès !");
        }
    }
}