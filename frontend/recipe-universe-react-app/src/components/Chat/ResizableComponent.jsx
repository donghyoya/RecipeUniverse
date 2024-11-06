import React, { useState, useCallback, useRef, useEffect } from 'react';

const ResizableComponent = () => {
  const [height, setHeight] = useState(200);
  const [isResizing, setIsResizing] = useState(false);
  const resizableRef = useRef(null);
  const initialMousePos = useRef(0);

  const handleStyle = {
    width: '100%',
    height: '10px',
    background: 'blue',
    position: 'absolute',
    left: 0,
    top: '-5px',
    cursor: 'ns-resize',
    zIndex: 1000, // 다른 요소들보다 위에 오도록 설정
  };

  const handleMouseDown = useCallback((e) => {
    e.preventDefault(); // 텍스트 선택 방지
    setIsResizing(true);
    initialMousePos.current = e.clientY;
  }, []);

  const handleMouseMove = useCallback((e) => {
    if (!isResizing) return;

    const deltaY = initialMousePos.current - e.clientY;
    setHeight((prevHeight) => Math.max(50, prevHeight + deltaY));
    initialMousePos.current = e.clientY;
  }, [isResizing]);

  const handleMouseUp = useCallback(() => {
    setIsResizing(false);
  }, []);

  useEffect(() => {
    const handleMouseMoveGlobal = (e) => {
      handleMouseMove(e);
    };

    const handleMouseUpGlobal = () => {
      handleMouseUp();
    };

    if (isResizing) {
      document.addEventListener('mousemove', handleMouseMoveGlobal);
      document.addEventListener('mouseup', handleMouseUpGlobal);
    }

    return () => {
      document.removeEventListener('mousemove', handleMouseMoveGlobal);
      document.removeEventListener('mouseup', handleMouseUpGlobal);
    };
  }, [isResizing, handleMouseMove, handleMouseUp]);

  return (
    <div style={{ position: 'relative', height: '100vh' }}>
      <div
        ref={resizableRef}
        style={{
          width: '100%',
          height: `${height}px`,
          border: '1px solid black',
          position: 'absolute',
          bottom: 0,
          left: 0,
          padding: '10px',
          boxSizing: 'border-box',
          overflow: 'hidden', // 내용이 넘치지 않도록 설정
        }}
      >
        <div 
          onMouseDown={handleMouseDown} 
          style={handleStyle} 
        />
        <div style={{ marginTop: '10px' }}>
          Height: {height}px
        </div>
      </div>
    </div>
  );
};

// export default ResizableComponent;