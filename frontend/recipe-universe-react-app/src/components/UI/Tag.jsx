import { styled } from 'styled-components';

import closeIcon from '../../assets/icons/close.svg';
import sortIcon from '../../assets/icons/sort.svg';

const icons = { close: closeIcon, sort: sortIcon };

const Tag = ({ text, icon, id, onClick = () => {}, primary }) => {
  return (
    <TagLayout $icon={icon} onClick={onClick} id={id} $primary={primary}>
      {text}
      {icon && <img src={icons[icon]} alt={`tag ${icon} icon`} />}
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
  background-color: ${props => (props.$primary ? 'black' : 'white')};
  color: ${props => (props.$primary ? 'white' : 'black')};

  display: flex;
  justify-content: center;
  align-items: center;

  padding-left: 0.8rem;
  padding-right: ${props => (props.$icon ? '0.4rem' : '0.8rem')};
  white-space: nowrap;

  & > img {
    width: 1.6rem;
    height: 1.6rem;
    margin-left: 0.2rem;
  }
`;
