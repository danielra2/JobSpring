package mycode.jobspring.masina.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mycode.jobspring.user.models.User;

@Entity
@Table(name="masina")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Masina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 255)
    private String marca;

    @Column(nullable = false,length = 255)
    private String model;

    @Column(nullable = false)
    private int numarKilometri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    @Override
    public String toString(){
        return "Marca: "+marca+" Model: "+model+" NumarKilometri: "+numarKilometri;
    }








}
