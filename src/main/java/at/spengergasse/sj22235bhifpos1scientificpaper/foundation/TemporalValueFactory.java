package at.spengergasse.sj22235bhifpos1scientificpaper.foundation;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TemporalValueFactory {
    public LocalDateTime now(){
        return LocalDateTime.now();
    }
}
