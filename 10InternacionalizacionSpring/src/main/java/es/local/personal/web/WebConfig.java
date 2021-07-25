package es.local.personal.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/* Para incluir la internalización, se crea una clase en el paquete "web", como parte
del controlador en el modelo MVC. Esta clase debe implementar la interfaz "WebMvcConfigurer".
No es necesario hacer nada a nivel de las plantillas ni a nivel de los controladores
de aplicación, ya que Spring automáticamente realizará la sustitución de los valores
de los ficheros "messages" según el idioma seleccionado. */
@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    /* La anotación "Bean" hace que Spring inyecte una instancia del objeto "LocalResolver"
    al contenedor de Spring de forma automática, al llamar este método.  */
    @Bean
    public LocaleResolver localeResolver(){
       
        /* Se crea una instancia de "SessionLocaleResolver", que ayudará a gestionar
        la internacionalización. El paquete se llama "i18n" para abreviar la palabra
        "internacionalization" (i + 18 char + n).  */
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;        
    }
    
    /* Es necesario configurar un interceptor, para poder cambiar de idioma de manera
    dinámica. */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    /* Se registra el interceptor creado */
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        
        registro.addInterceptor(localeChangeInterceptor());
    }
}
