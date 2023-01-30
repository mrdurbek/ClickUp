package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    boolean existsByNameAndIdNot(String name, Integer id);
}
