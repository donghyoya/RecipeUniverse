import { styled } from 'styled-components';

import TickSlider from '../UI/TickSlider';
import { HeaderLayout } from '../../styles/layout';
import Modal from '../UI/Modal';

const AddIngredientsModal = ({ onClose, onConfirm }) => {
  return (
    <Modal onClose={onClose} onConfirm={onConfirm}>
      <ModalContent>
        <HeaderLayout>
          <h1>재료 추가하기</h1>
        </HeaderLayout>
        <FormField>
          <label>재료명</label>
          <InputWrapper>
            <input type="text" />
          </InputWrapper>
        </FormField>
        <FormField>
          <label>수량 / 단위</label>
          <InputWrapper>
            <input type="text" />
          </InputWrapper>
        </FormField>
        <FormField>
          <label>선호도</label>
          <InputWrapper>
            <TickSlider
              onChange={() => {}}
              step={1}
              min={0}
              max={4}
              initialValue={2}
              labels={['선호', '', '', '', '불호']}
            />
          </InputWrapper>
        </FormField>
      </ModalContent>
    </Modal>
  );
};

export default AddIngredientsModal;

const ModalContent = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`;

const FormField = styled.div`
  display: flex;
  margin-bottom: 1.5rem;
  flex-direction: row;
  gap: 2rem;
  justify-content: space-between;
  align-items: center;
  min-height: 3rem;

  & > label {
    font-size: 2rem;
  }
`;

const InputWrapper = styled.div`
  width: 50%;

  & > input[type='text'] {
    height: 3rem;
    border: 0.1rem solid black;
    border-radius: 0.2rem;
    width: 100%;
    padding: 0 1rem;
    box-sizing: border-box;
  }
`;
