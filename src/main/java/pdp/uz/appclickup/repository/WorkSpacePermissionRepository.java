package pdp.uz.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appclickup.entity.WorkSpacePermission;
import pdp.uz.appclickup.entity.enums.WorkspacePermissionName;

import java.util.List;
import java.util.Optional;

public interface WorkSpacePermissionRepository extends JpaRepository<WorkSpacePermission,Integer> {

    Optional<WorkSpacePermission> findByWorkSpaceRoleIdAndWorkspacePermissionName(Integer workSpaceRole_id, WorkspacePermissionName workspacePermissionName);

    List<WorkSpacePermission> findAllByWorkSpaceRole_NameAndWorkSpaceRole_WorkSpaceId(String workSpaceRole_name, Integer workSpaceRole_workSpace_id);
}
