package com.recipe.universe.global.executer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ViewCreater implements CommandLineRunner {
    private final String dropRecipeViewSql = "DROP VIEW IF EXISTS recipe_sort_view;";
    private final JdbcTemplate jdbcTemplate;
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

    @Override
    public void run(String... args) throws Exception {
        createRecipeSortView();
    }

    private void createRecipeSortView(){
        jdbcTemplate.execute(dropRecipeViewSql);
        jdbcTemplate.execute(createRecipeViewSql);
    }
}
