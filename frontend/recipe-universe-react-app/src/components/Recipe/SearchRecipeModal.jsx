import { styled } from 'styled-components';
import { useState } from 'react';

import Modal from '../UI/Modal';
import { HeaderLayout } from '../../styles/layout';
import MultiTickSlider from '../UI/TickSlider/MultiTickSlider';

const SearchRecipeModal = ({ onClose, onConfirm }) => {
  const [selectedMenuIndex, setSelectedMenuIndex] = useState(0);

  const menu = ['필터', '태그', '정렬'];

  const handleSelectMenu = index => {
    setSelectedMenuIndex(index);
  };

  const menuContents = [
    // 필터
    <ModalContent key="filter-modal">
      <FormField>
        <label>난이도</label>
        <SliderWrapper>
          <MultiTickSlider
            onChange={() => {}}
            min={0}
            max={2}
            step={1}
            labels={['쉬움', '중간', '어려움']}
          />
        </SliderWrapper>
      </FormField>
      <FormField>
        <label>조리시간</label>
        <SliderWrapper>
          <MultiTickSlider
            onChange={() => {}}
            min={0}
            max={4}
            step={1}
            labels={[
              '30분\n이하',
              '1시간',
              '1시간\n30분',
              '2시간',
              '3시간\n이상',
            ]}
          />
        </SliderWrapper>
      </FormField>
      <FormField>
        <label>인원수</label>
        <SliderWrapper>
          <MultiTickSlider
            onChange={() => {}}
            min={0}
            max={2}
            step={1}
            labels={['1인분', '2인분', '3인분\n이상']}
          />
        </SliderWrapper>
      </FormField>
    </ModalContent>,
    // 태그
    <ModalContent key="tag-modal"></ModalContent>,
    // 정렬
    <ModalContent key="sort-modal">
      <FormField>
        <label>최신순</label>
        <input type="radio" />
      </FormField>
      <FormField>
        <label>좋아요 많은 순</label>
        <input type="radio" />
      </FormField>
      <FormField>
        <label>후기 많은 순</label>
        <input type="radio" />
      </FormField>
      <FormField>
        <label>별점순</label>
        <input type="radio" />
      </FormField>
    </ModalContent>,
  ];

  return (
    <Modal onClose={onClose} onConfirm={onConfirm}>
      <StyledHeaderLayout>
        {menu.map((item, index) => (
          <StyledTitle
            $select={selectedMenuIndex === index}
            onClick={() => {
              handleSelectMenu(index);
            }}
            key={item}
          >
            {item}
          </StyledTitle>
        ))}
      </StyledHeaderLayout>
      {menuContents.map(
        (content, index) => index === selectedMenuIndex && content
      )}
    </Modal>
  );
};

export default SearchRecipeModal;

const StyledHeaderLayout = styled(HeaderLayout)`
  justify-content: flex-start;
  gap: 2rem;
`;

const StyledTitle = styled.h1`
  color: ${props => (props.$select ? 'black' : 'gray')};
`;

const ModalContent = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`;

const FormField = styled.div`
  display: flex;
  margin-bottom: 1.5rem;
  flex-direction: row;
  gap: 2rem;
  justify-content: space-between;
  align-items: center;
  min-height: 3rem;

  & > label {
    font-size: 2rem;
  }

  & > input[type='text'] {
    height: 3rem;
    border: 1px solid black;
    border-radius: 0.2rem;
  }
`;

const SliderWrapper = styled.div`
  width: 50%;
`;
