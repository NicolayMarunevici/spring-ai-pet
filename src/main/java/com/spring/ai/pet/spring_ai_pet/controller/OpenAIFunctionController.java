package com.spring.ai.pet.spring_ai_pet.controller;

import com.spring.ai.pet.spring_ai_pet.model.Answer;
import com.spring.ai.pet.spring_ai_pet.model.Question;
import com.spring.ai.pet.spring_ai_pet.services.OpenAIService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OpenAIFunctionController {

  private final OpenAIService openAIService;

  @PostMapping("/weather ")
  public Answer askQuestion(@RequestBody Question question){
    return openAIService.getAnswer(question);
  }
}
