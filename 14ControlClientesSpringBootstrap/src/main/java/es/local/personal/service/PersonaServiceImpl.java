package es.local.personal.service;

import es.local.personal.dao.PersonaDAO;
import es.local.personal.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* La anotación "Service" hace que Spring identifique esta clase como una clase de
servicio en el contenedor de Psring y se pueda injectar en el controlador. */
@Service
public class PersonaServiceImpl implements PersonaService{

    /* Anotación "Autowired" para hacer la injección de la clase "PersonaDAO" en esta
    clase de servicio. */
    @Autowired
    private PersonaDAO personaDao;
    
    /* La anotación "Transactional" se utiliza para que la gestión de "commit" y
    "rollback" se haga de manera automática, funcionando el método dentro de una 
    transacción.
    Si se indica el atributo "readOnly=true", se informa de que no se hacen cambios
    sobre el modelo. Si no se indica, se debe gestionar como transacción de BBDD.*/
    @Transactional(readOnly = true)
    @Override
    public List<Persona> listarPersonas() {

        return (List<Persona>) personaDao.findAll();
    }

    @Transactional
    @Override
    public void guardar(Persona persona) {
        
        personaDao.save(persona);
    }

    @Transactional
    @Override
    public void eliminar(Persona persona) {
        
        personaDao.delete(persona);
    }

    @Transactional(readOnly = true)
    @Override
    public Persona encontrarPersona(Persona persona) {
        
        /* La implementación de "findById" devuelve un objeto de tipo "Optional<Persona>",
        para poder gestionar el caso de error o de no devolver resultado.
        Para gestionar estos casos hay varios métodos, de los cuales elegimos el "orElse",
        para el que se indica el valor a devolver en caso de error o no resultado. */
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
