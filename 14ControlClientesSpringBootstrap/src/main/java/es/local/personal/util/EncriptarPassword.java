package es.local.personal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/* Clase custom para encriptar las password y guardarlas así en la BBDD.
Lo suyo sería usar una clase similar a esta para que al dar de alta los usuarios
se crease ya la password encriptada en lugar de introducirla manualmente.
Para el caso de ejemplo asumimos que esto fuera así, pero creamos nosotros la
password manualmente con esta clase. */
public class EncriptarPassword {

    public static void main(String[] args) {
        
        var password = "123";
        
        System.out.println("Password: " + password);
        System.out.println("Password encriptado: " + encriptarPassword(password));
        
        
    }
    
    public static String encriptarPassword(String password){
        
        /* Se crea una instancia de la clase de Spring que codifica los password. */
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        return encoder.encode(password);
    }
}
