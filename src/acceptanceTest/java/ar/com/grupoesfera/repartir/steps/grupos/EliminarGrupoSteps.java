package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@DisplayName("Eliminar Grupo")
public class EliminarGrupoSteps extends CucumberSteps {

    @Dado("que el usuario inicia la aplicación")
    public void elUsuarioInicioAplicacion() {

        baseDeDatos.estaVacia();
        driver.navigate().to(url("/"));

        var wait = new WebDriverWait(driver, Duration.of(2, ChronoUnit.SECONDS));
        wait.until(visibilityOfElementLocated(By.id("iniciarDialog")));

        driver.findElement(By.id("usuarioInput")).sendKeys("ramon");
        var iniciarButton = driver.findElement(By.id("iniciarBienvenidaButton"));
        iniciarButton.click();

        wait.until(invisibilityOfElementLocated(By.id("iniciarDialog")));
    }

    @Y("el usuario crea un grupo de gastos llamado {string}")
    public void elUsuarioCreaUnGrupoDeGastosLlamado(String nombreGrupo) {
        var crearGruposButton = driver.findElement(By.id("crearGruposButton"));
        crearGruposButton.click();

        driver.findElement(By.id("nombreGrupoNuevoInput")).sendKeys(nombreGrupo);

        var miembrosInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
        miembrosInput.sendKeys("Oscar");
        miembrosInput.sendKeys(Keys.ENTER);
        miembrosInput.sendKeys("Maria");
        miembrosInput.sendKeys(Keys.ENTER);
        driver.findElement(By.id("guardarGrupoNuevoButton")).click();

        var wait = new WebDriverWait(driver, Duration.of(2, ChronoUnit.SECONDS));
        wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
    }

    @Cuando("el usuario hace click en el botón de eliminar del grupo {string}")
    public void elUsuarioHaceClickEnElBotonDeEliminarDelGrupo(String nombreGrupo) {
        WebElement tabla = driver.findElement(By.id("tablaGrupos"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));

        for (WebElement fila : filas) {
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            if (!columnas.isEmpty() && columnas.get(1).getText().equals(nombreGrupo)) {
                WebElement botonEliminar = fila.findElement(By.cssSelector("p-button[id^='eliminarGrupoButton'] button"));
                botonEliminar.click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensajesToast")));
                return;
            }
        }

        throw new NoSuchElementException("No se encontró el grupo con nombre: " + nombreGrupo);
    }

}
