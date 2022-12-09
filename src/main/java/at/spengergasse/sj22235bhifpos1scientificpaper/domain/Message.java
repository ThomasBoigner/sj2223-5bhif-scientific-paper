package at.spengergasse.sj22235bhifpos1scientificpaper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "message")
public class Message extends AbstractPersistable<Long> {
    private String text;
    private LocalDateTime creationTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_message_user"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

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
