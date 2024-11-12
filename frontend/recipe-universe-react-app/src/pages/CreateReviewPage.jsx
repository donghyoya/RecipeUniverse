import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';

import ImageSelector from '../components/UI/ImageSelector';
import RatingSelector from '../components/UI/RatingSelector';
import TagList from '../components/UI/TagList';
import Button from '../components/UI/Button';
import {
  PageLayout,
  HeaderLayout,
  FooterButtonWrapper,
  LikeButton,
} from '../styles/layout';

const CreateReviewPage = () => {
  const [isLiked, setIsLiked] = useState(false);
  const navigate = useNavigate();

  const tags = ['태그1', '태그2'];

  const handleToggleLike = () => {
    setIsLiked(prevIsLiked => !prevIsLiked);
  };

  const handleSubmit = () => {
    navigate('/recipe/1');
  };

  return (
    <PageLayout>
      <HeaderLayout>
        <h1>완성</h1>
        <LikeButton onClick={handleToggleLike} isLiked={isLiked} />
      </HeaderLayout>
      <ImageSelector />
      <RatingSelector />
      <InnerContainer>
        <TagList tags={tags} />
        <InputWarpper>
          <textarea type="text" placeholder="후기를 작성해주세요" />
          <span>요리시간: XX분</span>
        </InputWarpper>
      </InnerContainer>
      <FooterButtonWrapper style={{ flex: 0 }}>
        <Button onClick={handleSubmit}>나중에</Button>
        <Button onClick={handleSubmit} primary>
          제출
        </Button>
      </FooterButtonWrapper>
    </PageLayout>
  );
};

export default CreateReviewPage;

const InnerContainer = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1;
`;

const InputWarpper = styled.div`
  width: 100%;
  flex: 1;
  display: flex;
  flex-direction: column;
  border: 1px solid black;
  margin-top: 1rem;

  & > textarea {
    border: 0;
    outline: none;
    resize: none;
    width: 100%;
    flex: 1;
    padding: 1.5rem;
    background-color: transparent;
    box-sizing: border-box;
    align-self: flex-start;
    justify-self: flex-start;
    font-family: 'Interop';

    &::-webkit-scrollbar {
      display: none;
    }

    &:focus {
      outline: none;
    }
  }

  & > span {
    margin: 1.5rem;
    font-size: 1.6rem;
    text-align: end;
  }
`;
