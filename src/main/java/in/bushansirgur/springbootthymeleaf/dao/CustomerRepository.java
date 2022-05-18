package in.bushansirgur.springbootthymeleaf.dao;

import in.bushansirgur.springbootthymeleaf.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Customer findByEmail(String email);

    public Customer findByUsername(String username);
}
