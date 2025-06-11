package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.services.GruposService;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import io.cucumber.java.es.Dado;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EliminarGrupoSteps extends FastCucumberSteps {

    @Autowired
    private GruposService gruposService;

    @Dado("que existe un grupo con id {long}")
    public void queExisteUnGrupoConId(Long id) {
        Grupo grupo = gruposService.recuperar(id);

        assertNotNull(grupo);
        assertEquals(id, grupo.getId());
        assertEquals("Campamento", grupo.getNombre());
    }
}
