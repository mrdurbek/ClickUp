package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByNameAndIdNot(String name, Integer id);
}
