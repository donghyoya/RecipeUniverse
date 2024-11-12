import { useCallback, useEffect, useRef, useState } from 'react';
import { styled } from 'styled-components';

import Line from './Line';
import Labels from './Labels';
import Ticks from './Ticks';

const MultiTickSlider = ({ initialValue = [0, 0], onChange, labels }) => {
  const [tickValue, setTickValue] = useState(initialValue);
  const [tickMode, setTickMode] = useState('SINGLE');
  const pressTimer = useRef(null);

  const handleChangeIndex = useCallback(
    e => {
      clearTimeout(pressTimer.current);
      const newValue = +e.target.value;
      const position = +e.target.dataset.position;
      onChange(newValue);

      setTickValue(prevValue => {
        if (tickMode === 'SINGLE') {
          return [newValue, newValue];
        }

        return prevValue.map((value, index) =>
          index === position ? newValue : value
        );
      });
    },
    [tickMode, onChange]
  );

  const handlePressStart = useCallback(
    e => {
      pressTimer.current = setTimeout(() => {
        if (tickMode === 'SINGLE') {
          setTickMode('DUAL');
        }
      }, 500);
    },
    [tickMode]
  );

  const handlePressEnd = useCallback(
    e => {
      clearTimeout(pressTimer.current);
      if (tickValue[0] === tickValue[1]) {
        setTickMode('SINGLE');
      }
    },
    [tickValue]
  );

  return (
    <Container>
      <InnerContainer>
        <Line labels={labels} tickValue={tickValue} />
        <Ticks labels={labels} tickValue={tickValue} mode={tickMode} />
        <SliderContainer>
          <input
            type="range"
            data-position={0}
            min={0}
            max={labels.length - 1}
            step={1}
            value={tickValue[0]}
            onChange={handleChangeIndex}
            onMouseDown={handlePressStart}
            onMouseUp={handlePressEnd}
          />
          <input
            type="range"
            data-position={1}
            min={0}
            max={labels.length - 1}
            step={1}
            value={tickValue[1]}
            onChange={handleChangeIndex}
            onMouseDown={handlePressStart}
            onMouseUp={handlePressEnd}
          />
        </SliderContainer>
      </InnerContainer>
      <Labels labels={labels} />
    </Container>
  );
};

export default MultiTickSlider;

const Container = styled.div`
  width: 100%;
  height: 6rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const InnerContainer = styled.div`
  width: calc(100% - 1.5rem);
  display: flex;
  flex-direction: column;
  position: relative;
`;

const SliderContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  height: 3rem;

  & > input[type='range'] {
    margin: 0;
    width: 100%;
    position: absolute;
    appearance: none;
    background-color: transparent;
    pointer-events: none;
    height: 3rem;
    outline: none;

    &::-webkit-slider-thumb {
      pointer-events: auto;
      appearance: none;
      width: 1.1rem;
      height: 1.1rem;
      cursor: pointer;
    }
  }
`;
