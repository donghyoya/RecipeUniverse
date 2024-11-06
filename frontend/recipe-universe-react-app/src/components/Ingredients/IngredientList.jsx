// props.ingredients = [{
//    name: string,
//    amount: string,
//    unit: string,
//    preference: number[0-4],
// }]

import { styled } from 'styled-components'
import IngredientItem from './IngreientItem'

const IngredientList = (props) => {
  return (
    <IngredientListWrapper>
      {props.ingredients.map((ingredient) => 
        <IngredientItem
          name={ingredient.name}
          amount={ingredient.amount}
          unit={ingredient.unit}
          key={ingredient.name}
        />)}
    </IngredientListWrapper>
  )
}

export default IngredientList;

const ITEM_SIZE = 7;
const GAP_SIZE = 1.5;

const getMaxWidth = (columns) => {
  return `${columns * (ITEM_SIZE + GAP_SIZE) - GAP_SIZE}rem`;
};

const IngredientListWrapper = styled.div`
  display: grid;
  grid-template-columns: repeat(4, ${ITEM_SIZE}rem);
  gap: ${GAP_SIZE}rem;
  justify-content: start;
  max-width: ${getMaxWidth(5)};

  @media (max-width: ${getMaxWidth(6)}) {
    grid-template-columns: repeat(6, ${ITEM_SIZE}rem);
    max-width: ${getMaxWidth(6)};
  }

  @media (max-width: ${getMaxWidth(5)}) {
    grid-template-columns: repeat(5, ${ITEM_SIZE}rem);
    max-width: ${getMaxWidth(5)};
  }

  @media (max-width: ${getMaxWidth(4)}) {
    grid-template-columns: repeat(4, ${ITEM_SIZE}rem);
    max-width: ${getMaxWidth(4)};
  }

  @media (max-width: ${getMaxWidth(3)}) {
    grid-template-columns: repeat(3, ${ITEM_SIZE}rem);
    max-width: ${getMaxWidth(3)};
  }

  @media (max-width: ${getMaxWidth(2)}) {
    grid-template-columns: repeat(2, ${ITEM_SIZE}rem);
    max-width: ${getMaxWidth(2)};
  }

  @media (max-width: ${getMaxWidth(1)}) {
    grid-template-columns: repeat(1, ${ITEM_SIZE}rem);
    max-width: ${getMaxWidth(1)};
  }
`