package at.spengergasse.sj22235bhifpos1scientificpaper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
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
    private User user;

    @Version
    private int version;
    private LocalDateTime creationTS;
    private String token;

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(text, message.text) && Objects.equals(creationTime, message.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text, creationTime);
    }
}
