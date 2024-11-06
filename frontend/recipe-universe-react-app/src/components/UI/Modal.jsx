import { styled } from 'styled-components';
import TickSlider from './TickSlider';
import { useSelector, useDispatch } from 'react-redux';
import { closeModal } from '../../store/modalSlice';

const Modal = () => {
  const isOpen = useSelector((state) => state.modal.isOpen);
  const dispatch = useDispatch();

  const handleClose = () => {
    dispatch(closeModal())
  };

  if (!isOpen) return null;

  return (
    <ModalBackground onClick={(e) => { 
      console.log('modal background');
      e.stopPropagation(); 
      handleClose();
    }}>
      <ModalWrapper onClick={(e) => { e.stopPropagation(); }}>
        <ModalContent>
          <ModalTitle>재료 추가하기</ModalTitle>
          <Row>
            <ModalLabel>재료명</ModalLabel>
            <ModalTextInput />
          </Row>
          <Row>
            <ModalLabel>수량 / 단위</ModalLabel>
            <ModalTextInput />
          </Row>
          <Row>
            <ModalLabel>선호도</ModalLabel>
            <TickSlider onChange={() => {}} step={1} min={0} max={4} initialValue={2}/>
          </Row>
        </ModalContent>
        <ButtonRow>
          <ModalButton $primary>확인</ModalButton>
          <ModalButton onClick={handleClose}>취소</ModalButton>
        </ButtonRow>
      </ModalWrapper>
    </ModalBackground>
  )
}

export default Modal;

const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
`

const ModalWrapper = styled.div`
  background-color: white;
  border-radius: 1rem;
  width: calc(100% - 7rem);
  margin: 2rem;
  padding: 1.5rem;
  min-height: 50%;
  display: flex;
  flex-direction: column;
`

const ModalContent = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`

const ModalTitle = styled.span`
  color: black;
  font-weight: bold;
  font-size: 2.4rem;
  margin-bottom: 1.5rem;
`

const Row = styled.div`
  display: flex;
  margin-bottom: 1.5rem;
  flex-direction: row;
  gap: 2rem;
  justify-content: space-between;
  align-items: center;
  min-height: 3rem;
`

const ButtonRow = styled(Row)`
  margin-top: auto;
  margin-bottom: 0;
  background-color: transparent;
`

const ModalLabel = styled.label`
  font-size: 2rem;
`

const ModalButton = styled.button`
  flex: 1;
  border: 1px solid black;
  border-radius: 1rem;
  height: 4rem;
  font-size: 1.6rem;
  background-color: ${props => props.$primary ? 'black' : 'white'};
  color: ${props => props.$primary ? 'white' : 'black'};
  font-weight: ${props => props.$primary ? 'bold' : '300'};
`

const ModalTextInput = styled.input`
  height: 3rem;
  border: 1px solid black;
  border-radius: 0.2rem;
`