package org.application;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Moedas(
    @JsonAlias("base_code") String moeda,
    @JsonAlias("target_code") String moedaConvertida, 
    @JsonAlias("conversion_rate") Double conversao) {
   
}
