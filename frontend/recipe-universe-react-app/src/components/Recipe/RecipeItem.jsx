import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import { useState } from 'react';

const RecipeItem = ({ recipeData }) => {
  const [isLiked, setIsLiked] = useState(recipeData.isLiked);

  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/recipe/${recipeData.id}`);
  };

  const handleToggleLike = e => {
    e.stopPropagation();
    setIsLiked(prevIsLiked => !prevIsLiked);
  };

  return (
    <RecipeWrapper onClick={handleClick}>
      <ImageWrapper>
        <img alt="recipe" />
        <IconListWrapper>
          <IconWrapper>
            <img alt="icon" />
            <span>{recipeData.difficulty}</span>
          </IconWrapper>
          <IconWrapper>
            <img alt="icon" />
            <span>{recipeData.servings}</span>
          </IconWrapper>
          <IconWrapper>
            <img alt="icon" />
            <span>{recipeData.cookingTime}</span>
          </IconWrapper>
        </IconListWrapper>
        <LikesWrapper>
          <button onClick={handleToggleLike}>{isLiked ? '♥' : '♡'}</button>
          <span>{recipeData.likeCount}</span>
        </LikesWrapper>
        <ReviewWrapper>
          <span>후기 {recipeData.reviewCount}개</span>
        </ReviewWrapper>
        <RatingsWrapper>
          <span>{recipeData.rating}</span>
        </RatingsWrapper>
      </ImageWrapper>
      <RecipeTitle>{recipeData.title}</RecipeTitle>
    </RecipeWrapper>
  );
};

export default RecipeItem;

const RecipeWrapper = styled.div`
  width: 50%;
  height: min-content;
  padding: 1rem;
  box-sizing: border-box;
`;

const ImageWrapper = styled.div`
  position: relative;
  border: 1px solid black;
  width: 100%;
  aspect-ratio: 1 / 1;

  & > img {
    width: 100%;
    height: 100%;
  }
`;

const IconListWrapper = styled.div`
  position: absolute;
  bottom: 0;
  left: 0;
  gap: 0.3rem;
  padding: 0.3rem;
  display: flex;
  flex-direction: row;
`;

const IconWrapper = styled.div`
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;

  & > span {
    color: white;
    font-size: 0.8rem;
    font-weight: bold;
  }

  & > img {
    &[src=''],
    &:not([src]) {
      visibility: hidden;
      width: 0;
      height: 0;
    }
  }
`;

const LikesWrapper = styled.div`
  position: absolute;
  top: 0;
  right: 0;
  padding: 0.3rem;

  & > button {
    border: 0;
    background-color: transparent;
    font-family: 'Interop';
    font-size: 2rem;
    font-weight: bold;
  }

  & > span {
    font-size: 2rem;
    font-weight: bold;
  }
`;

const RatingsWrapper = styled.div`
  position: absolute;
  bottom: 0;
  right: 0;
  padding: 0.3rem;

  & > span {
    font-size: 2rem;
    font-weight: bold;
  }
`;

const ReviewWrapper = styled.div`
  position: absolute;
  bottom: 2.3rem;
  right: 0.3rem;
  display: flex;
  align-items: center;
  height: 2rem;

  & > span {
    font-size: 1rem;
  }
`;

const RecipeTitle = styled.h2`
  margin: 0;
  margin-top: 1rem;
  font-size: 2rem;
  font-weight: bold;
`;
