package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority,Integer> {
}
