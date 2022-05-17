package in.bushansirgur.springbootthymeleaf.dao;

import in.bushansirgur.springbootthymeleaf.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{

}
