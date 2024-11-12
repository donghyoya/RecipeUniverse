import { useState } from 'react';
import styled from 'styled-components';

import RecipeList from '../components/Recipe/RecipeList';
import SearchRecipeModal from '../components/Recipe/SearchRecipe/SearchRecipeModal';

import heartIcon from '../assets/icons/heart_filled.svg';
import searchIcon from '../assets/icons/search.svg';
import sortIcon from '../assets/icons/sort.svg';
import filterIcon from '../assets/icons/filter.svg';
import { PageLayout, HeaderLayout } from '../styles/layout';

const RecipeListPage = () => {
  const [isModalOpen, setModalOpen] = useState(false);

  const handleOpenModal = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };

  return (
    <PageLayout>
      {isModalOpen && (
        <SearchRecipeModal onClose={handleCloseModal} onConfirm={() => {}} />
      )}
      <HeaderLayout>
        <h1>레시피 검색</h1>
        <IconList>
          <img src={heartIcon} alt="icon" />
          <img src={searchIcon} alt="icon" onClick={handleOpenModal} />
          <img src={sortIcon} alt="icon" />
          <img src={filterIcon} alt="icon" />
        </IconList>
      </HeaderLayout>
      <RecipeList />
    </PageLayout>
  );
};

export default RecipeListPage;

const IconList = styled.div`
  display: flex;
  flex-direction: row;
  gap: 0.5rem;
  justify-content: space-between;

  img {
    width: 3.6rem;
    height: 3.6rem;
  }

  img:first-child {
    padding: 0.5rem;
    width: 2.6rem;
    height: 2.6rem;
  }
`;
