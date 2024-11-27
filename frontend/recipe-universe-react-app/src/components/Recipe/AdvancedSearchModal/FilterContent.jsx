import { styled } from 'styled-components';

import RangeSlieder from '../../UI/TickSlider/RangeSlider';

import { FILTER_OPTIONS } from './constants';

const FilterContent = ({ selectedFilterIds, onChange }) => {
  const getSliderValue = groupId => {
    return selectedFilterIds[groupId].map(id =>
      FILTER_OPTIONS[groupId].options.findIndex(option => option.id === id)
    );
  };

  const handleOnChange = (newValue, groupId) => {
    const newIds = newValue
      .sort()
      .map(index => FILTER_OPTIONS[groupId].options[index].id);
    onChange(newIds, groupId);
  };

  return (
    <ModalContent>
      {Object.entries(FILTER_OPTIONS).map(
        ([groupId, { groupName, options }]) => (
          <FormField key={groupId}>
            <label>{groupName}</label>
            <SliderWrapper>
              <RangeSlieder
                id={groupId}
                onChange={handleOnChange}
                labels={options.map(opt => opt.label)}
                value={getSliderValue(groupId)}
              />
            </SliderWrapper>
          </FormField>
        )
      )}
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
