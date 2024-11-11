import { useState } from 'react'
import { styled } from 'styled-components';

import heartIcon from '../../assets/icons/heart.svg';
import filledHeartIcon from '../../assets/icons/heart_filled.svg';

const LikeButton = (props) => {
  const [isPressed, setIsPressed] = useState(false);

  const handleToggle = () => {
    setIsPressed(isPressed => !isPressed);
  }

  return (
    <LikeButtonStyle onClick={handleToggle}>
      <img src={isPressed ? filledHeartIcon : heartIcon} alt='like icon'/>
    </LikeButtonStyle>
  )
}

export default LikeButton;

const LikeButtonStyle = styled.button`
  border: 0;
  background-color: transparent;
  width: 3.6rem;
  height: 3.6rem;
  display: flex;
  justify-content: center;
  align-items: center;

  img {
    width: 2.6rem;
    height: 2.6rem;
  }
`