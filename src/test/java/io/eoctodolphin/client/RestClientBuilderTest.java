package io.eoctodolphin.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestClientBuilderTest 
{

    @Test
    public void clientBuilderShouldConstruct() throws Exception
    {
        RestClientBuilder clientBuilder = RestClientBuilder.builder().timeoutMs(1000).build();
        assertEquals(-1, clientBuilder.getConnectionTimeoutMs());
        assertEquals(1000, clientBuilder.getTimeoutMs());
    }
}
