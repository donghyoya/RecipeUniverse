import { useState } from 'react';
import styled from 'styled-components';

import RecipeList from '../components/Recipe/RecipeList';
import AdvancedSearchModal from '../components/Recipe/AdvancedSearchModal/AdvancedSearchModal';
import { getSelectedOptions } from '../components/Recipe/AdvancedSearchModal/utils';

import heartIcon from '../assets/icons/heart_filled.svg';
import searchIcon from '../assets/icons/search.svg';
import { PageLayout, HeaderLayout } from '../styles/layout';
import TagList from '../components/UI/TagList';

const initialFilterIds = {
  difficulty: [],
  time: [],
  servings: [],
};

const RecipeListPage = () => {
  const [isModalOpen, setModalOpen] = useState(false);
  const [selectedFilterIds, setSelectedFilterIds] = useState(initialFilterIds);
  const [selectedTagIds, setSelectedTagIds] = useState([]);
  const [selectedOrderId, setSelectedOrderId] = useState('latest');

  const handleOpenModal = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };

  const handleConfirmModal = (newFilterIds, newTagIds, newOrderId) => {
    setSelectedFilterIds(newFilterIds);
    setSelectedTagIds(newTagIds);
    setSelectedOrderId(newOrderId);
    handleCloseModal();
  };

  const toggleTag = {
    text: '필터',
    icon: 'sort',
    onClick: handleOpenModal,
    id: 'toggle',
  };

  const getSelectedOptionTags = () =>
    getSelectedOptions(selectedFilterIds, selectedTagIds, selectedOrderId);

  return (
    <PageLayout>
      {isModalOpen && (
        <AdvancedSearchModal
          onClose={handleCloseModal}
          onConfirm={handleConfirmModal}
          initialFilterIds={selectedFilterIds}
          initialTagIds={selectedTagIds}
          initialOrderId={selectedOrderId}
        />
      )}
      <HeaderLayout>
        <h1>레시피 검색</h1>
        <IconList>
          <img src={heartIcon} alt="icon" />
          <img src={searchIcon} alt="icon" />
        </IconList>
      </HeaderLayout>
      <TagListWrapper>
        <TagList tags={[toggleTag, ...getSelectedOptionTags()]} scrollable />
      </TagListWrapper>
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

const TagListWrapper = styled.div`
  width: 100%;
  height: 2.4rem;
  padding-bottom: 1rem;
  margin: 0.5rem 0;
  border-bottom: 0.1rem solid gray;
`;
