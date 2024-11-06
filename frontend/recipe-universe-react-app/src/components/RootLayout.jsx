import { Outlet } from "react-router-dom";
import { styled } from 'styled-components';

import BottomNavigation from "./UI/BottomNavigation";
import Modal from './UI/Modal';
import { useSelector, useDispatch } from 'react-redux';
import { closeModal } from '../store/modalSlice';
import { useCallback } from 'react';

import ChatPage from '../pages/ChatPage';

const RootPage = (props) => {
  const isOpen = useSelector((state) => state.modal.isOpen);
  const dispatch = useDispatch();

  const handleCloseModal = useCallback(() => {
    dispatch(closeModal());
  }, [dispatch]);

  return (
    <RootPageLayout>
      {isOpen && <Modal closeModal={handleCloseModal}/>}
      <OutletWrapper>
        <Outlet />
      </OutletWrapper>
      <BottomNavigation />
      <ChatPage key="chat-page" />
    </RootPageLayout>
  );
}

export default RootPage;

const RootPageLayout = styled.div`
  width: 100%;
  height: calc(100vh - 6rem);
  display: flex;
  flex-direction: column;
  overflow-y: auto;

  &::-webkit-scrollbar {
    display: none;
  }
`;

const OutletWrapper = styled.div`
  flex: 1;
  display: flex;
  overflow-y: auto;
  
  &::-webkit-scrollbar {
    display: none;
  }
`;