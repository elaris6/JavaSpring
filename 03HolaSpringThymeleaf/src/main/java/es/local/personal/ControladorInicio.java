package es.local.personal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* Spring es un contenedor de clases.
Para que Spring pueda reconocer automáticamente las clases que forman parte de la 
aplicación, será necesario que éstas se encuentren en el mismo paquete que la clase
principal de la aplicación, la que tiene la anotación "@SpringBootApplication".
También pueden estar en subpaquetes, siempre que éstos estén dentro del paquete
en el que está la clase principal.
Aunque lo que se ejecuta por detrás es la tecnología de los Servlets, Spring simplifica
mucho su uso y facilita la implementación automatizando la mayoría de esta tecnología.
Existen varias anotaciones que permiten definir componentes de Spring. Uno de ellos
es el del ejemplo "Controller", aunque hay otros como: RestController, Service... etc. */
/* La anotación "Slf4j" sirve para poder tener acceso al uso del logging, que por defecto
funcionará en modo INFO. */
@Controller
@Slf4j
public class ControladorInicio {
    
    /* Se crea un método sencillo al que se añade la anotación "GetMapping("path")",
    que indica que el método responderá a una llamada de tipo GET al path indicado.
    En este caso al ser un componente Spring de tipo "Controller", y no "RestController"
    como en el ejemplo inicial, devolverá un archivo HTML por defecto, con el nombre indicado
    en el return. */
    @GetMapping("/")
    public String inicio(){
        
        log.info("--- Ejecutando controlador Spring MVC.");
        
        return "index";
    }
}
