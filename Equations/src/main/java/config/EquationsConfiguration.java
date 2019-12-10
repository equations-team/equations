package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class EquationsConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    private Map<String, Map<String, String>> view;

    @JsonProperty("database")
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    @JsonProperty("database")
    public DataSourceFactory getDatabase() {
        return database;
    }

    @JsonProperty("views")
    public void setViewRenderConfiguration(Map<String, Map<String, String>> view) {
        this.view = view;
    }

    @JsonProperty("views")
    public Map<String, Map<String,String>> getViewRendererConfiguration() {
        return view;
    }
}
