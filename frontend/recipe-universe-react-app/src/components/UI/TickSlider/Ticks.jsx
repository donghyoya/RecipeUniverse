import { styled } from 'styled-components';

const Ticks = ({ labels, value, onClick }) => {
  const getTickState = (index, value) => {
    const isSelected = value.includes(index);
    if (value.length === 1) {
      return isSelected ? 'SINGLE' : 'DEFAULT';
    }

    const isBetween =
      (index > value[0] && index < value[1]) ||
      (index < value[0] && index > value[1]);

    if (isSelected) return 'DUAL';
    if (isBetween) return 'HIDDEN';
    return 'DEFAULT';
  };

  return (
    <TickContainer>
      {labels.map((label, index) => {
        const state = getTickState(index, value);
        return (
          <Tick
            key={index}
            onClick={onClick}
            $state={state}
            data-position={index}
          />
        );
      })}
    </TickContainer>
  );
};

export default Ticks;

/**
 * 틱의 배경색을 결정하는 함수
 * @param {Object} props
 * @param {TickState} props.$state - 틱의 상태
 * @returns {string} 배경색
 */
const getBackground = props => {
  switch (props.$state) {
    case 'SINGLE':
      return 'black';
    case 'HIDDEN':
      return 'transparent';
    default:
      return 'white';
  }
};

/**
 * 틱의 그림자 너비를 결정하는 함수
 * @param {Object} props
 * @param {TickState} props.$state - 틱의 상태
 * @returns {string} 그림자 너비
 */
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
