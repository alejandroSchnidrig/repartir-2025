package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.services.GruposService;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import ar.com.grupoesfera.repartir.exceptions.GrupoNoEncontradoException;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EliminarGrupoSteps extends FastCucumberSteps {

    @Autowired
    private GruposService gruposService;

    @Dado("que existe un grupo con id {long}")
    public void queExisteUnGrupoConId(Long id) {
        Grupo grupo = gruposService.recuperar(id);

        assertNotNull(grupo);
        assertEquals(id, grupo.getId());
    }

    @Cuando("el usuario elimina el grupo con id {long}")
    public void elUsuarioEliminaElGrupoConId(Long id) {
        gruposService.eliminar(id);
    }

    @Entonces("el grupo con id {long} ya no debería aparecer en la lista de grupos")
    public void elGrupoYaNoDeberiaEstarEnLaLista(Long id) {
        assertThrows(GrupoNoEncontradoException.class, () -> gruposService.recuperar(id));
    }

    @Cuando("se intenta eliminar el grupo con id {long}")
    public void seIntentaEliminarGrupoInexistente(Long id) {
        try {
            gruposService.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
