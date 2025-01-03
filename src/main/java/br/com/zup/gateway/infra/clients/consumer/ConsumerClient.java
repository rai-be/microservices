package br.com.zup.gateway.infra.clients.consumer;

import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ConsumerClient {

    @Autowired
    private WebClient webClient;
    private final String URL_BASE = "http://localhost:8081/consumer";

    public ConsumerResponseDTO registerConsumer(ConsumerRegisterDTO registerDTO) {
        return webClient
                .post()
                .uri(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerDTO)
                .retrieve()
                .bodyToMono(ConsumerResponseDTO.class)
                .block();
    }

    public ConsumerResponseDTO getConsumer(String consumerId) {
        return webClient
                .get()
                .uri(URL_BASE + "/" + consumerId)
                .retrieve()
                .bodyToMono(ConsumerResponseDTO.class)
                .block();
    }

    public ConsumerResponseDTO registerConsumerClient(ConsumerRegisterDTO registerDTO) {
        return null;
    }

    public void deleteConsumer(String consumerId) {
        webClient.delete()
                .uri(URL_BASE + "/" + consumerId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public ConsumerResponseDTO updateConsumerClient(String consumerId, ConsumerRegisterDTO registerDTO) {
        return webClient
                .put()
                .uri(URL_BASE + "/" + consumerId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerDTO)
                .retrieve()
                .bodyToMono(ConsumerResponseDTO.class)
                .block();
    }
}