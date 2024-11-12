import { styled } from 'styled-components';

import MultiTickSlider from '../../UI/TickSlider/MultiTickSlider';

const FilterContent = () => {
  return (
    <ModalContent key="filter-modal">
      <FormField>
        <label>난이도</label>
        <SliderWrapper>
          <MultiTickSlider
            onChange={() => {}}
            labels={['쉬움', '중간', '어려움']}
          />
        </SliderWrapper>
      </FormField>
      <FormField>
        <label>조리시간</label>
        <SliderWrapper>
          <MultiTickSlider
            onChange={() => {}}
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
            labels={['1인분', '2인분', '3인분\n이상']}
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
