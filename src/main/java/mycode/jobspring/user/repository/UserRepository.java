package mycode.jobspring.user.repository;

import mycode.jobspring.masina.models.Masina;
import mycode.jobspring.user.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {


    @Override
    @EntityGraph(attributePaths = "masini")
    List<User> findAll();
}
