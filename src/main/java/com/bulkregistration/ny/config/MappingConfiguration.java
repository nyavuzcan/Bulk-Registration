package com.bulkregistration.ny.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {

  @Bean
  public Mapper mapper() throws Exception {
    Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    return mapper;
  }
}