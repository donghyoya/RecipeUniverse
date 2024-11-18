import { styled } from 'styled-components';
import { useState } from 'react';

import Modal from '../../UI/Modal';
import { HeaderLayout } from '../../../styles/layout';
import FilterContent from './FilterContent';
import TagContent from './TagContent';
import SortContent from './SortContent';
import TagList from '../../UI/TagList';

import { getSelectedOptions } from './constants';

const AdvancedSearchModal = ({
  onClose,
  onConfirm,
  initialFilterIds,
  initialTagIds,
  initialOrderId,
}) => {
  const [selectedFilterIds, setSelectedFilterIds] = useState(initialFilterIds);
  const [selectedTagIds, setSelectedTagIds] = useState(initialTagIds);
  const [selectedOrderId, setSelectedOrderId] = useState(initialOrderId);

  const handleChangeFilterIds = (newFilter, id) => {
    setSelectedFilterIds(prevFilter => {
      return { ...prevFilter, [id]: newFilter };
    });
  };

  const handleChangeTagIds = newTag => {
    setSelectedTagIds(newTag);
  };

  const handleChangeOrderId = newOrder => {
    setSelectedOrderId(newOrder);
  };

  const handleOnConfirm = () => {
    onConfirm(selectedFilterIds, selectedTagIds, selectedOrderId);
  };

  const handleFilterOnClick = e => {
    const clickedId = e.target.id;
    setSelectedFilterIds(prevfilterIds => {
      const newFilterIds = { ...prevfilterIds };
      newFilterIds[clickedId] = [];
      return newFilterIds;
    });
  };

  const handleTagOnClick = e => {
    const clickedId = e.target.id;
    const newTagIds = selectedTagIds.filter(tagId => tagId !== clickedId);
    setSelectedTagIds(newTagIds);
  };

  const getSelectedOptionTags = () =>
    getSelectedOptions(
      selectedFilterIds,
      selectedTagIds,
      selectedOrderId,
      handleFilterOnClick,
      handleTagOnClick
    );

  const MENU_ITEMS = [
    {
      id: 'filter',
      label: '필터',
      content: (
        <FilterContent
          selectedFilterIds={selectedFilterIds}
          onChange={handleChangeFilterIds}
        />
      ),
    },
    {
      id: 'tag',
      label: '태그',
      content: (
        <TagContent
          selectedTagIds={selectedTagIds}
          onChange={handleChangeTagIds}
        />
      ),
    },
    {
      id: 'sort',
      label: '정렬',
      content: (
        <SortContent
          selectedOrderId={selectedOrderId}
          onChange={handleChangeOrderId}
        />
      ),
    },
  ];

  const [selectedMenu, setSelectedMenu] = useState(MENU_ITEMS[0].id);

  const handleSelectMenu = menu => {
    setSelectedMenu(menu);
  };

  const selectedContent = MENU_ITEMS.find(
    menuItem => menuItem.id === selectedMenu
  )?.content;

  return (
    <Modal onClose={onClose} onConfirm={handleOnConfirm}>
      <StyledHeaderLayout>
        {MENU_ITEMS.map(item => (
          <StyledTitle
            key={item.id}
            $select={selectedMenu === item.id}
            onClick={() => handleSelectMenu(item.id)}
          >
            {item.label}
          </StyledTitle>
        ))}
      </StyledHeaderLayout>
      <SelectedTagListWrapper>
        <TagList tags={getSelectedOptionTags()} scrollable />
      </SelectedTagListWrapper>
      {selectedContent}
    </Modal>
  );
};

export default AdvancedSearchModal;

const StyledHeaderLayout = styled(HeaderLayout)`
  justify-content: flex-start;
  gap: 2rem;
  margin: 0;
`;

const StyledTitle = styled.h1`
  color: ${props => (props.$select ? 'black' : 'gray')};
`;

const SelectedTagListWrapper = styled.div`
  width: 100%;
  height: 2.4rem;
  padding-bottom: 1rem;
  margin: 0.5rem 0;
  border-bottom: 0.1rem solid gray;
`;
