package at.spengergasse.sj22235bhifpos1scientificpaper.config;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Message;
import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor

@Component
public class DemoData {
    private final MessageRepository messageRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        messageRepository.save(Message.builder()
                        .text("This is a test message")
                        .creationTime(LocalDateTime.now())
                        .creationTS(LocalDateTime.now())
                        .user(User.builder()
                                .creationTS(LocalDateTime.now())
                                .username("Domas Boghner")
                                .email("domas@gmail.com")
                                .password(passwordEncoder.encode("P@ssw0rd"))
                                .build())
                .build());
    }
}
