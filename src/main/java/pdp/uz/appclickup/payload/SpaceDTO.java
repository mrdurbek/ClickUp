package pdp.uz.appclickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.Attachment;
import pdp.uz.appclickup.entity.Icons;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.entity.WorkSpace;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceDTO {

    @NotNull
    private String name;

    @NotNull
    private String color;

    @NotNull
    private Integer workSpaceId;


    @NotNull
    private Integer iconId;

    private Integer avatartId;

    @NotNull
    private Integer ownerId;

    @NotNull
    private String accessType;
}
