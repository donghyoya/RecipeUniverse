import { styled } from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { FooterButtonWrapper, HeaderLayout, PageLayout } from '../styles/layout';
import Button from '../components/UI/Button';

const CookingPrepPage = () => {
  const navigate = useNavigate();

  const handleCancel = () => {
    navigate('/prepare');
  }

  const handleStart = () => {
    navigate('/cooking');
  }

  const ingredientData = [
    ['돼지고기', '300g'],
    ['돼지고기', '300g'],
    ['돼지고기', '300g'],
    ['돼지고기', '300g'],
  ]

  return (
    <StyledPageLayout>
      <HeaderLayout>
        <h1>재료 확인하기</h1>
      </HeaderLayout>
      <IngredientList>
        <div>
          {ingredientData.map((item, idx) => (
            <li key={idx}>
              <span>{item[0]}</span>
              <span>{item[1]}</span>
            </li>
          ))}
        </div>
      </IngredientList>
      <p>내 냉장고에서 위의 재료들이 제거됩니다.</p>
      <FooterButtonWrapper>
        <Button onClick={handleCancel}>취소</Button>
        <Button onClick={handleStart} primary>확인</Button>
      </FooterButtonWrapper>
    </StyledPageLayout>
  );
}

export default CookingPrepPage

const StyledPageLayout = styled(PageLayout)`
  p {
    font-size: 1.6rem;
  }
`

const IngredientList = styled.ul`
  width: 100%;

  padding: 0;
  box-sizing: border-box;

  div {
    border: 1px solid black;
  }

  li {
    display: flex;
    flex-direction: row;
    font-size: 1.8rem;

    span {
      flex: 1;
      border: 1px solid black;
      height: 2rem;
      display: flex;
      align-items: center;
      padding: 0.6rem;
    }

    span:last-child {
      justify-content: end;
    }
  }
`;