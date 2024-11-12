import { styled } from 'styled-components';

const Ticks = ({ labels, tickValue, mode }) => {
  return (
    <TickContainer>
      {labels.map((label, currIdx) => (
        <Tick key={currIdx} $state={getTickState(currIdx, tickValue, mode)} />
      ))}
    </TickContainer>
  );
};

export default Ticks;

const getTickState = (currIdx, tickValue, mode) => {
  const isSelected = tickValue.includes(currIdx);
  if (tickValue.length === 1) {
    return isSelected ? 'SELECTED' : 'DEFAULT';
  }

  const isBetween =
    (currIdx > tickValue[0] && currIdx < tickValue[1]) ||
    (currIdx < tickValue[0] && currIdx > tickValue[1]);

  if (isSelected) {
    return mode === 'SINGLE' ? 'SELECTED' : 'DUAL';
  }

  if (mode === 'DUAL' && isBetween) {
    return 'HIDDEN';
  }

  return 'DEFAULT';
};

const getBackground = props => {
  switch (props.$state) {
    case 'SELECTED':
      return 'black';
    case 'HIDDEN':
      return 'transparent';
    default:
      return 'white';
  }
};

const getShadowWidth = props => {
  switch (props.$state) {
    case 'DUAL':
      return '0.5';
    case 'HIDDEN':
      return '0';
    default:
      return '0.25';
  }
};

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
  width: 1.6rem;
  height: 1.6rem;
  background: ${getBackground};
  border-radius: 50%;
  box-shadow: 0 0 0 ${getShadowWidth}rem black inset;
`;
