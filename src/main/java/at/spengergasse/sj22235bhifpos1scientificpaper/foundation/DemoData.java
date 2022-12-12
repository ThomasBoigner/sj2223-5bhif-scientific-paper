package at.spengergasse.sj22235bhifpos1scientificpaper.foundation;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Message;
import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor

@Component
public class DemoData {
    private final MessageRepository messageRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        messageRepository.save(Message.builder()
                        .text("This is a test message")
                        .creationTime(LocalDateTime.now())
                        .creationTS(LocalDateTime.now())
                        .user(User.builder()
                                .creationTS(LocalDateTime.now())
                                .username("Domas Boghner")
                                .email("kek@gmail.com")
                                .password("P@ssw0rd")
                                .build())
                .build());
    }
}
