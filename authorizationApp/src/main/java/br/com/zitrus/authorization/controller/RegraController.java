package br.com.zitrus.authorization.controller;

import br.com.zitrus.authorization.model.Regra;
import br.com.zitrus.authorization.service.IRegraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RegraController {

    @Autowired
    private IRegraService regraService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/listar-regras", method = RequestMethod.GET)
    public String obterRegras(ModelMap modelMap) {
        montarModelMap(modelMap);
        modelMap.put("regras", isAdmin() ? regraService.obterRegras() : regraService.obterRegras(getLoggedInUserName()));
        return "listar-regras";
    }


    @RequestMapping(value = "/vizualizar-regra", method = RequestMethod.GET)
    public String obterRegra(@RequestParam Long id, ModelMap modelMap) {
        final Optional<Regra> regra = isAdmin() ? regraService.obterRegra(id) : regraService.obterRegra(id, getLoggedInUserName());
        if (regra.isPresent()) {
            montarModelMap(modelMap);
            modelMap.put("regra", regra.get());
            return "vizualizar-regra";
        } else {
            return "redirect:/regras";
        }
    }

    @RequestMapping(value = "/criar-regra", method = RequestMethod.GET)
    public String criarRegra(ModelMap modelMap) {
        montarModelMap(modelMap);
        modelMap.addAttribute("regra", new Regra(getLoggedInUserName()));
        return "vizualizar-regra";
    }

    @RequestMapping(value = "/salvar-regra", method = RequestMethod.POST)
    public String salvarRegra(ModelMap modelMap, @Validated Regra regra, BindingResult result) {
        if (result.hasErrors()) {
            return "vizualizar-regra";
        }

        if (regra.getUsuario() == null) regra.setUsuario(getLoggedInUserName());

        regraService.salvarRegra(regra);

        return "redirect:/listar-regras";
    }

    @RequestMapping(value = "/editar-regra", method = RequestMethod.GET)
    public String editarRegra(ModelMap modelMap, @RequestParam Long id) {
        final Optional<Regra> regra = regraService.obterRegra(id);
        if (regra.isPresent()) {
            montarModelMap(modelMap);
            modelMap.put("regra", regra.get());
            return "vizualizar-regra";
        } else {
            return "redirect:/listar-regras";
        }
    }

    @RequestMapping(value = "/excluir-regra", method = RequestMethod.GET)
    public String excluirRegra(ModelMap modelMap, @RequestParam Long id) {
        final Optional<Regra> regra = regraService.obterRegra(id);
        if (regra.isPresent()) {
            regraService.excluirRegra(regra.get());
        }
        return "redirect:/listar-regras";
    }
}