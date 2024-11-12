import { styled } from 'styled-components';

import TextWithLineBreaks from '../../../utils/TestWithLineBreaks';

const Labels = ({ labels }) => {
  return (
    <LabelContainer>
      {labels.map((label, idx) => (
        <label key={idx}>
          <TextWithLineBreaks text={label} />
        </label>
      ))}
    </LabelContainer>
  );
};

export default Labels;

const LabelContainer = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;

  label {
    width: 3rem;
    font-size: 1rem;
    text-align: center;
  }
`;
