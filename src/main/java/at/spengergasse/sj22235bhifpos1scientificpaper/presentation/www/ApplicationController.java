package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor

@Controller
@RequestMapping(ApplicationController.BASE_URL)
public class ApplicationController {

    public static final String BASE_URL = "";
    public static final String PATH_VAR_ID = "/{id}";
    public static final String ROUTE_INDEX = "/";
    public static final String ROUTE_SHOW = "/show" + PATH_VAR_ID;
    public static final String ROUTE_NEW = "/new";
    public static final String ROUTE_EDIT = "/edit" + PATH_VAR_ID;
    public static final String ROUTE_DELETE = "delete" + PATH_VAR_ID;

    
}
