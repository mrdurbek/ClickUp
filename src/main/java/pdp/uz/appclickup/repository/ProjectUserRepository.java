package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.appclickup.entity.ProjectUser;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser,Integer> {
}
