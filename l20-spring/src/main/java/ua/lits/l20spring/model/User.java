package ua.lits.l20spring.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"id"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Min(value = 0)
    private Integer age;

    private String address;

    @Email
    @Column(unique = true)
    private String email;

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}
