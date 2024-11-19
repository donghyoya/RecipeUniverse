package com.recipe.universe.global.executer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ViewCreater implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    private final String dropRecipeViewSql = "DROP VIEW IF EXISTS recipe_sort_view;";
    private final String createRecipeViewSql  = """
            create view recipe_sort_view as
            select r.recipe_id as recipe_id,
                   coalesce(avg(ur.rating), 0) as avg_rating,
                   count(ur.review_id) as review_size,
                   count(ul.id) like_size
            from recipe r
            left join user_review ur on r.recipe_id = ur.recipe_id
            left join user_like ul on r.recipe_id = ul.recipe_id
            group by r.
                    recipe_id;
    
            """;

    private final String dropUserReviewViewSql = "DROP VIEW IF EXISTS user_review_view;";
    private final String createUserReviewView = """
            create view user_review_view as
                select ur.review_id as review_id,
                count(ul.id) like_size
            from user_review ur
            left join user_like ul using (review_id)
            group by ur.review_id;
            """;

    @Override
    public void run(String... args) throws Exception {
        createRecipeSortView();
        createUserReviewView();
    }

    private void createRecipeSortView(){
        jdbcTemplate.execute(dropRecipeViewSql);
        jdbcTemplate.execute(createRecipeViewSql);
    }

    private void createUserReviewView(){
        jdbcTemplate.execute(dropUserReviewViewSql);
        jdbcTemplate.execute(createUserReviewView);
    }
}
