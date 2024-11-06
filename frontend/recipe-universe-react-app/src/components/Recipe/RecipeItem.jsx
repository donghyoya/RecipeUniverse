import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components'
import { useState } from 'react';

const RecipeItem = ({ recipeData }) => {  
  const [isLiked, setIsLiked] = useState(recipeData.isLiked);

  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/recipe/${recipeData.id}`);
  }

  const handleToggleLike = (e) => {
    e.stopPropagation();
    setIsLiked(prevIsLiked => !prevIsLiked);
  }

  return (
    <RecipeWrapper onClick={handleClick}>
      <ImageWrapper>
        <Image />
        <IconListWrapper>
        <IconWrapper>
          <Icon/>
          <IconLabel>{recipeData.difficulty}</IconLabel>
        </IconWrapper>
        <IconWrapper>
          <Icon/>
          <IconLabel>{recipeData.servings}</IconLabel>
        </IconWrapper>
        <IconWrapper>
          <Icon/>
          <IconLabel>{recipeData.cookingTime}</IconLabel>
        </IconWrapper>
      </IconListWrapper>
      <LikesWrapper>
        <button onClick={handleToggleLike}>{isLiked ? '♥' : '♡'}</button>
        <LikesLabel>{recipeData.likeCount}</LikesLabel>
      </LikesWrapper>
      <ReviewWrapper>
        <ReviewLabel>후기 {recipeData.reviewCount}개</ReviewLabel>
      </ReviewWrapper>
      <RatingsWrapper>
        <RatingLabel>{recipeData.rating}</RatingLabel>
      </RatingsWrapper>
      </ImageWrapper>
      <RecipeTitle>{recipeData.title}</RecipeTitle>
    </RecipeWrapper>
  )
}

export default RecipeItem;

const RecipeWrapper = styled.div`
  width: 50%;
  height: min-content;
  padding: 1rem;
  box-sizing: border-box;
`

const ImageWrapper =  styled.div`
  position: relative;
  border: 1px solid black;
  width: 100%;
  aspect-ratio: 1 / 1;
`

const Image = styled.img``

const IconListWrapper = styled.div`
  position: absolute;
  bottom: 0;
  left: 0;
  gap: 0.3rem;
  padding: 0.3rem;
  display: flex;
  flex-direction: row;
`

const IconWrapper = styled.div`
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Icon = styled.img``;

const IconLabel = styled.span`
  color: white;
  font-size: 0.8rem;
  font-weight: bold;
`

const LikesWrapper = styled.div`
  position: absolute;
  top: 0;
  right: 0;
  padding: 0.3rem;

  button {
    border: 0;
    background-color: transparent;
    font-family: 'Interop';
    font-size: 2rem;
    font-weight: bold;
  }
`

const LikesLabel = styled.span`
  font-size: 2rem;
  font-weight: bold;
`

const RatingsWrapper = styled.div`
  position: absolute;
  bottom: 0;
  right: 0;
  padding: 0.3rem;
`

const RatingLabel = styled.span`
  font-size: 2rem;
  font-weight: bold;
`

const ReviewWrapper = styled.div`
  position: absolute;
  bottom: 2.3rem;
  right: 0.3rem;
  display: flex;
  align-items: center;
  height: 2rem;
`

const ReviewLabel = styled.span`
  font-size: 1rem;
`

const RecipeTitle = styled.h2`
  margin: 0;
  margin-top: 1rem;
  font-size: 2rem;
  font-weight: bold;
`
