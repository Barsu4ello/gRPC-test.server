package org.example;


import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends com.example.grpc.GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(com.example.grpc.GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<com.example.grpc.GreetingServiceOuterClass.HelloResponse> responseObserver) {

        for (int i = 0; i < 10_000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            com.example.grpc.GreetingServiceOuterClass.HelloResponse response = com.example.grpc.GreetingServiceOuterClass.HelloResponse
                    .newBuilder()
                    .setGreeting("Hello from server, " + request.getName())
                    .build();

            responseObserver.onNext(response);
        }


        responseObserver.onCompleted();
    }

}
