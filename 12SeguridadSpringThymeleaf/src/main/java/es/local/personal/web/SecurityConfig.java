package es.local.personal.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/* Clase de configuración de seguridad. Puede tener cualquier nombre.
Esta clase debe tener las anotaciones "Configuration" y "EnableWebSecurity", además
de heredar de la clase "WebSecurityConfigurerAdapter". */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    /* AUTENTICACION. Este método a sobreescribir se usará para agragar más usuarios y personalizar
    los usuarios  utilizar. A este concepto se le conoce como autenticación. */ 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        
        /* En este ejemplo se usarán usuarios creados directamente en memoria. Lo normal
        será recuperar los usuarios de BBDD, que se verá en ejemplo posterior.
        La password debe estar encriptada, pero se puede indicar el prefijo "{noop}", para
        forzar que se acepte en texto plano en este caso.
        Para los roles, Spring automáticamente añadirá el prefijo "ROLE_" a los nombres de
        rol indicados. */
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}admin123")
                    .roles("ADMIN","USER")
                .and()
                .withUser("user")
                    .password("{noop}user123")
                    .roles("USER");
                
    }
    
    /* AUTORIZACION. Este método a sobreescribir se usará para restringir las urls de la aplicación web.
    A este concepto se le conoce como autorización. */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
        /* Mediante el método "authorixeRequests", e indicando los paths concretos, con sus
        restricciones, se puede definir el control de acceso por url.
        La anotación "**" hace referencia a cualqueir path.
        Una vez añadida la restricción de paths, será necesario también indicar la página
        de login, para que se identifique y se permita el acceso a todos los usuarios. */
        http.authorizeRequests()
                .antMatchers("/editar/**", "/insertar/**", "/eliminar")
                    .hasRole("ADMIN")
                .antMatchers("/")
                    .hasAnyRole("USER", "ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/errores/403");
    }
}
