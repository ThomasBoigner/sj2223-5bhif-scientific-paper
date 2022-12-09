package at.spengergasse.sj22235bhifpos1scientificpaper.service;

import at.spengergasse.sj22235bhifpos1scientificpaper.foundation.NanoIdFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class TokenService {
    private final NanoIdFactory nanoIdFactory;
    public final int DEFAULT_NANO_ID_LENGTH = 8;

    public String createNanoId(){
        return createNanoId(DEFAULT_NANO_ID_LENGTH);
    }

    public String createNanoId(int size){
        return nanoIdFactory.randomNanoId(size);
    }
}
