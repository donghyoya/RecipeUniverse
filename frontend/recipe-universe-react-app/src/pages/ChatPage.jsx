import React, { useState, useCallback, useRef, useEffect } from 'react';
import { styled } from 'styled-components';
import { useSelector, useDispatch } from 'react-redux';
import { motion, AnimatePresence } from 'framer-motion';
import useWindowSize from '../hooks/useWindowSize';

import { hide } from '../store/chatSlice';
import ChatInput from '../components/Chat/ChatInput';

const MIN_HEIGHT = 83;
const BOTTOM_NAVIGATION_HEIGHT = 60;

const ChatPage = () => {
  const [height, setHeight] = useState(83);
  const [isResizing, setIsResizing] = useState(false);
  const chatDisplay = useSelector((state) => state.chat.display);
  const resizableRef = useRef(null);
  const initialMousePos = useRef(0);
  const { height: windowHeight } = useWindowSize();
  const dispatch = useDispatch();

  const handleMouseDown = useCallback((e) => {
    setIsResizing(true);
    initialMousePos.current = e.clientY;
  }, []);

  const handleMouseMove = useCallback(
    (e) => {
      if (!isResizing || !resizableRef.current) return;

      const deltaY = initialMousePos.current - e.clientY;
      setHeight((prevHeight) => {
        const newHeight = prevHeight + deltaY;
        const maxHeight = windowHeight - BOTTOM_NAVIGATION_HEIGHT; 
        return Math.max(Math.min(newHeight, maxHeight), MIN_HEIGHT - 1);
      });

      initialMousePos.current = e.clientY;
    },
    [isResizing, windowHeight]
  );

  const handleMouseUp = useCallback(() => {
    setIsResizing(false);
  }, []);

  useEffect(() => {
    document.addEventListener('mousemove', handleMouseMove);
    document.addEventListener('mouseup', handleMouseUp);

    return () => {
      document.removeEventListener('mousemove', handleMouseMove);
      document.removeEventListener('mouseup', handleMouseUp);
    };
  }, [handleMouseMove, handleMouseUp]);

  useEffect(() => {
    if (chatDisplay === 'MINIMIZED') {
      setHeight(MIN_HEIGHT);
    }
    if (chatDisplay === 'MAXIMIZED') {
      const newHeight = windowHeight - BOTTOM_NAVIGATION_HEIGHT;
      setHeight(newHeight);
    }
  }, [chatDisplay, windowHeight])
  
  const heightTransition = isResizing ? {
    type: "tween",
    duration: 0
  } : {
    type: "tween",
    duration: 0.3, 
    ease: "easeOut"
  };

  useEffect(() => {
    if (height < MIN_HEIGHT && !isResizing) {
      dispatch(hide());
    }
  }, [height, isResizing]);

  return (
    <AnimatePresence>
    {chatDisplay !== 'HIDDEN' && (
      <PageLayout 
        initial={{ y: BOTTOM_NAVIGATION_HEIGHT }}
        animate={{ y: 0 }}
        exit={{ y: height }}
        transition={{ type: "spring", stiffness: 300, damping: 30 }}
      >
        <Overlay />
        <InnerLayout 
          ref={resizableRef}
          animate={{ 
            height: height
          }}
          transition={heightTransition}
        >
          <Handle onMouseDown={handleMouseDown}>
            <HandleIcon />
          </Handle>
          <ChatInput />
        </InnerLayout>
      </PageLayout>
    )}
  </AnimatePresence>
  );
};

export default ChatPage;

const PageLayout = styled(motion.div)`
  position: fixed;
  height: 100%;
  bottom: 6rem;
  width: 100%;
  pointer-events: none;
`;

const Overlay = styled.div`
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
`;

const InnerLayout = styled(motion.div)`
  width: 100%;
  height: ${props => `${props.$height}px`};
  max-height: 100%;
  border: 2px solid black;
  border-radius: 1rem 1rem 0 0;
  position: absolute;
  bottom: 0;
  left: 0;
  padding: 10px;
  box-sizing: border-box;
  background-color: white;
  pointer-events: auto;
`;

const Handle = styled.div`
  width: 100%;
  height: 1.5rem;
  position: absolute;
  left: 0;
  top: 0;
  cursor: ns-resize;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const HandleIcon = styled.div`
  width: 6rem;
  height: 0.5rem;
  background-color: black;
  border-radius: 0.25rem;
`;