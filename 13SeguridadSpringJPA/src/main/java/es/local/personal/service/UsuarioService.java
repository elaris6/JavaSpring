package es.local.personal.service;

import es.local.personal.dao.UsuarioDAO;
import es.local.personal.domain.Rol;
import es.local.personal.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* Esta clase va a ser usada por SpringSecurity, por lo que el nombre del Bean en la
anotación "Service" debe respetar el literal "serDetailsSservice", para que se reconozca
como clase de gestión de usaurio.
Además se debe implementar la interfaz "UserDetailsService" para su manejo. */
@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    /* Se injecta la interfaz automimplementada por Spring para gestionar los usuarios. */
    @Autowired
    private UsuarioDAO usuarioDao;
    
    /* Se sobreescribe el método "loadUserByUsername" de la interfaz "UserDetailsService",
    para realizar las comprobaciones necesarias sobre el usuario. */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        /* Se recupera la entidad usaurio. */
        Usuario usuario = usuarioDao.findByUsername(username);
        
        /* Se comprueba si existe el usuario y en caso contrario se lanza una excepción. */
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        /* Se cargan los roles asociados al usuario recuperado. Para ello se cargan sobre
        una lista de tipo "GrantedAuthority", que es la clase usada por Spring para
        gestionar los roles.
        Así mismo es necesario convertir cada objeto de tipo "Rol", a un objeto de tipo
        "SimpleGrantedAuthority". */
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Rol rol: usuario.getRoles()){
            
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        /* Para devolver un objeto de tipo "UserDetails" tenemos que instanciar la clase
        "User" de Spring, pasando como argunmentos: username, password y roles, obtenidos
        en los pasos previos. */
        return new User(usuario.getUsername(),usuario.getPassword(),roles);
        
    }
    
    
}
