package es.local.personal.dao;

import es.local.personal.domain.Persona;
import org.springframework.data.repository.CrudRepository;

/* Con la implementación más actual de SPRING, ya no hace necesaria la creación de una 
interfaz con las operaciones y una clase con la implementación del accesp en si.
Para sustiruir esto, se debe crear un interfaz que herede de "CrudRepository<E,T>",
que se encargará de gestionar las operaciones CRUD básicas. */
public interface PersonaDAO extends CrudRepository<Persona, Integer>{

    /* Aunque la herencia de "CrudRepository<E,T>" incluye todos los métodos CRUD básicos,
    es posible incluir los métodos personalizados que sean necesarios de forma adicional. */
}
