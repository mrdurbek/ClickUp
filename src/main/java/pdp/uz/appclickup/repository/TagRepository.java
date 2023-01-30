package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Integer> {
}
