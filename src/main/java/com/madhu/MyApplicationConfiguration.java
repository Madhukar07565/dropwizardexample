package com.madhu;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Madhukar Reddy
 *
 */
public class MyApplicationConfiguration extends Configuration {

    @JsonProperty
    @Getter
    private String template;

    @JsonProperty
    @Getter
    private String name;

    @Valid
    @NotNull
    @JsonProperty("database")
    @Getter
    private DataSourceFactory database = new DataSourceFactory();

    @Getter
    @JsonProperty
    private SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();

}
