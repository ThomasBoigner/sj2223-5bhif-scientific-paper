package at.spengergasse.sj22235bhifpos1scientificpaper.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "user_account")
public class User extends AbstractPersistable<Long> {

    @NotNull(message = "Username must not be null!")
    @NotBlank(message = "Username must not be blank!")
    @Column(unique = true)
    private String username;
    @NotNull(message = "email must not be null!")
    @NotBlank(message = "email must not be blank!")
    private String email;
    @NotNull(message = "password must not be null!")
    @NotBlank(message = "password must not be blank!")
    private String password;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Message> messages;

    @Version
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private int version;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private LocalDateTime creationTS;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull(message = "Token must not be null!")
    @NotBlank(message = "Token must not be blank")
    private String token;
}
