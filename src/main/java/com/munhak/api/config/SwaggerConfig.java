package com.munhak.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@OpenAPIDefinition(
        info = @Info(title = "문학동네 API 명세서",
                description = "문학동네 API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI(){
        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT Access Token is required to access this API"))
                );
    }

    @Bean
    GroupedOpenApi bookclubApi() {
        String[] pathsToMatch = {"/login"};
//        String[] pathsToExclude = {"/api/v1/o/docpa/**", "/api/v1/o/homepage/**", "/api/v1/o/localBookstore/**"};

        return GroupedOpenApi.builder()
                .group("북클럽 API")
                .pathsToMatch(pathsToMatch)
//                .pathsToExclude(pathsToExclude)
                .build();
    }

    @Bean
    GroupedOpenApi docpaApi() {
        String[] pathsToMatch = {"/login"};
//        String[] pathsToExclude = {"/api/v1/o/bookclub/**", "/api/v1/o/homepage/**", "/api/v1/o/localBookstore/**"};

        return GroupedOpenApi.builder()
                .group("독파 API")
                .pathsToMatch(pathsToMatch)
//                .pathsToExclude(pathsToExclude)
                .build();
    }

    @Bean
    GroupedOpenApi homepageApi() {
        String[] pathsToMatch = {"/login"};
//        String[] pathsToExclude = {"/api/v1/o/bookclub/**", "/api/v1/o/docpa/**", "/api/v1/o/localBookstore/**"};

        return GroupedOpenApi.builder()
                .group("홈페이지 API")
                .pathsToMatch(pathsToMatch)
//                .pathsToExclude(pathsToExclude)
                .build();
    }

    @Bean
    @Order(0)
    GroupedOpenApi localBookstoreApi() {
        String[] pathsToMatch = {"/localBookstore/login", "/api/v1/c/**", "/api/v1/o/bookstore/**", "/api/v1/o/localBookstore/**",  "/api/v1/u/localBookstore/**"};

        return GroupedOpenApi.builder()
                .group("동네서점 API")
                .pathsToMatch(pathsToMatch)
                .build();
    }

}
