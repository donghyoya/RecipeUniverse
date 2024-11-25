import React, { useState } from 'react';
import styled from 'styled-components';
import Ticks from './Ticks';
import Line from './Line';
import Labels from './Labels';

const TickSlider = ({ initialValue = 0, onChange, labels }) => {
  const [tickValue, setTickValue] = useState(initialValue);

  const handleChange = e => {
    const newIndex = Number(e.target.value);
    setTickValue(newIndex);
    onChange(newIndex);
  };

  return (
    <Container>
      <SliderContainer>
        <input
          type="range"
          min={0}
          max={labels.length - 1}
          step={1}
          value={tickValue}
          onChange={handleChange}
        />
        <Line />
        <Ticks labels={labels} tickValue={[tickValue]} />
      </SliderContainer>
      <Labels labels={labels} />
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
`;

const SliderContainer = styled.div`
  position: relative;
  width: calc(100% - 1.5rem);
  height: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;

  & > input[type='range'] {
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
