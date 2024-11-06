import { styled } from 'styled-components'

const Tag = (props) => {
  return (
    <TagLayout>{props.children}</TagLayout>
  )
}

export default Tag;

const TagLayout = styled.div`
  width: fit-content;
  height: 2rem;
  font-size: 1.2rem;
  border: 1px solid black;
  border-radius: 1rem;

  display: flex;
  justify-content: center;
  align-items: center;

  padding: 0 1rem;
`