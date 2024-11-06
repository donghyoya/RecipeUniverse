import { styled } from 'styled-components'

import resizerIcon from '../assets/icons/resize-slider.svg';

const ResizeHandle = (props) => {
  <ResizeHandleLayout onMouseDown={props.onMouseDown} />
}

export default ResizeHandle;

const ResizeHandleLayout = styled.img`
  width: 36px;
  height: 36px;
  background: blue;
  position: absolute;
  left: 0;
  top: 0px;
  cursor: ns-resize;
`;