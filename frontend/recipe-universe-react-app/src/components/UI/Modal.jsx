import { styled } from 'styled-components';
import { useDispatch } from 'react-redux';

const Modal = (props) => {
  const dispatch = useDispatch();

  return (
    <ModalBackground onClick={props.onClose}>
      <ModalWrapper onClick={(e) => { e.stopPropagation(); }}>
        {props.children}
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