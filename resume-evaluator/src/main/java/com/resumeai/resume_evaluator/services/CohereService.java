package com.resumeai.resume_evaluator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CohereService {

    @Value("${cohere.api.key}")
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public double getSimilarity(String resumeText, String jobText) throws IOException {
        List<String> texts = List.of(
                resumeText.length() > 1000 ? resumeText.substring(0, 1000) : resumeText,
                jobText.length() > 1000 ? jobText.substring(0, 1000) : jobText
        );

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "embed-english-v3.0");
        payload.put("texts", texts);
        payload.put("input_type", "search_document"); // ← REQUIRED for v3.0


        String json = objectMapper.writeValueAsString(payload);

        Request request = new Request.Builder()
                .url("https://api.cohere.ai/v1/embed")
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Cohere-Version", "2022-12-06")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("❌ Cohere API error response: " + response.body().string());
                throw new IOException("Unexpected response " + response);
            }

            JsonNode root = objectMapper.readTree(response.body().string());
            List<Double> a = objectMapper.convertValue(root.get("embeddings").get(0), List.class);
            List<Double> b = objectMapper.convertValue(root.get("embeddings").get(1), List.class);
            return cosineSimilarity(a, b);
        }
    }


    private List<String> stripLongTexts(List<String> inputs) {
        return inputs.stream()
                .map(text -> text.length() > 1000 ? text.substring(0, 1000) : text)
                .toList();
    }

    private double cosineSimilarity(List<Double> a, List<Double> b) {
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < a.size(); i++) {
            dot += a.get(i) * b.get(i);
            normA += Math.pow(a.get(i), 2);
            normB += Math.pow(b.get(i), 2);
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
