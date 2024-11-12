import { styled } from 'styled-components';
import { useState } from 'react';

import Modal from '../../UI/Modal';
import { HeaderLayout } from '../../../styles/layout';
import FilterContent from './FilterContent';
import TagContent from './TagContent';
import SortContent from './SortContent';
import TagList from '../../UI/TagList';

const MENU_ITEMS = [
  {
    id: 'filter',
    label: '필터',
    content: <FilterContent />,
  },
  {
    id: 'tag',
    label: '태그',
    content: <TagContent />,
  },
  {
    id: 'sort',
    label: '정렬',
    content: <SortContent />,
  },
];

const SearchRecipeModal = ({ onClose, onConfirm }) => {
  const [selectedMenu, setSelectedMenu] = useState(MENU_ITEMS[0].id);

  const selectedContent = MENU_ITEMS.find(
    menuItem => menuItem.id === selectedMenu
  )?.content;

  const handleSelectMenu = menuId => {
    setSelectedMenu(menuId);
  };

  return (
    <Modal onClose={onClose} onConfirm={onConfirm}>
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
        <TagList
          tags={[
            { text: '최신순', icon: 'sort' },
            { text: '식사', icon: 'close' },
          ]}
          scrollable
        />
      </SelectedTagListWrapper>
      {selectedContent}
    </Modal>
  );
};

export default SearchRecipeModal;

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
  padding-bottom: 1rem;
  margin: 0.5rem 0;
  border-bottom: 0.1rem solid gray;
`;
