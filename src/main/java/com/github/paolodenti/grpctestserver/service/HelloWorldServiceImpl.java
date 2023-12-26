package com.github.paolodenti.grpctestserver.service;

import com.github.paolodenti.grpctestproto.HelloWorldRequest;
import com.github.paolodenti.grpctestproto.HelloWorldResponse;
import com.github.paolodenti.grpctestproto.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void helloWorld(HelloWorldRequest request, StreamObserver<HelloWorldResponse> responseObserver) {

        log.debug("Received request: {}", request);
        HelloWorldResponse response = HelloWorldResponse.newBuilder()
                .setResponseMessage("Replied to '%s' at %d".formatted(request.getRequestMessage(),
                        System.currentTimeMillis()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
