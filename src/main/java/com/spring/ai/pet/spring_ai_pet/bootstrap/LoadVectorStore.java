package com.spring.ai.pet.spring_ai_pet.bootstrap;

import com.spring.ai.pet.spring_ai_pet.config.VectorStoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class LoadVectorStore implements CommandLineRunner {

  private final VectorStore vectorStore;
  private final VectorStoreProperties vectorStoreProperties;

  @Override
  public void run(String... args) throws Exception {

    if (vectorStore.similaritySearch("Sportsman").isEmpty()) {
      vectorStoreProperties.getDocumentsToLoad().forEach(document -> {
        log.debug("Loading Vector Store ...");
        TikaDocumentReader documentReader = new TikaDocumentReader(document);
        List<Document> documents = documentReader.get();

        TextSplitter textSplitter = new TokenTextSplitter();
        List<Document> splitDocuments = textSplitter.apply(documents);

        vectorStore.add(splitDocuments);
      });
    }
    log.debug("Vector store loaded");
  }
}