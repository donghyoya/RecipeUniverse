import React, { useState } from 'react';
import styled from 'styled-components';

const TickSlider = ({ min, max, step, initialValue, onChange }) => {
  const [value, setValue] = useState(initialValue);

  const handleChange = (e) => {
    const newValue = Number(e.target.value);
    setValue(newValue);
    onChange(newValue);
  };

  const labels = ['선호', '', '', '', '불호'];

  return (
    <SliderContainer>
      <StyledSlider
        type="range"
        min={min}
        max={max}
        step={step}
        value={value}
        onChange={handleChange}
      />
      <HorizontalLine />
      <TickContainer>
        {labels.map((label, idx) => (
          <Tick key={idx} $active={idx === value}/>
        ))}
      </TickContainer>
      <LabelContainer>
        {labels.map((label, idx) => (
          <TickLabel key={idx}>{label}</TickLabel>
        ))}
      </LabelContainer>
    </SliderContainer>
  );
};

const SliderContainer = styled.div`
  position: relative;
  width: 50%;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const StyledSlider = styled.input`
  -webkit-appearance: none;
  width: calc(100% - 5px);
  background: transparent;
  outline: none;
  position: absolute;
  z-index: 2;

  &::-webkit-slider-thumb {
    appearance: none;
    width: 1.1rem;
    height: 1.1rem;
    cursor: pointer;
  }

  &::-moz-range-thumb {
    width: 1.1rem;
    height: 1.1rem;
    cursor: pointer;
  }
`;

const HorizontalLine = styled.div`
  width: 100%;
  height: 0.1rem;
  background-color: black;
  position: absolute;
`

const TickContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: absolute;
  top: 0;
  width: 100%;
  height: 40px;
  z-index: 1;
`;

const Tick = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1.1rem;
  height: 1.1rem;
  background: ${props => props.$active ? 'black' : 'white'};
  border-radius: 50%;
  border: 0.2rem solid black;
`;

const LabelContainer = styled.div`
  height: 40px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
`

const TickLabel = styled.span`
  font-size: 0.8rem;
  font-weight: bold;
`;

export default TickSlider;