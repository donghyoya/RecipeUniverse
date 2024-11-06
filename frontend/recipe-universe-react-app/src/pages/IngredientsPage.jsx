import { styled } from 'styled-components';
import { useDispatch } from 'react-redux';

import IngredientList from '../components/Ingredients/IngredientList';
import AddButton from '../components/Ingredients/AddButton';
import { openModal } from '../store/modalSlice';
import IngredientCartegory from '../components/Ingredients/IngredientCartegory';

const IngredientsPage = () => {
  const dispatch = useDispatch();

  const DUMMY_INGREDIENTS = [
    { name: '사과', amount: 1, unit: '개' },
    { name: '바나나', amount: 1, unit: '개' },
    { name: '양파', amount: 1, unit: '개' },
    { name: '버섯', amount: 1, unit: '개' },
    { name: '피망', amount: 1, unit: '개' },
    { name: '두부', amount: 1, unit: '개' },
    { name: '두부1', amount: 1, unit: '개' },
    { name: '두부2', amount: 1, unit: '개' },
    { name: '두부3', amount: 1, unit: '개' },
  ]

  return (
    <IngredientPageWrapper>
      <IngredientCartegory />
      <IngredientListWrapper>
        <IngredientList ingredients={DUMMY_INGREDIENTS}/> 
      </IngredientListWrapper>
      <AddButtonWrapper>
        <AddButton onClick={() => dispatch(openModal())}/>
      </AddButtonWrapper>
    </IngredientPageWrapper>
  )
}

export default IngredientsPage;

const IngredientPageWrapper = styled.div`
  position: relative;
  display: flex;
  flex: 1;
  flex-direction: column;
`

const AddButtonWrapper = styled.div`
  position: absolute;
  bottom: 0.8rem;
  right: 0.8rem;
`

const IngredientListWrapper = styled.div`
  padding: 1.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
`