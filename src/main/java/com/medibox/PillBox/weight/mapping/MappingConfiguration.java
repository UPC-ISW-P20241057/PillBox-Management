package com.medibox.PillBox.weight.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("weightMappingConfiguration")
public class MappingConfiguration {
  @Bean
  public WeightMapper medicineMapper() {
    return new WeightMapper();
  }
}
