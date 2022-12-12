package gmarmari.demo.microservices.orders.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Orders API",
                version = "V01",
                description = "Manages  orders and products",
                contact = @Contact(
                        name = "Georgios",
                        url = "https://github.com/gmarmari/"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://opensource.org/licenses/MIT")
        )
)
public class OpenApiConfiguration {

}


