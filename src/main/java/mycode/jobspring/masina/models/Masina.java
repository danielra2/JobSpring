package mycode.jobspring.masina.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mycode.jobspring.user.models.User;

@Entity
@Table(name="masina")
@AllArgsConstructor
@Builder
@Data
public class Masina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 255)
    private String marca;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false,length = 255)
    private String model;

    @Column(nullable = false)
    private int numarKilometri;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Masina(String marca,String model,int price, int numarKilometri, User user) {
        this.marca = marca;
        this.model = model;
        this.numarKilometri = numarKilometri;
        this.price=price;
        this.user = user;
    }

    public Masina(){

    }
    @Override
    public String toString(){
        return "Marca: "+marca+" Model: "+model+" NumarKilometri: "+numarKilometri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumarKilometri() {
        return numarKilometri;
    }

    public void setNumarKilometri(int numarKilometri) {
        this.numarKilometri = numarKilometri;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
