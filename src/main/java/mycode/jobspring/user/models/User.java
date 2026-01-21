package mycode.jobspring.user.models;

import jakarta.persistence.*;
import lombok.*;
import mycode.jobspring.masina.models.Masina;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="user")
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 255)
    private String nume;

    @Column(nullable = false,length = 255)
    private String prenume;

    @Column(nullable = false)
    private int varsta;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Masina> masini= new HashSet<>();

    public User() {


    }
    public void addMasina(Masina masina){
        masini.add(masina);
        masina.setUser(this);
    }

    @Override
    public String toString(){
        return "Nume: "+nume+" prenume: "+prenume+" varsta "+varsta+" masina ";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public Set<Masina> getMasini() {
        return masini;
    }

    public void setMasini(Set<Masina> masini) {
        this.masini = masini;
    }
}
