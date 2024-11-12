import { styled } from 'styled-components';

const Button = props => {
  return (
    <ButtonLayout $primary={props.primary} onClick={props.onClick}>
      {props.children}
    </ButtonLayout>
  );
};

export default Button;

const ButtonLayout = styled.button`
  border: 1px solid black;
  height: 4rem;
  background-color: ${props => (props.$primary ? 'black' : 'white')};
  font-weight: ${props => (props.$primary ? 'bold' : 'normal')};
  color: ${props => (props.$primary ? 'white' : 'black')};
  flex: 1;
  border-radius: 1rem;
`;
