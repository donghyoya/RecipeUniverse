import { useCallback, useRef, useState } from 'react';
import { styled } from 'styled-components';

import TextWithLineBreaks from '../../utils/TestWithLineBreaks';

const MultiTickSlider = ({ initialValue={ front: 0, behind: 0 }, onChange, labels }) => {
  const [tickValue, setTickValue] = useState(initialValue);
  const [tickMode, setTickMode] = useState('SINGLE');
  const pressTimer = useRef(null);

  const handleChangeValue = useCallback((e) => {
    clearTimeout(pressTimer.current);
    const newValue = e.target.value;
    onChange(newValue);
    
    setTickValue(prevValue => {
      if (tickMode === 'SINGLE') {
        return { front: newValue, behind: newValue };
      }
      
      return {
        front: e.target.id === 'front' ? newValue : prevValue.front,
        behind: e.target.id === 'behind' ? newValue : prevValue.behind,
      };
    });
  }, [tickMode, onChange]);

  const handlePressStart = useCallback((e) => {
    pressTimer.current = setTimeout(() => {
      if (tickMode === 'SINGLE') {
        setTickMode('DUAL');
      }
    }, 500);
  }, [tickMode]);

  const handlePressEnd = useCallback((e) => {
    clearTimeout(pressTimer.current);
    if (tickValue.front === tickValue.behind) {
      setTickMode('SINGLE');
    }
  }, [tickValue]);

  return (
    <Container>
      <InnerContainer>
        <LineContainer>
          {labels.map((label, idx) => (
            <HorizontalLine key={idx} $active={
              (idx >= tickValue.front && idx < tickValue.behind) ||
              (idx < tickValue.front && idx >= tickValue.behind) 
            }/>
          ))}
        </LineContainer>
        <TickContainer>
          {labels.map((label, idx) => {
            const active = idx === tickValue.front || idx === tickValue.behind;
            const isInRange = 
              (idx > tickValue.front && idx < tickValue.behind) || 
              (idx < tickValue.front && idx > tickValue.behind);

            if (tickMode === 'DUAL' && active) {
              return <DualTick key={idx} />;
            }
            
            return <Tick key={idx} $active={active} $visible={!isInRange}/>
          })}
        </TickContainer>
        <SliderContainer>
          <input 
            type='range' 
            id='behind'
            min={0} 
            max={labels.length - 1} 
            step={1} 
            value={tickValue.behind}
            onChange={handleChangeValue} 
            onMouseDown={handlePressStart}
            onMouseUp={handlePressEnd}
          />
          <input 
            type='range'
            id='front'
            min={0} 
            max={labels.length - 1} 
            step={1} 
            value={tickValue.front}
            onChange={handleChangeValue} 
            onMouseDown={handlePressStart}
            onMouseUp={handlePressEnd}
          />
        </SliderContainer>
      </InnerContainer>
      <LabelContainer>
        {labels.map((label, idx) => (
          <label key={idx}>
            <TextWithLineBreaks text={label} />
          </label>
        ))}
      </LabelContainer>
    </Container>
    
  )
}

export default MultiTickSlider;

const Container = styled.div`
  width: 100%;
  height: 6rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`

const InnerContainer = styled.div`
  width: calc(100% - 1.5rem);
  display: flex;
  flex-direction: column;
  position: relative;
`

const SliderContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  height: 3rem;

  & > input[type=range] {
    margin: 0;
    width: 100%;
    position: absolute;
    appearance: none;
    background-color: transparent;
    pointer-events: none;
    height: 3rem;

    &::-webkit-slider-thumb {
      pointer-events: auto;
      appearance: none;
      width: 1.1rem;
      height: 1.1rem;
      cursor: pointer;
    }
  }
`

const LineContainer = styled.div`
  display: flex;
  position: absolute;
  top: 0;
  
  width: 100%;
  height: 3rem;
  flex-direction: row;
  align-items: center;
  padding: 0 0.8rem;
  box-sizing: border-box;
`

const HorizontalLine = styled.div`
  flex: 1;
  height: 0.3rem;
  background-color: ${props => props.$active ? 'black' : 'gray'};
  
  &:last-child {
    display: none;
  }
`

const TickContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: absolute;
  top: 0;
  width: 100%;
  height: 3rem;
`;

const Tick = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1.1rem;
  height: 1.1rem;
  background: ${ props => 
    props.$active ? 'black' :
    props.$visible ? 'white' : 'transparent'
  };
  border-radius: 50%;
  border: 0.25rem solid black;
  border-color: ${props => props.$visible ? 'black' : 'transparent'};
`;

const DualTick = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  width: 0.6rem;
  height: 0.6rem;
  background-color: white; 
  border-radius: 50%;
  border: 0.5rem solid black;
`

const LabelContainer = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;

  label {
    width: 3rem;
    font-size: 0.8rem;
    font-weight: bold;
    text-align: center;
  }
`