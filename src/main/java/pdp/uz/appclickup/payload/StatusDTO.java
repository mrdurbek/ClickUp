package pdp.uz.appclickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.Category;
import pdp.uz.appclickup.entity.Project;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.entity.enums.StatusType;


import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {

    @NotNull(message = "name bosh bolishi mumkin emas")
    private String name;

    @NotNull(message = "project bosh bolishi mumkin emas")
    private Integer project;

    @NotNull(message = "category bosh bolishi mumkin emas")
    private Integer category;

    @NotNull(message = "color bosh bolishi mumkin emas")
    private String color;

    @NotNull(message = "statusType bosh bolishi mumkin emas")
    private StatusType statusType;
}
