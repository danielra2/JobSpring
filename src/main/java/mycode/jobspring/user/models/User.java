package mycode.jobspring.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mycode.jobspring.masina.models.Masina;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Data

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
    private Set<Masina> masini= new TreeSet<>();

    @Override
    public String toString(){
        return "Nume: "+nume+" prenume "+prenume+" varsta "+varsta+" masina "+masini;
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
