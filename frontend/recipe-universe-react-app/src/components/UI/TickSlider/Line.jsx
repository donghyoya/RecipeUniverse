import { styled } from 'styled-components';

const Line = ({ labels = [0, 1], tickValue = [0, 0] }) => {
  return (
    <LineContainer>
      {labels.map((label, idx) => (
        <LineStyle key={idx} $active={isLineActive(idx, tickValue)} />
      ))}
    </LineContainer>
  );
};

export default Line;

export const isLineActive = (currIdx, tickValue) => {
  return (
    (currIdx >= tickValue[0] && currIdx < tickValue[1]) ||
    (currIdx < tickValue[0] && currIdx >= tickValue[1])
  );
};

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
`;

const LineStyle = styled.div`
  flex: 1;
  height: ${props => (props.$active ? '0.5rem' : '0.3rem')};
  background-color: ${props => (props.$active ? 'black' : 'gray')};

  &:last-child {
    display: none;
  }
`;
