package petstore.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String managerName;

    @ManyToMany(mappedBy = "petStores")
    private Set<Product> products = new HashSet<>();
    @OneToOne
    private Adresse adresse;
    @OneToMany(mappedBy = "petStore")
    private Set<Animal> animals = new HashSet<>();

    public PetStore(String name, String managerName, Set<Product> products) {
        this.name = name;
        this.managerName = managerName;
        this.products = products;
    }

    public PetStore() {
    }
    public PetStore(String name, String managerName) {
        this.name = name;
        this.managerName = managerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
}
