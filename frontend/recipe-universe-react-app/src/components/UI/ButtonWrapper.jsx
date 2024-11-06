import styled from 'styled-components';
import Button from './Button';

// buttons = [{onClick: function, primary: boolean, children: string}]

const Buttons = (props) => {
  return (
    <ButtonWrapper>
        {props.buttons.map((button, idx) => (
          <Button onClick={button.onClick} primary={button.primary} key={idx}>{button.children}</Button>
        ))}
    </ButtonWrapper>
  )
}

export default Buttons;

const ButtonWrapper = styled.div`
  width: 100%;
  flex: 1;
  display: flex;
  flex-direction: row;
  gap: 2rem;
  padding: 1.6rem;
  box-sizing: border-box;
  align-items: end;
`