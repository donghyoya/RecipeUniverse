import { styled } from 'styled-components';

import appleImg from '../../assets/apple.jpg';

const IngredientItem = (props) => {

  const label = `${props.amount ?? 0}${props.unit ?? '개'}`
  return (
    <IngredientItemWrapper $active={props.isSelected}>
      <IngredientLabel>{label}</IngredientLabel>
      <IngredientIcon src={appleImg} alt={props.name}/>
    </IngredientItemWrapper>
  )
}

export default IngredientItem;

const IngredientItemWrapper = styled.div`
  width: 7rem;
  height: 7rem;
  border: 1px solid black;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  background-color: ${props => props.$active ? 'black' : 'white'};
`

const IngredientIcon = styled.img`
  width: 5rem;
  height: 5rem;
  /* border: 1px solid black; */
  border-radius: 2.5rem;
  background-color: white;
`

const IngredientLabel = styled.label`
  position: absolute;
  bottom: 0.5rem;
  right: 0.5rem;
  text-shadow: -1px 0px white, 0px 1px white, 1px 0px white, 0px -1px white;

`