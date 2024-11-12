import { styled } from 'styled-components';
import { HeaderLayout, PageLayout } from '../styles/layout';

const AccountPage = props => {
  const settingsMenuList = [
    { title: '내가 작성한 레시피' },
    {
      title: '불호 재료 편집',
      description: '불호 재료가 포함된 레시피는 차단됩니다',
    },
    {
      title: '기본 재료 편집',
      description: '재료 정보를 입력하지 않아도 있다고 가정합니다',
    },
    { title: '연동 계정', description: 'example@google.com' },
    { title: '로그아웃' },
    { title: '계정탈퇴' },
  ];

  return (
    <PageLayout>
      <HeaderLayout>
        <h1>계정</h1>
      </HeaderLayout>
      <MenuList>
        {settingsMenuList.map(settingsMenuItem => (
          <MenuItem key={settingsMenuItem.title}>
            <span>{settingsMenuItem.title}</span>
            {settingsMenuItem.description && (
              <p>{settingsMenuItem.description}</p>
            )}
          </MenuItem>
        ))}
      </MenuList>
    </PageLayout>
  );
};

export default AccountPage;

const MenuList = styled.ul`
  margin: 2rem 0;
  padding: 0;

  display: flex;
  flex-direction: column;
  gap: 1.5rem;
`;

const MenuItem = styled.li`
  list-style: none;
  margin: 0;

  & > span {
    font-size: 2rem;
  }

  & > p {
    font-size: 1.6rem;
    margin: 0;
  }
`;
