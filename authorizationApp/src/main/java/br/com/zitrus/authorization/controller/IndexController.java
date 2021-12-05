package br.com.zitrus.authorization.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mostrarPaginaIncial(ModelMap modelMap) {
        montarModelMap(modelMap);
        return "index";
    }

    @RequestMapping(value = "/sobre", method = RequestMethod.GET)
    public String mostrarPaginaSobre(ModelMap modelMap) {
        montarModelMap(modelMap);
        return "sobre";
    }

    private void montarModelMap(ModelMap modelMap) {
        modelMap.put("usuario", getLoggedInUserName());
        modelMap.put("anonimo", isAnonimo());
        modelMap.put("admin", isAdmin());
    }

    private boolean isAnonimo() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) return false;
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) return false;
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ANONYMOUS"));
    }

    private boolean isAdmin() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) return false;
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().isEmpty()) return false;
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

}
