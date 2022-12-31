package at.spengergasse.sj22235bhifpos1scientificpaper.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

//tag::GoogleUserInfo[]
@Data
@AllArgsConstructor
@Builder
public class GoogleUserInfo {

    private Map<String, Object> attributes;

    public String getId() {
        return (String) attributes.get("sub");
    }

    public String getName(){
        return (String) attributes.get("name");
    }

    public String getEmail(){
        return (String) attributes.get("email");
    }
}
//end::GoogleUserInfo[]