import styled from 'styled-components';

import bagIcon from '../../assets/icons/bag.svg';
import bagIconWhite from '../../assets/icons/bag_white.svg';
import bookIcon from '../../assets/icons/book.svg'
import bookIconWhite from '../../assets/icons/book_white.svg'
import chatIcon from '../../assets/icons/chat.svg';
import chatIconWhite from '../../assets/icons/chat_white.svg';
import historyIcon from '../../assets/icons/history.svg'
import historyIconWhite from '../../assets/icons/history_white.svg'
import userIcon from '../../assets/icons/user.svg'
import userIconWhite from '../../assets/icons/user_white.svg'
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { toggle } from '../../store/chatSlice';

const BottomNavigation = (props) => {
  const [active, setActive] = useState(-1);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const icons = [
    {icon: bagIcon, iconWhite: bagIconWhite, label: '냉장고', link: '/ingredients'}, 
    {icon: bookIcon, iconWhite: bookIconWhite, label: '레시피', link: '/recipe'},
    {icon: chatIcon, iconWhite: chatIconWhite, label: '채팅',},
    {icon: historyIcon, iconWhite: historyIconWhite, label: '기록', link: '/history'},
    {icon: userIcon, iconWhite: userIconWhite, label: '계정', link: '/account'}
  ];

  const handleClick = (index) => {
    if (index === 2) {
      dispatch(toggle());
      return;
    }
    setActive(index);
    navigate(icons[index].link);
  }

  return (
    <NavigationContainer>
      {icons.map((icon, index) => (
        <ButtonContainer key={index}>
          <Button onClick={() => {handleClick(index)}} $active={active === index}>
            <img src={active === index ? icon.iconWhite : icon.icon} alt='icon'/>
            <span>{icon.label}</span>
          </Button>
        </ButtonContainer>
      ))}
    </NavigationContainer>
  )
}

export default BottomNavigation;

const NavigationContainer = styled.div`
  display: flex;
  justify-content: space-around;
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 6rem;
  z-index: 1;
`;

const ButtonContainer = styled.div`
  display: flex;
  height: 100%;
  flex: 1;
  justify-content: center;
  align-items: center;
`;

const Button = styled.button`
  flex: 1;
  padding: 0;
  border: none;
  background-color: ${props => props.$active ? 'black' : 'white'};
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;

  img {
    width: 3.6rem;
    height: 3.6rem;
  }

  span {
    font-size: 1.0rem;
    color: ${props => props.$active ? 'white' : 'black'};
  }
`;