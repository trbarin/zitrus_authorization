package br.com.zitrus.authorization.controller;

import br.com.zitrus.authorization.model.Solicitacao;
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

@Controller
public class ValidacaoController {

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

    @RequestMapping(value = "/criar-validacao", method = RequestMethod.GET)
    public String criarValidacao(ModelMap modelMap) {
        montarModelMap(modelMap);
        modelMap.addAttribute("solicitacao", new Solicitacao());
        return "validacao";
    }

    @RequestMapping(value = "/validar-regra", method = RequestMethod.POST)
    public String validarRegra(ModelMap modelMap, @Validated Solicitacao solicitacao, BindingResult result) {
        if (result.hasErrors()) {
            return "validacao";
        }

        montarModelMap(modelMap);

        final boolean sucesso = regraService.regraValida(isAdmin() ? null : getLoggedInUserName(), solicitacao.getCdProcedimento(), solicitacao.getIdade(), solicitacao.getSexo());

        ((Solicitacao) modelMap.getAttribute("solicitacao")).setSucesso(sucesso);

        return "validacao";
    }

}