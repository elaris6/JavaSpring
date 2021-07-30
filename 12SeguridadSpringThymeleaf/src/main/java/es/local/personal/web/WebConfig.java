package es.local.personal.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/* Clase de configuración WEB. Puede tener cualquier nombre.
Para incluir la internalización, se crea una clase en el paquete "web", como parte
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
    
    /* Una vez agregada la seguridad, como el path de inicio "/" está ya securizado, es
    necesario hacer el mappgin de la url del path por defecto. */
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        
        /* Si se deja así esta configuración, será necesario haber hecho ya login para visualizar
        la página de inicio.
        Para solucionar esto es necesario agregar una página de login personalizada, para que en
        caso de no haber hecho login, se nos muestre esta página. */
        registro.addViewController("/").setViewName("index");
        
        /* Se mapea el path de "/login" que no pasa por el controlador, para solucionar el punto
        indicado en la línea previa. */
        registro.addViewController("/login");
        
        /* De igual manera, se agrega el mapeo para la vista de errores de acceso. En caso contratrio
        si pasase por el controlador, el usuario no podría ver la página de error personializada que se
        ha creado. */
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
    
    
}
