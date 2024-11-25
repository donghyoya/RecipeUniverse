import { styled } from 'styled-components';

import GoogleLoginButton from '../components/UI/GoogleLoginButton';
import Carousel from '../components/UI/Carousel';

const HomePage = props => {
  return (
    <HomePageLayout>
      <Carousel />
      <TitleWrapper>
        <span>내가 몰랐던/원하는 레시피 발견하기</span>
        <h1>recipeUniverse</h1>
      </TitleWrapper>
      <GoogleLoginButtonWrapper>
        {!props.isLoggedIn && <GoogleLoginButton onClick={props.handleLogin} />}
      </GoogleLoginButtonWrapper>
    </HomePageLayout>
  );
};

export default HomePage;

const HomePageLayout = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
`;

const TitleWrapper = styled.div`
  margin-top: 3rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  & > h1 {
    font-size: 3rem;
    font-weight: bold;
  }

  & > span {
    font-size: 2rem;
  }
`;

const GoogleLoginButtonWrapper = styled.div`
  display: flex;
  flex: 1;
  justify-content: center;
  align-items: center;
`;
