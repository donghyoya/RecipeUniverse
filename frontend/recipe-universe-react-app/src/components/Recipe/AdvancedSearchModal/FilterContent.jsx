import { styled } from 'styled-components';

import RangeSlieder from '../../UI/TickSlider/RangeSlider';

const COOKING_CONSTANTS = {
  difficulty: ['easy', 'medium', 'hard'],
  time: ['under30', '60', '90', '120', 'over180'],
  servings: ['1', '2', '3plus'],
};

const FilterContent = ({ selectedFilterIds, onChange }) => {
  const getSliderValue = groupId => {
    const sliderValue = selectedFilterIds[groupId].map(label =>
      COOKING_CONSTANTS[groupId].indexOf(label)
    );
    return sliderValue;
  };

  const handleOnChange = (newValue, id) => {
    const newLabel = newValue.sort().map(value => COOKING_CONSTANTS[id][value]);
    onChange(newLabel, id);
  };

  return (
    <ModalContent key="filter-modal">
      <FormField>
        <label>난이도</label>
        <SliderWrapper>
          <RangeSlieder
            id="difficulty"
            onChange={handleOnChange}
            labels={['쉬움', '중간', '어려움']}
            value={getSliderValue('difficulty')}
          />
        </SliderWrapper>
      </FormField>
      <FormField>
        <label>조리시간</label>
        <SliderWrapper>
          <RangeSlieder
            onChange={handleOnChange}
            labels={[
              '30분\n이하',
              '1시간',
              '1시간\n30분',
              '2시간',
              '3시간\n이상',
            ]}
            value={getSliderValue('time')}
            id="time"
          />
        </SliderWrapper>
      </FormField>
      <FormField>
        <label>인원수</label>
        <SliderWrapper>
          <RangeSlieder
            onChange={handleOnChange}
            labels={['1인분', '2인분', '3인분\n이상']}
            value={getSliderValue('servings')}
            id="servings"
          />
        </SliderWrapper>
      </FormField>
    </ModalContent>
  );
};

export default FilterContent;

const ModalContent = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`;

const FormField = styled.div`
  display: flex;
  flex-direction: row;
  gap: 2rem;
  justify-content: space-between;
  align-items: center;
  min-height: 3rem;

  & > label {
    font-size: 2rem;
  }
`;

const SliderWrapper = styled.div`
  width: 60%;
`;
