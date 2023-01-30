package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.Status;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    boolean existsByNameAndUserId(String name, Integer user_id);
    boolean existsByNameAndIdNot(String name, Integer id);
}
