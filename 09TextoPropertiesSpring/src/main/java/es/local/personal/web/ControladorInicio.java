package es.local.personal.web;

import es.local.personal.domain.Persona;
import es.local.personal.service.PersonaService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    
    /* De forma muy similar a la anotación "Inject" de Java2EE, la anotación "Autowired",
    injectará la clase del atributo sobre el que se use, permitiendo el acceso a todos
    los atributos y métodos ahí definidos. */
    @Autowired
    private PersonaService personaService;

    /* Se crea un método sencillo al que se añade la anotación "GetMapping("path")",
    que indica que el método responderá a una llamada de tipo GET al path indicado.
    En este caso al ser un componente Spring de tipo "Controller", y no "RestController"
    como en el ejemplo inicial, devolverá un archivo HTML por defecto, con el nombre indicado
    en el return. */
    @GetMapping("/")
    public String inicio(Model model) {

        log.info("--- Ejecutando controlador Spring MVC. Método inicio");

        var personas = personaService.listarPersonas();
        model.addAttribute("personas", personas);
        
        /* Se redirige al template "index.html" */
        return "index";
    }
    
    /* Al indicar a Spring que el método recibe un objeto de tipo "Persona", si no es
    recibido como atributo en la llamada al método, el framework buscará la clase en
    la aplicación y creará una instancia de dicha clase de forma autónoma.
    Además, de forma automática, esta instancia también se comparte dentro del alcance
    "request". */
    @GetMapping("/insertar")
    public String insertar(Persona persona){
        
        /* Se redirige al template "modificar.html" */
        return "modificar";
    }
    
    
    /* Para validar un objeto que tiene validaciones en la clase de dominio, se indica
    la anotación "Valid" sobre la clase recibida, y se incluye como parámetro de entrada
    un objeto de tipo "Errors", que permitirá hacer la gestión. */
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        
        /* Mediante el método "hasErrors" se comprueba si hay algún error. */
        if(errores.hasErrors()){
            
            log.info("Error en atributos persona: " );
            for(var err:errores.getAllErrors()){
                log.info("Error: " + err.getObjectName() + " - " + err.getDefaultMessage());
            }
            /* En caso de errores, se redirige al formulario modificar.html */
            return "modificar";
        }
        
        personaService.guardar(persona);
        
        /* Se redirige al path "/" */
        return "redirect:/";
    }
    
    /* Se recibe una petición de tipo GET, con un parámetro en el path, que se puede
    asociar directamente al atributo del objeto que se va a gestionar. Se debe nombrar
    exactamente igual.
    De esta forma, si el objeto "persona" recibido como agrumento del método ya existe
    en memoria para la request, Spring automáticamente asociará el atributo al objeto si
    es posible. */
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        
        /* Al tener ya cumplimentado por Spring el idPersona, podemos realizar directamente
        la recuperación de todos los atributos sobre la misma instancia y que esté así
        completa.*/
        persona = personaService.encontrarPersona(persona);
        
        /* Una vez recuperado el objeto completo, lo compartimos actualizando el objeto en
        el alcance de request.*/
        model.addAttribute("persona", persona);
        
        /* Se redirige al template "modificar.html" ya con el objeto "persona" completo en
        memoria dentro del alcance de request. */
        return "modificar";
    }
    
    /* En este caso, se envía el atributo "idPersona" como un query param (parámetro en la url).
    En el path de la anotación no se indica, pero Spring automáticamente reconocerá que se ha enviado
    el atributo perteneciente a la clase "Persona" y lo asociará al objeto recibido en la
    request ("persona"). */
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        
        /* Al tener ya cumplimentado por Spring el idPersona, podemos realizar directamente
        la recuperación de todos los atributos sobre la misma instancia y que esté así
        completa.*/
        persona = personaService.encontrarPersona(persona);
        
        /* Una vez recuperado el objeto completo, lo compartimos actualizando el objeto en
        el alcance de request.*/
        personaService.eliminar(persona);
        
        /* Se redirige al path "/" */
        return "redirect:/";
    }
    
}
