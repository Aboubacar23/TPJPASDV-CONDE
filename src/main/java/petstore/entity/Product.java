package petstore.entity;

import jakarta.persistence.*;
import petstore.enumerated.ProdType;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 255, nullable = false)
    private String code;
    @Column(length = 255, nullable = false)
    private String label;
    @Column(length = 255, nullable = false)
    private ProdType type;
    @Column(nullable = false)
    private double price;

    @ManyToMany
    @JoinTable(name = "Pro_Pet",
        joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ID_PET", referencedColumnName = "id")
    )
    private Set<PetStore> petStores = new HashSet<>();

    public Product() {
    }

    public Product(String code, String label, ProdType type, double price) {
        this.code = code;
        this.label = label;
        this.type = type;
        this.price = price;
    }

    public Product(String code, String label, double price) {
        this.code = code;
        this.label = label;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProdType getType() {
        return type;
    }

    public void setType(ProdType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<PetStore> getPetStores() {
        return petStores;
    }

    public void setPetStores(Set<PetStore> petStores) {
        this.petStores = petStores;
    }
}
