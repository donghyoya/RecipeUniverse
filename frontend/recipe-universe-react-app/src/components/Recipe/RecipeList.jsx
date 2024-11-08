import { styled } from 'styled-components';
import RecipeItem from './RecipeItem';

const RecipeList = () => {

  const recipeDataList = [
    {
      id: 1,
      title: '레시피 이름1',
      difficulty: '쉬움',
      servings: '1인분',
      cookingTime: '30분',
      rating: '★5.0',
      reviewCount: 3,
      likeCount: 3,
      isLiked: false,
    },
    {
      id: 2,
      title: '레시피 이름2',
      difficulty: '쉬움',
      servings: '1인분',
      cookingTime: '30분',
      rating: '★5.0',
      reviewCount: 3,
      likeCount: 3,
      isLiked: false,
    },
  ];

  return (
    <RecipeListWrapper>
      {recipeDataList.map(recipeData => (
        <RecipeItem key={recipeData.id} recipeData={recipeData}/>
      ))}
    </RecipeListWrapper>
  )
}

export default RecipeList;

const RecipeListWrapper = styled.div`
  display: flex;
  justify-content: flex-start;
  align-content: flex-start;
  flex-wrap: wrap;
`