package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.TaskTag;

public interface TaskTagRepository extends JpaRepository<TaskTag,Integer> {
}
