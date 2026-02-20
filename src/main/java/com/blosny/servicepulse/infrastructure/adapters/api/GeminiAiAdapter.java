package com.blosny.servicepulse.infrastructure.adapters.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.blosny.servicepulse.domain.port.AiAnalysisPort;

import reactor.core.publisher.Mono;

@Component
public class GeminiAiAdapter implements AiAnalysisPort {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiAiAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<String> analyzeError(String errorMessage, String url) {
        String prompt = String.format(
                "Bir sistem mühendisi gibi şu hatayı analiz et. Site: %s, Hata: %s. " +
                        "Kısa ve öz bir çözüm önerisi sun.",
                url, errorMessage);

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt)))));

        return webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key="
                        + apiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    try {
                        List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                        return (String) parts.get(0).get("text");
                    } catch (Exception e) {
                        return "Analiz şu an alınamıyor.";
                    }
                })
                .onErrorReturn("Yapay zeka servisi şu an meşgul.");
    }
}