package com.betheve.betheve.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI springShopOpenAPI() {

        /**
         * Swagger 접속 기본 URL : http://localhost:8097/swagger-ui/index.html (기본 경로를 사용하는
         * 경우 )
         * Api 정보 경로 : /v3/api-docs (기본 경로를 사용하는 경우) --> Explore 입력 후 조회
         */

        return new OpenAPI()
                .info(new Info()
                        .title("BeTheVe")
                        .description("채식 소개 서비스")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("test")
                        .url("https://springshop.wiki.github.org/docs"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
