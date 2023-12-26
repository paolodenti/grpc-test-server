package com.github.paolodenti.grpctestserver;

import com.github.paolodenti.grpctestproto.HelloWorldRequest;
import com.github.paolodenti.grpctestproto.HelloWorldServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GrpcTestServerApplicationTests {

    @GrpcClient("grpc-server")
    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

    @Test
    void testServer() {

        var request = HelloWorldRequest.newBuilder()
                .setRequestMessage("Hello World from test")
                .build();
        var response = helloWorldServiceBlockingStub.helloWorld(request);
        assertTrue(response.getResponseMessage().startsWith("Replied to 'Hello World from test'"));
    }
}
