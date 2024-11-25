import { styled } from 'styled-components';

import RecipeItem from './RecipeItem';

const RecipeList = () => {
  const recipeDataList = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10].map(idx => {
    return {
      id: idx,
      title: '레시피 이름' + idx,
      difficulty: '쉬움',
      servings: '1인분',
      cookingTime: '30분',
      rating: '★5.0',
      reviewCount: 3,
      likeCount: 3,
      isLiked: false,
    };
  });

  return (
    <RecipeListWrapper>
      {recipeDataList.map(recipeData => (
        <RecipeItem key={recipeData.id} recipeData={recipeData} />
      ))}
    </RecipeListWrapper>
  );
};

export default RecipeList;

const RecipeListWrapper = styled.div`
  display: flex;
  justify-content: flex-start;
  align-content: flex-start;
  flex-wrap: wrap;
`;
