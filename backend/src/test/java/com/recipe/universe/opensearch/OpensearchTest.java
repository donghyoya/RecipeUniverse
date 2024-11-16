package com.recipe.universe.opensearch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.test.context.ActiveProfiles;

@Disabled("오픈서치 점검 중")
@SpringBootTest
@ActiveProfiles("test")
public class OpensearchTest {
    @Autowired private ElasticsearchOperations operations;

    @Test
    public void createIndex() {
        // 인덱스가 존재하지 않으면 생성
        boolean indexExists = operations.indexOps(IndexCoordinates.of("dummy")).exists();
        if (!indexExists) {
            operations.indexOps(Dummy.class).create();
            operations.indexOps(Dummy.class).putMapping();
        }
    }

    @Document(indexName = "dummy")
    public static class Dummy {

        @Id
        private String id;
        private String name;
        private boolean isDelete;

        public Dummy(String id, String name, boolean isDelete) {
            this.id = id;
            this.name = name;
            this.isDelete = isDelete;
        }
    }
}
