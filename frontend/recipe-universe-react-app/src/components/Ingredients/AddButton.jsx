import { styled } from 'styled-components';

import addIcon from '../../assets/icons/add.svg';

const AddButton = props => {
  return (
    <AddButtonWrapper onClick={props.onClick}>
      <img src={addIcon} alt="Add" />
    </AddButtonWrapper>
  );
};

export default AddButton;

const AddButtonWrapper = styled.button`
  width: 6rem;
  height: 6rem;
  border: 1px solid black;
  border-radius: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: transparent;
`;
