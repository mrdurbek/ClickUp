package pdp.uz.appclickup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.TaskAttachment;

public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment,Integer> {
}
