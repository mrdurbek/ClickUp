package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
