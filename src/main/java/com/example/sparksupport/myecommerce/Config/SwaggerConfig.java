package com.example.sparksupport.myecommerce.Config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.*;


@Configuration
@OpenAPIDefinition(
  info =@Info(
    title = "SparkSupport API",
    version = "Verions 1.0",
    contact = @Contact(
      name = "Asif Anwar", email = "asifanu1998@gmail.com", url = "https://t.me/AsifAnwar1"
    ),
    license = @License(
      name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
    ),
    termsOfService = "https://t.me/AsifAnwar1",
    description = "SparkSupport Swagger Client by Asif Anwar"
  )
)
public class SwaggerConfig {
}
