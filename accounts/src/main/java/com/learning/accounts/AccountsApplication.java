package com.learning.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts Microservice REST API", description = "REST API for managing accounts in the microservice", version = "v1", contact = @Contact(name = "Virendra Kumar", email = "virendranitdgp@gmail.com", url = """
        https://www.learning.com"""), license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")

), externalDocs = @ExternalDocumentation(description = "External documentation for the Accounts Microservice", url = "https://docs.learning.com/accounts"))
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
