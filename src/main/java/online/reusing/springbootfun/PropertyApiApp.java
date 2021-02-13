package online.reusing.springbootfun;

import io.swagger.models.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PropertyApiApp {
    public static void main(String[] args) {
        SpringApplication.run(PropertyApiApp.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("online.reusing.springbootfun"))
                .build()
                .apiInfo(metaInfo());

    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Property API",
                "Properties CRUD",
                "1.0",
                "Terms of Service","Brent Reusing"
                ,
                "",
                ""
        );

        return apiInfo;
    }
}
