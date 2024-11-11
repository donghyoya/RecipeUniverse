import { styled } from 'styled-components';
import TickSlider from '../UI/TickSlider'
import Button from '../UI/Button';
import { FooterButtonWrapper, HeaderLayout } from '../../styles/layout';
import Modal from '../UI/Modal';

const AddIngredientsModal = ({ onClose }) => {

  return (
    <Modal onClose={onClose}>
      <ModalContent>
        <HeaderLayout>
          <h1>재료 추가하기</h1>
        </HeaderLayout>
        <FormField>
          <label>재료명</label>
          <input type='text'/>
        </FormField>
        <FormField>
          <label>수량 / 단위</label>
          <input type='text'/>
        </FormField>
        <FormField>
          <label>선호도</label>
          <TickSlider 
            onChange={() => {}} 
            step={1} 
            min={0} 
            max={4} 
            initialValue={2}
            labels={['선호', '', '', '', '불호' ]}
          />
        </FormField>
      </ModalContent>
      <FooterButtonWrapper>
        <Button primary>확인</Button>
        <Button onClick={onClose}>취소</Button>
      </FooterButtonWrapper>
    </Modal>
  )
}

export default AddIngredientsModal;

const ModalContent = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`

const FormField = styled.div`
  display: flex;
  margin-bottom: 1.5rem;
  flex-direction: row;
  gap: 2rem;
  justify-content: space-between;
  align-items: center;
  min-height: 3rem;

  label {
    font-size: 2rem;
  }

  input[type="text"] {
    height: 3rem;
    border: 1px solid black;
    border-radius: 0.2rem;
  }
`