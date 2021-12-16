package ua.lits.l20spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "firstName can't be blank")
    private String firstName;

    @NotBlank(message = "lastName can't be blank")
    private String lastName;

    @NotNull
    @Min(value = 0, message = "Age must be >= 0")
    @Max(value = 200, message = "Age must be <= 200")
    private Integer age;

    @Min(value = 1, message = "Salary must be > 0 or null")
    private Integer salary;

    private String address;

    @Email
    private String email;

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}
