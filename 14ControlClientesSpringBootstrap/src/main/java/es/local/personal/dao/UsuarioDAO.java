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
    
    /* Ejemplo de método de búsqueda por un campo, usando las conveciones de Spring JPA.
    Hay bastantes convenciones ya creadas que permiten realizar queries sin necesidad de
    escribir el código JPQL. */
    
    /* Búsqueda por un campo concreto. El nombre del campo en el método deb ser exactamente
    igual que en la clase entidad.*/
    public Usuario findByUsername(String username);
    /* Búsqueda combinada por dos campos. */
    public Usuario findByUsernameAndPassword(String username, String password);
    /* Búsqueda por un campo que contenga un substring ignorando además las mayúsc/minusc. */
    public List<Usuario> findUsernameContainingIgnoreCase(String cadena);
    
    
    /* Definición de método con JPQL personalizado. */
    @Query("SELECT u FROM Usuario u WHERE u.username LIKE %:cadena%")
    public List<Usuario> buscarUsuarioPorTexto(@Param("cadena") String cadena);
    /* Misma consulta con parámetros ordenados. */
    //@Query("SELECT u FROM Usuario u WHERE u.username LIKE ?1%")
    //public List<Usuario> buscarUsuarioPorTexto(String cadena);
    
    
    /* Definición de método transaccional personalizado.
    Este método debe anotarse con "Transactional" and "Modifying", para que la operación
    se realice de manera consistente al ser una modificación de la BBDD. */
    @Transactional
    @Modifying
    @Query("DELETE FROM Usuario u WHERE u.password = ?1%")
    public void eliminarPorPassword(String password);
}
