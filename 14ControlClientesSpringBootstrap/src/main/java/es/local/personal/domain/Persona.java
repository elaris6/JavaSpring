package es.local.personal.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


/* La anotación "Data" de "lombok", hace que se generen de forma automática los
requisitos de una clase de dominio para ser considerado un Java Bean.
Simplemente declarando los atributos de clase, la anotación se encargará de generar
el constructor vacío, los getter y setter necesarios, así como los métodos:
toString, hashCode y equals.
Esta anotación no tiene relación con el framework Spring. Es opcional usarla. */
@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    /* Añadiendo la dependencia, se pueden incluir anotaciones para realizar validación
    de atributos directamente en la clase de dominio. */
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    @Email
    private String email;
    
    private String telefono;
    
    @NotNull
    private Double saldo;

}
