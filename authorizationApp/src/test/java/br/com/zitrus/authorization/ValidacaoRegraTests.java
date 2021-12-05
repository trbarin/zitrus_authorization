package br.com.zitrus.authorization;

import br.com.zitrus.authorization.service.RegraService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidacaoRegraTests {

    @Autowired
    private RegraService regraService;

    @Test
    void contextLoads() {
        Assertions.assertThat(regraService).isNotNull();
    }

    @Test
    void testarPermitidos() {
        final boolean resultado1 = regraService.regraValida("admin", 4567L, 20L, "M"); // Permitido
        final boolean resultado2 = regraService.regraValida("admin", 6789L, 10L, "M"); // Permitido
        final boolean resultado3 = regraService.regraValida("admin", 1234L, 20L, "M"); // Permitido
        final boolean resultado4 = regraService.regraValida("admin", 4567L, 30L, "F"); // Permitido

        Assertions.assertThat(resultado1).isEqualTo(true);
        Assertions.assertThat(resultado2).isEqualTo(true);
        Assertions.assertThat(resultado3).isEqualTo(true);
        Assertions.assertThat(resultado4).isEqualTo(true);
    }

    @Test
    void testarNãoPermitidos() {
        final boolean resultado1 = regraService.regraValida("admin", 1234L, 10L, "M"); // Não permitido
        final boolean resultado2 = regraService.regraValida("admin", 6789L, 10L, "F"); // Não permitido
        final boolean resultado3 = regraService.regraValida("fulano", 4567L, 20L, "M"); // Não permitido, embora para o usuário admin fosse um resultado válido
        final boolean resultado4 = regraService.regraValida("cicrano", 4567L, 20L, "M"); // Não permitido, embora para o usuário admin fosse um resultado válido
        final boolean resultado5 = regraService.regraValida("beltrano", 4567L, 20L, "M"); // Não permitido, embora para o usuário admin fosse um resultado válido

        Assertions.assertThat(resultado1).isEqualTo(false);
        Assertions.assertThat(resultado2).isEqualTo(false);
        Assertions.assertThat(resultado3).isEqualTo(false);
        Assertions.assertThat(resultado4).isEqualTo(false);
        Assertions.assertThat(resultado5).isEqualTo(false);
    }
}
