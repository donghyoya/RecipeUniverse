import { styled } from 'styled-components';

import closeIcon from '../../assets/icons/close.svg';
import sortIcon from '../../assets/icons/sort.svg';

const icons = { close: closeIcon, sort: sortIcon };

const Tag = ({ text, icon }) => {
  return (
    <TagLayout $icon={icon}>
      {text}
      {icon && <img src={icons[icon]} />}
    </TagLayout>
  );
};

export default Tag;

const TagLayout = styled.div`
  width: fit-content;
  min-width: min-content;
  height: 2.4rem;
  font-size: 1.4rem;
  border: 1px solid black;
  border-radius: 1.2rem;

  display: flex;
  justify-content: center;
  align-items: center;

  padding-left: 1rem;
  padding-right: ${props => (props.$icon ? '0.5rem' : '1rem')};
  white-space: nowrap;

  & > img {
    width: 1.6rem;
    height: 1.6rem;
  }
`;
