package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

public interface RedirectForwardSupport {
    default String redirect(String route){
        return "redirect:"+route;
    }

    default String forward(String route){
        return "forward:"+route;
    }
}
