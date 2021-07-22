package es.local.personal;

import es.local.personal.domain.Persona;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    /* Incluir atributos definidos en el fichero "application.properties", para poder
    luego hacer uso de los mismos en el proceso. */
    @Value("${index.mensaje}")
    private String mensajeProp;

    /* Se crea un método sencillo al que se añade la anotación "GetMapping("path")",
    que indica que el método responderá a una llamada de tipo GET al path indicado.
    En este caso al ser un componente Spring de tipo "Controller", y no "RestController"
    como en el ejemplo inicial, devolverá un archivo HTML por defecto, con el nombre indicado
    en el return. */
    @GetMapping("/")
    public String inicio(Model model) {

        log.info("--- Ejecutando controlador Spring MVC.");

        /* Para compartir datos entre el modelo de datos y las vistas, en lugar de usar directamente
        los objetos request y response, como se hace con el api de Servlets, se usará la clase
        "Model", que puede ser recibida directamente gracias a la anotación "GetMapping".
        La fábrica de Spring va a instanciar la clase Model y permitirá su uso directo en los
        métodos, como parte de las facilidades que aporta Spring. */
        var mensaje = "Hola mundo con thymeleaf";
        
        var persona1 = new Persona();
        persona1.setNombre("Israel");
        persona1.setApellido("Balboa");
        persona1.setEmail("mail@mail.com");
        persona1.setTelefono("666777888");
        
        var persona2 = new Persona();
        persona2.setNombre("Alicia");
        persona2.setApellido("Balcuesta");
        persona2.setEmail("ali@mail.com");
        persona2.setTelefono("666111222");
        
        var persona3 = new Persona();
        persona3.setNombre("Natalia");
        persona3.setApellido("Balcuesta");
        persona3.setEmail("nata@mail.com");
        persona3.setTelefono("666000999");
        
//        var personas = new ArrayList<>();
//        personas.add(persona1);
//        personas.add(persona2);
//        personas.add(persona3);

        /* Método alternativo para crear una lista de objetos*/
        var personas = Arrays.asList(persona1, persona2, persona3);
        //var personas = new ArrayList();

        /* De forma similar a añadir un atributo aun alcance en Servlets, se añade un atributo
        al modelo. */
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("mensajeProp", mensajeProp);
        model.addAttribute("persona", persona1);
        model.addAttribute("personas", personas);
        
        return "index";
    }
}
