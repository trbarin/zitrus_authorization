package br.com.zitrus.authorization.repository;

import br.com.zitrus.authorization.model.Regra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegraRepository extends JpaRepository<Regra, Long> {

    List<Regra> findAllByUsuario(String usuario);

    boolean existsByIdAndUsuario(Long id, String usuario);

    Optional<Regra> findByIdAndUsuario(Long id, String usuario);

    List<Regra> findAllByCdProcedimentoAndIdadeAndSexo(Long cdProcedimento, Long idade, String sexo);

    List<Regra> findAllByUsuarioAndCdProcedimentoAndIdadeAndSexo(String usuario, Long cdProcedimento, Long idade, String sexo);

}
