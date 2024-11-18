import { styled } from 'styled-components';

import Tag from './Tag';

const TagList = ({ tags, scrollable, onClick }) => {
  return (
    <Container $scrollable={scrollable}>
      {tags.map(tag => (
        <Tag
          key={tag.id}
          id={tag.id}
          text={tag.text}
          icon={tag.icon}
          onClick={tag.onClick || onClick}
        />
      ))}
    </Container>
  );
};

export default TagList;

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  gap: 0.5rem;

  width: ${props => (props.$scrollable ? '100%' : 'fit-content')};
  height: fit-content;
  flex-wrap: ${props => (props.$scrollable ? 'nowrap' : 'wrap')};
  overflow-x: ${props => (props.$scrollable ? 'auto' : 'visible')};
`;
