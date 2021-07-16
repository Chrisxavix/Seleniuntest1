package test_suite;

/* Imports necesarios para la automatización */
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
/* Trabaja con Chrome, se puede asignar otro desde el pom como Edge, Safari, Explorer, etc */
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import javax.swing.*;

public class TestForm {

    /* Los Test se ejecutan en orden alfabético si no se coloca algo

       El framework de Junit no tiene un atributo que permite asignar un orden

       @Test: se va a ejecutar una sola vez
       @Before: se va a ejecutar siempre antes de cada @Test
       @After: se va a ejecutar siempre después de cada @Test

       Solo debe haber un BeforeClass y AfterClass
       Tienen que ser un método estático
       @BeforeClass: se va a ejecutar antes de todos los @Test
       @AfterClass: se va a ejecutar después de todos los @Test */

    /* Llamada al WebDriver
       Variable del tipo WebDriver */
    private static WebDriver driver = null;

    @BeforeClass
    public static void abrirBrowser(){

        /* Especificar la ruta donde se encuentra el archivo descargado del Chrome Driver:
           https://chromedriver.chromium.org/

           Adaptarse a la versión actual del navegador con el driver descargado.
           Darle la dirección y el nombre del archivo descomprimido.
           En este caso es realizado desde Ubuntu 20, por tal motivo la ruta con inicio /home*/
        System.setProperty("webdriver.chrome.driver", "/home/ue0100066b/Documentos/Chris/TestingCourse/Extraido/Chrome-Driver/chromedriver");

        /* Trabajar con automatización en una página web donde va a enviar y recibir datos */
        driver = new ChromeDriver();
        /* Levantar el browser de manera minimizada, segundo plano xD */
        driver.manage().window().maximize();
        /* Agregar dentro del get la ruta asignada */
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void controlDatosInicio(){
        try {
            /* Busca en el DOM el id y envía valores */
            driver.findElement(By.id("firstName")).sendKeys("Maggot");
            driver.findElement(By.id("lastName")).sendKeys("Course");
            driver.findElement(By.id("userEmail")).sendKeys("maggot@gmail.com");
            driver.findElement(By.id("userNumber")).sendKeys("0123456789");

            /* Seleccionar un botón */
            WebElement radioBtnMale = driver.findElement(By.id("gender-radio-1"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", radioBtnMale);

            /* Seleccionar varios checkbox */
            WebElement chkSports = driver.findElement(By.id("hobbies-checkbox-1"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", chkSports);

            WebElement chkMusic = driver.findElement(By.id("hobbies-checkbox-3"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", chkMusic);

            /* Opción de fecha */
            Actions actions = new Actions(driver);
            /* Busco el Id del campo de la fecha */
            actions.moveToElement(driver.findElement(By.xpath("//*[@id='dateOfBirthInput']")));
            actions.click();
            /* Borrar desde el final, la fecha que tiene por defecto */
            actions.sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE)
                    .sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE)
                    .sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE)
                    .sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE)
                    .sendKeys(Keys.BACK_SPACE).sendKeys(Keys.BACK_SPACE);

            Thread.sleep(3000);

            actions.sendKeys("12-10-1998");
            actions.sendKeys(Keys.ESCAPE);
            /* Hay que activar la construcción */
            actions.build().perform();

            /* Opción de agregar archivos */
            WebElement updElement = driver.findElement(By.id("uploadPicture"));
            updElement.sendKeys("/home/ue0100066b/Documentos/Chris/TestingCourse/Extraido/AssetsProject/dbvisualizer.png");

            /* Selección */
            WebElement select1 = driver.findElement(By.id("react-select-3-input"));
            select1.sendKeys("NCR");
            /* Una vez que se tenga el valor se debe hacer tab, que pasa a la siguiente opción */
            select1.sendKeys(Keys.TAB);

            /* Selección */
            WebElement select2 = driver.findElement(By.id("react-select-4-input"));
            select2.sendKeys("Delhi");
            /* Una vez que se tenga el valor se debe hacer tab, que pasa a la siguiente opción */
            select2.sendKeys(Keys.TAB);

            /* Antes que haga el click se le da una espera o setTimeOut */
            Thread.sleep(2000);
            driver.findElement(By.id("submit")).click();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void controlDireccion(){
        driver.findElement(By.id("currentAddress")).sendKeys("El Valle");
    }

    @AfterClass
    public static void cerrarBrowser(){
        System.out.println("Test desde After Class");
        /* Cierra todas las ventanas */
        driver.close();
    }
}
