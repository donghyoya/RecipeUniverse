import { useState } from 'react';
import { styled } from 'styled-components'

const RatingSelector = () => {
  const ratings = [1, 2, 3, 4, 5];
  const [currentRating, setCurrentRating] = useState(0);
  const [hover, setHover] = useState(0);

  const selectRating = (rating) => {
    setCurrentRating(rating);
  }

  const handleHover = (rating) => {
    setHover(rating);
  }

  const handleLeave = () => {
    setHover(0);
  }

  return (
    <Container>
      {ratings.map(rating => {
        const fill = !!(rating <= currentRating);
        const filltrans = hover >= rating;
        const icon = fill || filltrans ? '★' : '☆';
        return (
          <Star 
            id={rating}
            key={rating}
            $fill={fill}
            $filltrans={filltrans}
            onClick={() => selectRating(rating)}
            onMouseEnter={() => handleHover(rating)}
            onMouseLeave={handleLeave}
          >
            {icon}
          </Star>
        );
      })}
    </Container>
  )
}

export default RatingSelector;

const Container = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 8rem;
  justify-content: center;
  align-items: center;
`

const Star = styled.div`
  font-size: 4rem;
  font-weight: bold;
  width: 4rem;
  height: 4rem;
  color: ${props => props.$filltrans ? 'rgba(0, 0, 0, 0.5)' : 'black'};
`