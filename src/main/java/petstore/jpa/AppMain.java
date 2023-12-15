package petstore.jpa;

import jakarta.persistence.*;
import petstore.entity.*;
import petstore.enumerated.FishLivEnv;
import petstore.enumerated.ProdType;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class AppMain {
    public static void main(String[] args)
    {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpStore"))
        {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            //la partie adresse
            Adresse adresse1 = new Adresse("80", "France Ville", "44500", "Nantes");
            Adresse adresse2 = new Adresse("25", "Agenet", "44300", "Nantes");
            Adresse adresse3 = new Adresse("25", "Agenet", "44300", "Nantes");
            em.persist(adresse1);
            em.persist(adresse2);
            em.persist(adresse3);

            //la partie perte store
            PetStore petStore1  = new PetStore("Pet Store 1", "Manager Name 1");
            petStore1.setAdresse(adresse1);
            PetStore petStore2  = new PetStore("Pet Store 2", "Manager Name 2");
            petStore2.setAdresse(adresse2);
            PetStore petStore3  = new PetStore("Pet Store 3", "Manager Name 3");
            petStore3.setAdresse(adresse3);


            em.persist(petStore1);
            em.persist(petStore2);
            em.persist(petStore3);

            //la partie de product
            Product product1 = new Product("CodeP-01", "Product 1", ProdType.FRESH_WATER ,102.0);
            Product product2 = new Product("CodeP-02", "Product 2", ProdType.SEA_WATER, 80.24);
            Product product3 = new Product("CodeP-03", "Product 3", ProdType.SEA_WATER, 45);
            em.persist(product1);
            em.persist(product2);
            em.persist(product3);
            product1.getPetStores().add(petStore1);
            product2.getPetStores().add(petStore1);
            product3.getPetStores().add(petStore1);


            //ajout de cat
            Cat cat = new Cat();
            Cat cat1 = new Cat();

            cat.setBirth(new Date());
            cat.setChipd("puce ");
            cat.setPetStore(petStore3);
            cat.setCouleur("Black");

            cat1.setBirth(new Date());
            cat1.setChipd("Milou ");
            cat1.setPetStore(petStore2);
            cat1.setCouleur("Green");

            em.persist(cat);
            em.persist(cat1);

            //ajout des fishs
            Fish fish = new Fish();
            fish.setBirth(new Date());
            fish.setCouleur("Green");
            fish.setPetStore(petStore1);
            fish.setLivingEnv(FishLivEnv.ACCESSORY);

            Fish fish1 = new Fish();
            fish1.setBirth(new Date());
            fish1.setCouleur("Yellow");
            fish1.setPetStore(petStore2);
            fish1.setLivingEnv(FishLivEnv.FOOD);

            Fish fish2 = new Fish();
            fish2.setBirth(new Date());
            fish2.setCouleur("Black");
            fish2.setPetStore(petStore2);
            fish2.setLivingEnv(FishLivEnv.FOOD);

            em.persist(fish);
            em.persist(fish1);
            em.persist(fish2);

            //affichier le petStore 2 et ses animaux
            PetStore petStoreOne = em.find(PetStore.class, 2);
            if (petStoreOne != null)
            {

                System.out.println("+++++++++++++++++++++++ Affichage de PetStore *******************");
                System.out.println("Name : "+petStoreOne.getName());
                System.out.println("Manager Name : "+petStoreOne.getManagerName());
                System.out.println("N°  "+petStoreOne.getAdresse().getNumber());
                System.out.println("Rue : "+petStoreOne.getAdresse().getStreet());
                System.out.println("Code postal : "+petStoreOne.getAdresse().getZipCode());
                System.out.println("City : "+petStoreOne.getAdresse().getCity());
                System.out.println("+++++++++++++++++++++++ les animaux de ce store *******************");

                // Charger les animaux du PetStore
                List<Animal> animals = em.createQuery("SELECT a FROM Animal a WHERE a.petStore = :petStore", Animal.class)
                        .setParameter("petStore", petStoreOne)
                        .getResultList();

                if (animals != null && !animals.isEmpty())
                {
                    for (Animal animal : animals)
                    {
                        System.out.println("Animal ID: " + animal.getId());
                        System.out.println("Couleur: " + animal.getCouleur());
                        System.out.println("Birth Date: " + animal.getBirth());

                        //affichage des classes filles
                        if (animal instanceof Cat) {
                            Cat c = (Cat) animal;
                            System.out.println("Chip ID: " + c.getChipd());
                        } else if (animal instanceof Fish) {
                            Fish f = (Fish) animal;
                            System.out.println("Living Environment: " + f.getLivingEnv());
                        }
                    }
                } else {
                    System.out.println("Pas d'animal dans ce store");
                }
                System.out.println("+++++++++++++++++++++++ FIN *******************");
            }else {
                System.out.println("Le petStore n'existe pas !");
            }

            transaction.commit();
        }
    }
}
