import styled from 'styled-components';
import RecipeList from '../components/Recipe/RecipeList';

import heartIcon from '../assets/icons/heart_filled.svg';
import searchIcon from '../assets/icons/search.svg';
import sortIcon from '../assets/icons/sort.svg';
import filterIcon from '../assets/icons/filter.svg';


const RecipeListPage = () => {
  return (
    <PageWrapper>
      <HeaderWrapper>
        <Title>레시피 검색</Title>
        <IconList>
          <img src={heartIcon}/>
          <img src={searchIcon}/>
          <img src={sortIcon}/>
          <img src={filterIcon}/>
        </IconList>
      </HeaderWrapper>
      <RecipeList />
    </PageWrapper>
  );
}

export default RecipeListPage;

const PageWrapper = styled.div`
  width: 100%;
  height: 100%;
  box-sizing: border-box;
`

const HeaderWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  height: 3.6rem;
  padding: 1.5rem;
`

const Title = styled.h1`
  font-size: 2.4rem;
  font-weight: bold;
`

const IconList = styled.div`
  display: flex;
  flex-direction: row;
  gap: 0.5rem;
  justify-content: space-between;

  img {
    width: 3.6rem;
    height: 3.6rem;
  }

  img:first-child {
    padding: 0.5rem;
    width: 2.6rem;
    height: 2.6rem;
  }
`