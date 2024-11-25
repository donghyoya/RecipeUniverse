import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';

import { FooterButtonWrapper, HeaderLayout } from '../styles/layout';
import Button from '../components/UI/Button';
import { PageLayout } from '../styles/layout';

const CookingPage = props => {
  const [currStep, setCurrStep] = useState(0);
  const navigate = useNavigate();

  const cookingStepDataList = [
    {
      step: 1,
      title: '감자 손질하기',
      cookingTime: '5분',
      text: '감자 2개의 껍질을 벗기고, 한입 크기로 자른다.',
      image: '',
    },
    {
      step: 2,
      title: '완성',
      cookingTime: '5분',
      text: '끝',
      image: '',
    },
  ];

  const handleNext = () => {
    if (currStep === cookingStepDataList.length - 1) {
      navigate('/review');
      return;
    }
    setCurrStep(prevStep => prevStep + 1);
  };

  const handlePrevious = () => {
    if (currStep === 0) {
      navigate('/prepare');
      return;
    }
    setCurrStep(prevStep => prevStep - 1);
  };

  return (
    <StyledPageLayout>
      <HeaderLayout>
        <h1>요리하기</h1>
      </HeaderLayout>
      <img src={cookingStepDataList[currStep]?.image} alt="cooking" />
      <SubTitle>
        <h2>
          {cookingStepDataList[currStep]?.step}.{' '}
          {cookingStepDataList[currStep]?.title}
        </h2>
        <span>{cookingStepDataList[currStep]?.cookingTime}</span>
      </SubTitle>
      <p>{cookingStepDataList[currStep]?.text}</p>
      <FooterButtonWrapper>
        <Button onClick={handlePrevious}>이전</Button>
        <Button onClick={handleNext} primary>
          {currStep === cookingStepDataList.length - 1 ? '완료' : '다음'}
        </Button>
      </FooterButtonWrapper>
    </StyledPageLayout>
  );
};

export default CookingPage;

const StyledPageLayout = styled(PageLayout)`
  & > p {
    font-size: 1.6rem;
    margin: 0;
  }

  & > img {
    width: 100%;
    aspect-ratio: 3 / 2;
  }
`;

const SubTitle = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  height: 5rem;

  & > h2 {
    font-size: 2rem;
    font-weight: bold;
    margin: 0;
  }

  & > span {
    font-size: 1.6rem;
    margin: 0;
  }
`;
