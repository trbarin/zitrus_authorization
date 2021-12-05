package br.com.zitrus.authorization.service;

import br.com.zitrus.authorization.model.Regra;

import java.util.List;
import java.util.Optional;

public interface IRegraService {

    List<Regra> obterRegras();

    List<Regra> obterRegras(String usuario);

    Optional<Regra> obterRegra(Long id);

    Optional<Regra> obterRegra(Long id, String usuario);

    void salvarRegra(Regra regra);

    void excluirRegra(Regra regra);

    boolean regraValida(String usuario, Long cdProcedimento, Long idade, String sexo);
}
