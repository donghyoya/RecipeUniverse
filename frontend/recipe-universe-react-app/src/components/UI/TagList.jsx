import { styled } from 'styled-components';

import Tag from './Tag';

const TagList = ({ tags }) => {
  return (
    <Container>
      {tags.map(tag => (
        <Tag key={tag}>{tag}</Tag>
      ))}
    </Container>
  )
}

export default TagList;

const Container = styled.div`
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 0.5rem;
  width: fit-content;
  height: fit-content;
`