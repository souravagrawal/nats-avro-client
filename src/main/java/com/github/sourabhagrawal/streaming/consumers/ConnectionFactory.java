package com.github.sourabhagrawal.streaming.consumers;

import com.github.sourabhagrawal.ConfigurationProvider;
import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionFactory {

    public ConnectionFactory(){
    }

    public static Connection getConnection() throws IOException {
        Options.Builder builder = new Options.Builder();
        Properties properties = ConfigurationProvider.getProperties();
        if (properties != null){
            builder = builder.properties(properties);
        }
        try {
            Options options = builder.maxReconnects(0).build();
            return Nats.connect(options);
        }
        catch (Exception e) {
            throw new IOException("Cannot connect to NATS server.", e);
        }
    }
}
