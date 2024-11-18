import { useCallback, useEffect, useRef, useState } from 'react';
import { styled } from 'styled-components';

import Line from './Line';
import Labels from './Labels';
import Ticks from './Ticks';

const RangeSlider = ({ value: externalValue, onChange, labels, id }) => {
  const [internalValue, setInternalValue] = useState(externalValue);
  const [rangeMode, setRangeMode] = useState(getRangeMode(externalValue));
  const pressTimer = useRef(null);

  const handleOnClick = e => {
    if (rangeMode === 'DUAL') return;
    const newValue = +e.target.dataset.position;
    setRangeMode('SINGLE');
    setInternalValue([newValue]);
    onChange([newValue], id);
  };

  const handleChangeIndex = useCallback(
    e => {
      clearTimeout(pressTimer.current);
      const newValue = +e.target.value;
      const newValuePosition = +e.target.dataset.position;

      setInternalValue(prevValue => {
        if (rangeMode === 'SINGLE') {
          return [newValue];
        } else {
          return prevValue.map((value, index) =>
            index === newValuePosition ? newValue : value
          );
        }
      });
    },
    [rangeMode]
  );

  const handlePressStart = useCallback(
    e => {
      pressTimer.current = setTimeout(() => {
        if (rangeMode === 'SINGLE') {
          setRangeMode('DUAL');
          setInternalValue(prevValue => [prevValue[0], prevValue[0]]);
        }
      }, 500);
    },
    [rangeMode]
  );

  const handlePressEnd = useCallback(
    e => {
      clearTimeout(pressTimer.current);
      let newValue = internalValue;
      if (rangeMode === 'DUAL' && internalValue[0] === internalValue[1]) {
        setRangeMode('SINGLE');
        newValue = [internalValue[0]];
      }
      setInternalValue(newValue);
      onChange(newValue, id);
    },
    [internalValue, rangeMode, onChange, id]
  );

  useEffect(() => {
    setRangeMode(getRangeMode(externalValue));
    setInternalValue(externalValue);
  }, [externalValue]);

  return (
    <Container>
      <InnerContainer>
        <Line labels={labels} tickValue={internalValue} />
        <Ticks
          labels={labels}
          value={internalValue}
          mode={rangeMode}
          onClick={handleOnClick}
        />
        <SliderContainer>
          {rangeMode !== 'HIDDEN' && (
            <input
              type="range"
              data-position={0}
              min={0}
              max={labels.length - 1}
              step={1}
              value={internalValue[0]}
              onChange={handleChangeIndex}
              onMouseDown={handlePressStart}
              onMouseUp={handlePressEnd}
            />
          )}
          {rangeMode === 'DUAL' && (
            <input
              type="range"
              data-position={1}
              min={0}
              max={labels.length - 1}
              step={1}
              value={internalValue[1]}
              onChange={handleChangeIndex}
              onMouseDown={handlePressStart}
              onMouseUp={handlePressEnd}
            />
          )}
        </SliderContainer>
      </InnerContainer>
      <Labels labels={labels} />
    </Container>
  );
};

export default RangeSlider;

const getRangeMode = value => {
  switch (value.length) {
    case 0:
      return 'HIDDEN';
    case 1:
      return 'SINGLE';
    default:
      return 'DUAL';
  }
};

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
