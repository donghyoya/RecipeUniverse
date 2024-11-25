import { styled } from 'styled-components';

import { FooterButtonWrapper } from '../../styles/layout';
import Button from './Button';

const Modal = ({ onClose, onConfirm, children }) => {
  const handleClose = e => {
    e.stopPropagation();
    onClose();
  };

  return (
    <ModalBackground onClick={onClose}>
      <ModalWrapper
        onClick={e => {
          e.stopPropagation();
        }}
      >
        {children}
        <FooterButtonWrapper>
          <Button primary onClick={onConfirm}>
            확인
          </Button>
          <Button onClick={handleClose}>취소</Button>
        </FooterButtonWrapper>
      </ModalWrapper>
    </ModalBackground>
  );
};

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
`;

const ModalWrapper = styled.div`
  max-width: 60rem;
  max-height: 60rem;
  background-color: white;
  border-radius: 1rem;
  width: calc(100% - 7rem);
  margin: 2rem;
  padding: 1.5rem;
  min-height: 50%;
  display: flex;
  flex-direction: column;
`;
