package es.local.personal.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "rol")
public class Rol implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;
    
    @NotEmpty
    private String nombre;
    
    /* No creamos el atributo de usuario, pues para este ejercicio no tiene
    utilidad, pero para que la clase estuviese completa, debería existir. */
    /*@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario usuario;*/
    
}
