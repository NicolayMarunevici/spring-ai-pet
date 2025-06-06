package com.spring.ai.pet.spring_ai_pet.config;

import java.io.File;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class VectorStoreConfig {

  @Bean
  public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties){
    var store = SimpleVectorStore.builder(embeddingModel).build();
    var vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());

    if(vectorStoreFile.exists()){
      store.load(vectorStoreFile);
    } else {
      log.debug("Loading documents into vector store");
      vectorStoreProperties.getDocumentsToLoad().forEach(document -> {
        System.out.println("Loading document : " );
        TikaDocumentReader documentReader = new TikaDocumentReader(document);
        List<Document> docs = documentReader.get();
        TextSplitter textSplitter = new TokenTextSplitter();
        List<Document> splitDocs = textSplitter.apply(docs);
        store.add(splitDocs);
      });

      store.save(vectorStoreFile);
    }

    return store;
  }
}
