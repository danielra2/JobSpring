package mycode.jobspring.user.repository;

import mycode.jobspring.masina.models.Masina;
import mycode.jobspring.user.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    @Override
    @EntityGraph(attributePaths = "masini")
    List<User> findAll();

    @EntityGraph(attributePaths = "masini")
    Optional<User> findById(long id);

    @EntityGraph(attributePaths = "masini")
    List<User> findByVarstaGreaterThan(int varsta);
    @EntityGraph(attributePaths = "masini")
    List<User> findByPrenume(String prenume);
    @EntityGraph(attributePaths = "masini")
    List<User> findByMasini_Marca(String marca);
    @EntityGraph(attributePaths = "masini")
    List<User> findByMasini_NumarKilometriLessThan(int numarKilometri);
    @Query("select u from User u where u.masini is empty")
    @EntityGraph(attributePaths = "masini")
    List<User> findUsersWithoutMasini();
    @EntityGraph(attributePaths = "masini")
    List<User> findByMasini_MarcaAndVarstaGreaterThan(String marca,int varsta);

    @EntityGraph(attributePaths = "masini")
    List<User> findByPrenumeStartingWith(String prefix);
    @EntityGraph(attributePaths = "masini")
    List<User> findByMasini_ModelIsNot(String model);
    @EntityGraph(attributePaths = "masini")
    List<User> findByMasini_NumarKilometriGreaterThanOrderByVarstaAsc(int numarKilometri);



    Optional<User>findByNumeAndVarsta(String nume,int varsta);
    Optional<User>findByNumeAndPrenume(String nume,String prenume);


}