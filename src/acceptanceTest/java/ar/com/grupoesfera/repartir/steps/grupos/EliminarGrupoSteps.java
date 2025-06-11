package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.es.Dado;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

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

}
