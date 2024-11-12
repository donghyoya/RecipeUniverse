import styled from 'styled-components';

import toggleUpIcon from '../assets/icons/chevron-up.svg';
import toggleDownIcon from '../assets/icons/chevron-down.svg';
import { useNavigate } from 'react-router-dom';
import CookingStep from '../components/SimpleCookingStepItem';

import { PageLayout, HeaderLayout } from '../styles/layout';
import LikeButton from '../components/UI/LikeButton';
import TagList from '../components/UI/TagList';
import RecipeReview from '../components/Recipe/RecipeReviewItem';

const RecipePage = () => {
  const navigate = useNavigate();

  const handleStart = () => {
    navigate('/prepare');
  };

  const recipeData = {
    info: {
      serving: 'X인분',
      cookingTime: 'X시간',
      difficulty: '쉬움',
      kcal: 'XXXXkcal',
      authorNickname: 'nickname',
      dateCreated: '20XX-MM-DD',
    },
    ingredients: [
      { item: '돼지고기', amount: '500g' },
      { item: '돼지고기', amount: '500g' },
      { item: '돼지고기', amount: '500g' },
    ],
    tags: ['태그', '태그'],
    cookingSteps: [
      {
        img: '/',
        time: '5분',
        description: '1. 감자 2개의 껍질을 벗기고, 한입 크기로 자른다.',
      },
      {
        img: '/',
        time: '5분',
        description: '1. 감자 2개의 껍질을 벗기고, 한입 크기로 자른다.',
      },
      {
        img: '/',
        time: '5분',
        description: '1. 감자 2개의 껍질을 벗기고, 한입 크기로 자른다.',
      },
      {
        img: '/',
        time: '5분',
        description: '1. 감자 2개의 껍질을 벗기고, 한입 크기로 자른다.',
      },
      {
        img: '/',
        time: '5분',
        description: '1. 감자 2개의 껍질을 벗기고, 한입 크기로 자른다.',
      },
    ],
  };

  const reviewData = [
    {
      nickname: '닉네임',
      cookingTime: '30분',
      rating: '★5.0',
      dateCreated: '20YY-MM-DD',
      tags: ['태그', '태그'],
      text: '맛있어요',
      likeCount: 3,
    },
    {
      nickname: '닉네임',
      cookingTime: '30분',
      rating: '★5.0',
      dateCreated: '20YY-MM-DD',
      tags: ['태그', '태그'],
      text: '맛있어요',
      likeCount: 3,
    },
  ];

  return (
    <PageLayout>
      <HeaderLayout>
        <h1>레시피 이름</h1>
        <LikeButton />
      </HeaderLayout>

      <IngredientLayout>
        <img alt="recipe" />
        <div>
          <h2>재료</h2>
          {recipeData.ingredients.map((ingredient, index) => (
            <p key={index}>{`${ingredient.item} ${ingredient.amount}`}</p>
          ))}
        </div>
      </IngredientLayout>

      <RecipeBadges>
        {`${recipeData.info.serving} | 
          ${recipeData.info.cookingTime} | 
          ${recipeData.info.difficulty} | 
          ${recipeData.info.kcal}`}
      </RecipeBadges>
      <TagListWrapper>
        <TagList tags={recipeData.tags} />
      </TagListWrapper>
      <UserCard>
        <img alt="user" />
        <span>{recipeData.info.authorNickname}</span>
        <span>{recipeData.info.dateCreated}</span>
      </UserCard>

      <SectionWrapper>
        <div>
          <h3>영양성분</h3>
          <img src={toggleDownIcon} alt="toggle icon" />
        </div>
      </SectionWrapper>
      <SectionWrapper>
        <div>
          <h3>레시피</h3>
          <img src={toggleDownIcon} alt="toggle icon" />
        </div>
        {recipeData.cookingSteps.map((cookingStep, idx) => (
          <CookingStep
            key={idx}
            img={cookingStep.img}
            time={cookingStep.time}
            description={cookingStep.description}
          />
        ))}
      </SectionWrapper>
      <SectionWrapper>
        <div>
          <h3>후기 3개</h3>
          <img src={toggleUpIcon} alt="toggle icon" />
        </div>
        {reviewData.map((review, index) => (
          <RecipeReview key={index} reviewData={review} />
        ))}
      </SectionWrapper>
      <StartCookingButtonWrapper>
        <button onClick={handleStart}>요리 시작하기</button>
      </StartCookingButtonWrapper>
    </PageLayout>
  );
};

export default RecipePage;

const IngredientLayout = styled.div`
  display: flex;
  flex-direction: row;
  margin-top: 1rem;

  & > img {
    width: 50%;
    aspect-ratio: 2 / 3;
  }

  & > div {
    width: 50%;
    display: flex;
    flex-direction: column;
    padding: 1rem;

    & > h2 {
      margin: 0;
      margin-bottom: 1rem;
      font-size: 2rem;
    }

    & > p {
      margin: 0;
      font-size: 1.6rem;
    }
  }
`;

const RecipeBadges = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-start;
  padding: 0.5rem 0;
  box-sizing: border-box;
  font-size: 2rem;
`;

const UserCard = styled.div`
  position: relative;
  width: 100%;
  margin: 0.5rem 0;
  padding: 1.5rem;
  height: 6rem;
  border: 1px solid black;
  box-sizing: border-box;
  display: flex;
  align-items: center;

  & > img {
    width: 3.2rem;
    height: 3.2rem;
    border-radius: 50%;
    border: 1px solid black;
  }

  & > span:first-of-type {
    margin-left: 1.5rem;
    font-size: 2rem;
  }

  & > span:last-of-type {
    position: absolute;
    bottom: 0;
    right: 0;
    margin: 0.5rem;
    font-size: 1.2rem;
  }
`;

const SectionWrapper = styled.div`
  width: 100%;
  box-sizing: border-box;
  padding: 0 1rem;
  display: flex;
  flex-direction: column;

  & > div:first-of-type {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;

    & > h3 {
      font-size: 2rem;
      font-weight: 400;
    }
  }

  &:not(:first-child) {
    gap: 1rem;
  }
`;

const TagListWrapper = styled.div`
  padding: 0.5rem 0;
`;

const StartCookingButtonWrapper = styled.div`
  width: 100%;
  padding: 3rem;
  box-sizing: border-box;

  & > button {
    width: 100%;
    height: 5rem;
    border: 2px solid black;
    border-radius: 2.5rem;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2rem;
    font-weight: bold;
    background-color: white;
  }
`;
