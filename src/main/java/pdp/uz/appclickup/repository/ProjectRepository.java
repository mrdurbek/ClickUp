package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.appclickup.entity.Project;


public interface ProjectRepository extends JpaRepository<Project,Integer> {
    boolean existsByNameAndIdNot(String name, Integer id);
}
