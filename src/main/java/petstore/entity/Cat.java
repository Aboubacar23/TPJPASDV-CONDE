package petstore.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Cat extends Animal {

    @Column(length = 255,nullable = false)
    private String chipd;

    public Cat(String chipd) {
        this.chipd = chipd;
    }

    public Cat(String couleur, Date birth, String chipd) {
        super(couleur, birth);
        this.chipd = chipd;
    }

    public Cat() {
    }

    public String getChipd() {
        return chipd;
    }

    public void setChipd(String chipd) {
        this.chipd = chipd;
    }
}
