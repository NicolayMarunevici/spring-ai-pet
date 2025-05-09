package com.spring.ai.pet.spring_ai_pet.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.ai.pet.spring_ai_pet.model.Answer;
import com.spring.ai.pet.spring_ai_pet.model.GetCapitalRequest;
import com.spring.ai.pet.spring_ai_pet.model.Question;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService{

  private final ChatModel chatModel;
  private final SimpleVectorStore vectorStore;

  @Value("classpath:templates/get-capital-prompt.st")
  private Resource getCapitalPrompt;

  @Value("classpath:templates/rag-prompt-template.st")
  private Resource ragPromptTemplate;

  @Autowired
  ObjectMapper objectMapper;


  @Override
  public Answer getAnswerUsingRAG(Question question) {
    List<Document> documents = vectorStore.similaritySearch(
        SearchRequest.builder().query(question.question()).topK(5).build());
    List<String> contentList = documents.stream().map(Document::getText).toList();

    PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
    Prompt prompt = promptTemplate.create(
        Map.of("input", question.question(), "documents", String.join("\n", contentList)));

    ChatResponse response = chatModel.call(prompt);

    return new Answer(response.getResult().getOutput().getText());
  }

  @Override
  public Answer getAnswer(Question question) {
    PromptTemplate promptTemplate = new PromptTemplate(question.question());
    Prompt prompt = promptTemplate.create();

    ChatResponse response = chatModel.call(prompt);

    return new Answer(response.getResult().getOutput().getText());
  }

  @Override
  public Answer getCapital(GetCapitalRequest capital) {
    PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
    Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capital.stateOrCountry()));
    ChatResponse response = chatModel.call(prompt);
    String responseString;
    try{
      JsonNode jsonNode = objectMapper.readTree(response.getResult().getOutput().getText());
      responseString = jsonNode.get("answer").asText();
    } catch (JsonProcessingException e){
      throw new RuntimeException(e);
    }

    return new Answer(responseString);
  }

  @Override
  public String getAnswer(String question) {
    PromptTemplate promptTemplate = new PromptTemplate(question);
    Prompt prompt = promptTemplate.create();

    ChatResponse response = chatModel.call(prompt);

    return response.getResult().getOutput().getText();
  }
}
