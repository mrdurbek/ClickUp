package pdp.uz.appclickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.enums.AddType;
import pdp.uz.appclickup.entity.enums.WorkSpaceRoleName;
import pdp.uz.appclickup.entity.enums.WorkspacePermissionName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceRoleDTO {
    private Integer id;

    private String name;

    private WorkSpaceRoleName extendsRole;

    private WorkspacePermissionName permissionName;

    private AddType addType;
}
