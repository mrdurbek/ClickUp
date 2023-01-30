package pdp.uz.appclickup.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.Attachment;
import pdp.uz.appclickup.entity.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkSpaceDTO {
    private Integer id;

    @NotNull(message = "name bosh bolmasin")
    private String name;

    @NotNull(message = "color bosh bolmasin")
    private String color;

    private Integer avatarId;

    private String initialLetter;

}
