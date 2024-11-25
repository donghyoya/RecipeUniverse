import { styled } from 'styled-components';
import { useState } from 'react';

import IngredientList from '../components/Ingredients/IngredientList';
import AddButton from '../components/Ingredients/AddButton';
import IngredientCartegory from '../components/Ingredients/IngredientCartegory';
import AddIngredientsModal from '../components/Ingredients/AddIngredientsModal';
import { PageLayout } from '../styles/layout';

const IngredientsPage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleOpenModal = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

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
  ];

  return (
    <Wrapper>
      {isModalOpen && <AddIngredientsModal onClose={handleCloseModal} />}
      <IngredientCartegory />
      <PageLayout>
        <IngredientListWrapper>
          <IngredientList ingredients={DUMMY_INGREDIENTS} />
        </IngredientListWrapper>
        <AddButtonWrapper>
          <AddButton onClick={handleOpenModal} />
        </AddButtonWrapper>
      </PageLayout>
    </Wrapper>
  );
};

export default IngredientsPage;

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  position: relative;
`;

const AddButtonWrapper = styled.div`
  position: absolute;
  bottom: 0.8rem;
  right: 0.8rem;
`;

const IngredientListWrapper = styled.div`
  padding: 1.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
`;
