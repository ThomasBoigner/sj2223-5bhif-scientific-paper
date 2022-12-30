package at.spengergasse.sj22235bhifpos1scientificpaper.service;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Message;
import at.spengergasse.sj22235bhifpos1scientificpaper.foundation.IAuthenticationFacade;
import at.spengergasse.sj22235bhifpos1scientificpaper.foundation.TemporalValueFactory;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.MessageRepository;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.UserRepository;
import at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www.forms.NewMessageForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final TemporalValueFactory temporalValueFactory;
    private final IAuthenticationFacade authenticationFacade;

    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Message createMessage(NewMessageForm form){
        return messageRepository.save(Message.builder()
                        .user(userRepository.findUserByEmail(authenticationFacade.getAuthentication().getName())
                                .orElseGet(() -> userRepository.findUserByGoogleId(authenticationFacade.getAuthentication().getName())
                                .orElseThrow(() -> new UsernameNotFoundException("Can't find logged in user!"))))
                        .text(form.getMessage())
                        .creationTime(temporalValueFactory.now())
                        .creationTS(temporalValueFactory.now())
                        .token(tokenService.createNanoId())
                .build());
    }
}
