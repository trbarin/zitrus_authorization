package br.com.zitrus.authorization.service;

import br.com.zitrus.authorization.model.Regra;
import br.com.zitrus.authorization.repository.RegraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegraService implements IRegraService {

    @Autowired
    private RegraRepository regraRepository;

    @Override
    public List<Regra> obterRegras() {
        return regraRepository.findAll();
    }

    @Override
    public List<Regra> obterRegras(String usuario) {
        return regraRepository.findAllByUsuario(usuario);
    }

    @Override
    public Optional<Regra> obterRegra(Long id) {
        if (regraRepository.existsById(id)) {
            return regraRepository.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Regra> obterRegra(Long id, String usuario) {
        if (regraRepository.existsByIdAndUsuario(id, usuario)) {
            return regraRepository.findByIdAndUsuario(id, usuario);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void salvarRegra(Regra regra) {
        regraRepository.save(regra);
    }

    @Override
    public void excluirRegra(Regra regra) {
        regraRepository.delete(regra);
    }

    @Override
    public boolean regraValida(String usuario, Long cdProcedimento, Long idade, String sexo) {
        final List<Regra> regras;
        if (usuario == null) {
            regras = regraRepository.findAllByCdProcedimentoAndIdadeAndSexo(cdProcedimento, idade, sexo);
        } else {
            regras = regraRepository.findAllByUsuarioAndCdProcedimentoAndIdadeAndSexo(usuario, cdProcedimento, idade, sexo);
        }

        if (regras.isEmpty()) return false;

        return regras.stream().allMatch(Regra::getPermitido);
    }
}
