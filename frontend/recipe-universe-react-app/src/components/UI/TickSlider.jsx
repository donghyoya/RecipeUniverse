import React, { useState } from 'react';
import styled from 'styled-components';

const TickSlider = ({ initialValue, onChange, labels }) => {
  const [value, setValue] = useState(initialValue);

  const handleChange = (e) => {
    const newValue = Number(e.target.value);
    setValue(newValue);
    onChange(newValue);
  };

  return (
    <Container>
      <SliderContainer>
        <input
          type="range"
          min={0}
          max={labels.length - 1}
          step={1}
          value={value}
          onChange={handleChange}
        />
        <HorizontalLine />
        <TickContainer>
          {labels.map((label, idx) => (
            <Tick key={idx} $active={idx === value}/>
          ))}
        </TickContainer>
      </SliderContainer>
      <LabelContainer>
        {labels.map((label, idx) => (
          <label key={idx}>{label}</label>
        ))}
      </LabelContainer>
    </Container>
  );
};

export default TickSlider;

const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`

const SliderContainer = styled.div`
  position: relative;
  width: calc(100% - 1.5rem);
  height: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;

  & > input[type=range] {
    appearance: none;
    width: calc(100% - 1rem);
    background: transparent;
    outline: none;
    position: absolute;
    z-index: 2;
    margin: 0 2rem;

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
  height: 3rem;
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
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: flex-end;

  & > label {
    font-size: 0.8rem;
    font-weight: bold;
    width: 3rem;
    text-align: center;
  }
`
