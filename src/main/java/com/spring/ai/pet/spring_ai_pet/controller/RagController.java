package com.spring.ai.pet.spring_ai_pet.controller;

import com.spring.ai.pet.spring_ai_pet.model.Answer;
import com.spring.ai.pet.spring_ai_pet.model.Question;
import com.spring.ai.pet.spring_ai_pet.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RagController {
  private final OpenAIService openAIService;

  public RagController(OpenAIService openAIService) {
    this.openAIService = openAIService;
  }

  @PostMapping("/rag")
  public Answer getAnswerUsingRag(@RequestBody Question question){
    return openAIService.getAnswerUsingRAG(question);
  }
}
