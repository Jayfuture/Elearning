package in.bushansirgur.springbootthymeleaf.dao;

import in.bushansirgur.springbootthymeleaf.entity.Customer;
import in.bushansirgur.springbootthymeleaf.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Teacher findByEmail(String email);

    public Teacher findByUsername(String username);

}
