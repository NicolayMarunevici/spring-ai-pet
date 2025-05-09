package com.spring.ai.pet.spring_ai_pet.services;

import com.spring.ai.pet.spring_ai_pet.model.Answer;
import com.spring.ai.pet.spring_ai_pet.model.GetCapitalRequest;
import com.spring.ai.pet.spring_ai_pet.model.Question;

public interface OpenAIService {

  Answer getCapital(GetCapitalRequest getCapitalRequest);
  String getAnswer(String question);
  Answer getAnswer(Question question);
  Answer getAnswerUsingRAG(Question question);
  Answer getAnswerUsingOpenAiFunctions(Question question);
}
