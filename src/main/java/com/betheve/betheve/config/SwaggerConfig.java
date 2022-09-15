package com.betheve.betheve.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI springShopOpenAPI() {

        /**
         * Swagger 접속 기본 URL : http://localhost:8080/swagger-ui/index.html (기본 경로를 사용하는
         * 경우 )
         * Api 정보 경로 : /v3/api-docs (기본 경로를 사용하는 경우) --> Explore 입력 후 조회
         */

        return new OpenAPI()
                .info(new Info()
                        .title("dGuider")
                        .description("dartmedia dGuider Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("test")
                        .url("https://springshop.wiki.github.org/docs"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName));
    }
}
