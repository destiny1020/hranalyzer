package com.destiny1020.hranalyzer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.destiny1020.hranalyzer.domain.Employee;

@Configuration
@Import(RepositoryRestMvcConfiguration.class)
public class HRAnalyzerRestConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);

        config.exposeIdsFor(Employee.class);
    }

}
