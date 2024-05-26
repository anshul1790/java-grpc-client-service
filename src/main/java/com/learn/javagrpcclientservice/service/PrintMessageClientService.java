package com.learn.javagrpcclientservice.service;


import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PrintMessageClientService {

    @GrpcClient("grpc-server")
    private com.grpc.print.message.service.PrintMessageServiceGrpc.PrintMessageServiceBlockingStub printMessageServiceBlockingStub;

    @GetMapping("printMessage/{message}")
    public String printMessage(@PathVariable String message){
        com.grpc.print.message.service.PrintMessageRequest printMessageRequest =
                com.grpc.print.message.service.PrintMessageRequest.newBuilder().setMessage(message).build();
        log.info("Sending message to GRPC Server : {}",message);
        com.grpc.print.message.service.ServerResponse serverResponse = printMessageServiceBlockingStub.printMessage(printMessageRequest);
        log.info("Received response from GRPC server : {}",serverResponse.getServerResponse());
        return serverResponse.getServerResponse();
    }

}
