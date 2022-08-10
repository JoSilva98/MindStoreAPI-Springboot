package MindStore.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeansInit {

    //metodo que retorna nova instancia de model mapper
    //qd vamos buscar como atributo em alguma classe ele instancia novo modelMapper (injeta la dentro)
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
