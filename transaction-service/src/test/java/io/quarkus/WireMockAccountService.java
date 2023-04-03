package io.quarkus;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Collections;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockAccountService implements QuarkusTestResourceLifecycleManager {

    WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        stubFor(get(urlEqualTo("/accounts/1651351/balance"))
                .willReturn(aResponse()
                    .withHeader("Content-Type","application/json")
                    .withBody("435.145")
                ));

        stubFor(post(urlEqualTo("/accounts/1651351/transaction"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{}")
                )
        );

        return Collections.singletonMap("io.quarkus.services.AccountService/mp-rest/url",wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (wireMockServer != null){
            wireMockServer.stop();
        }

    }
}