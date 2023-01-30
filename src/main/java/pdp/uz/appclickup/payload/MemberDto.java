package pdp.uz.appclickup.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.enums.AddType;
import pdp.uz.appclickup.entity.enums.WorkSpaceRoleName;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {

    private Integer id;

    private String fullName;

    private String email;

    private String roleName;

    private Timestamp lastActive;

    private Integer roleId;

    private AddType addType;//ADD,EDIT,REMOVE
}
