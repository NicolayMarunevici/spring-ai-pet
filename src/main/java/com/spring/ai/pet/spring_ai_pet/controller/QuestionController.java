package com.spring.ai.pet.spring_ai_pet.controller;

import com.spring.ai.pet.spring_ai_pet.model.Answer;
import com.spring.ai.pet.spring_ai_pet.model.GetCapitalRequest;
import com.spring.ai.pet.spring_ai_pet.model.Question;
import com.spring.ai.pet.spring_ai_pet.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

  private final OpenAIService openAIService;

  public QuestionController(OpenAIService openAIService) {
    this.openAIService = openAIService;
  }

  @PostMapping("/capital")
  public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
    return openAIService.getCapital(getCapitalRequest);
  }

  @PostMapping("/ask")
  public Answer askQuestion(@RequestBody Question question){
    return openAIService.getAnswer(question);
  }
}
