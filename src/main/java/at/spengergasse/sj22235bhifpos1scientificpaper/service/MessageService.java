package at.spengergasse.sj22235bhifpos1scientificpaper.service;

import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;


}
