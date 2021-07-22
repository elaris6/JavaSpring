package es.local.personal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* Spring es un contenedor de clases.
Para que Spring pueda reconocer automáticamente las clases que forman parte de la 
aplicación, será necesario que éstas se encuentren en el mismo paquete que la clase
principal de la aplicación, la que tiene la anotación "@SpringBootApplication".
También pueden estar en subpaquetes, siempre que éstos estén dentro del paquete
en el que está la clase principal.
Existen varias anotaciones que permiten definir componentes de Spring. Uno de ellos
es el del ejemplo "RestController", aunque hay otros como: Controller, Service... etc. */
/* La anotación "Slf4j" sirve para poder tener acceso al uso del logging, que por defecto
funcionará en modo INFO. */
@RestController
@Slf4j
public class ControladorInicio {
    
    /* Se crea un método sencillo al que se añade la anotación "GetMapping("path")",
    que indica que el método responderá a una llamada de tipo GET al path indicado.*/
    @GetMapping("/")
    public String inicio(){
        
        log.info("--- Mensaje de info a nivel de aplicación. Ejecutando controlador Rest.");
        log.debug("--- Mensaje de log a nivel debug del paquete. ");
        return "Hola mundo con Spring";
    }
}
