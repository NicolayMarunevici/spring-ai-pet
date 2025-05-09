package com.spring.ai.pet.spring_ai_pet.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAIServiceImplTest {

  @Autowired
  OpenAIService openAIService;

  @Test
  void getAnswer(){
    String answer = openAIService.getAnswer("Tell me about Java");
    System.out.println("Got the answer");
    System.out.println(answer);
  }
}