package petstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import petstore.enumerated.FishLivEnv;

import java.util.Date;

@Entity
public class Fish extends Animal{
    @Column(nullable = false)
    private FishLivEnv livingEnv;

    public Fish(String couleur, Date birth) {
        super(couleur, birth);
    }

    public Fish(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }

    public Fish(String couleur, Date birth, FishLivEnv livingEnv) {
        super(couleur, birth);
        this.livingEnv = livingEnv;
    }

    public Fish() {
    }

    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }
}
