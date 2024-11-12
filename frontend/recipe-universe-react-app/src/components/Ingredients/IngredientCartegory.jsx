import { useState, useCallback } from 'react';
import styled from 'styled-components';

const IngredientCartegory = () => {
  const category = [
    '전체',
    '육류 / 생선류',
    '채소 / 과일류',
    '유제품',
    '소스류',
  ];

  const [selectedIndex, setSelectedIndex] = useState(0);

  const handleCategoryClick = useCallback(index => {
    setSelectedIndex(index);
  }, []);

  return (
    <CategoryWrapper>
      {category.map((item, index) => (
        <CategoryItem
          key={index}
          $active={selectedIndex === index}
          onClick={() => handleCategoryClick(index)}
        >
          {item}
        </CategoryItem>
      ))}
    </CategoryWrapper>
  );
};

export default IngredientCartegory;

const CategoryWrapper = styled.div`
  width: 100%;
  height: 4rem;
  border-bottom: 1px solid black;
  display: flex;
  flex-direction: row;
`;

const CategoryItem = styled.div`
  height: 100%;
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  border-right: 1px solid black;
  font-size: 1rem;
  color: ${props => (props.$active ? 'white' : 'black')};
  background-color: ${props => (props.$active ? 'black' : 'white')};
  font-weight: ${props => (props.$active ? 'bold' : '300')};
`;
