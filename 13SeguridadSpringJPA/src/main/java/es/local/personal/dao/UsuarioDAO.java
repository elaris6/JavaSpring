package es.local.personal.dao;

import es.local.personal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/* De forma similar a la clase "CrudRepository<E,T>", la clase "JpaRepository<E,T>"
permite que Spring implemente de manera automática los métodos CRUD estándar, para
que solo se deban implementar los más específicos que requiera la aplicación.
La clase "JpaRepository<E,T>" a su vez implementa la "CrudRepository<E,T>" y añade
mejoras.
https://www.baeldung.com/spring-data-repositories */
public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{
    
    /* Aunque la herencia de "JpaRepository<E,T>" incluye todos los métodos CRUD básicos,
    es posible incluir los métodos personalizados que sean necesarios de forma adicional. */
    
    
    public Usuario findByUsername(String username);
    
}
