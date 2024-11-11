import { styled } from 'styled-components'

import TagList from '../UI/TagList';
import { useState } from 'react';

const RecipeReviewItem = ({ reviewData }) => {

  const [isLiked, setIsLiked] = useState(reviewData.isLiked);

  const handleToggleLike = (e) => {
    e.stopPropagation();
    setIsLiked(prevIsLiked => !prevIsLiked);
  }

  return (
    <ReviewWrapper>
      <ReviewContent>
        <img alt='review'/>
        <div>
          <ReviewHeaderWrapper>
            <span>{reviewData.nickname}</span>
            <span>{reviewData.cookingTime}</span>
            <span>{reviewData.rating}</span>
          </ReviewHeaderWrapper>
          <p>{reviewData.text}</p>
        </div>
      </ReviewContent>
      <ReviewFooterWrapper>
        <TagList tags={reviewData.tags}/>
        <div>
          <span>{reviewData.dateCreated}</span>
          <button onClick={handleToggleLike}>{isLiked ? '♥' : '♡'} {reviewData.likeCount}</button>
        </div>
      </ReviewFooterWrapper>
    </ReviewWrapper>
  )
}

export default RecipeReviewItem;

const ReviewWrapper = styled.div`
  width: 100%;
  height: fit-content;
`

const ReviewHeaderWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;

  span:first-child {
    flex: 1;
    font-size: 1.6rem;
  }

  span:nth-child(2) {
    font-size: 1.6rem;
    color: gray;
  }

  span:last-child {
    font-size: 1.6rem;
    margin-left: 1rem;
    font-weight: bold;
  }
`

const ReviewContent = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 6rem;

  img {
    width: 6rem;
    aspect-ratio: 1 / 1;
    margin-right: 0.5rem;
  }

  p {
    font-size: 1.2rem;
  }

  & > div {
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    padding: 0.5rem;
  }
`

const ReviewFooterWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding: 0.5rem 0;
  width: 100%;
  box-sizing: border-box;
  
  span {
    font-size: 1.2rem;
    margin-right: 0.5rem;
  }

  button {
    font-size: 1.6rem;
    background-color: transparent;
    border: none;
    font-family: 'Interop';
  }
`
