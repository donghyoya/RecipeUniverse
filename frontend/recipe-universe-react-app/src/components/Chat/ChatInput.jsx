import { styled } from 'styled-components';
import { useState } from 'react';

const ChatInput = () => {

  return (
    <ChatInputLayout>
      <input placeholder='placeholder'/>
      <button />
    </ChatInputLayout>
  )
}

export default ChatInput;

const ChatInputLayout = styled.div`
  width: calc(100% - 4rem);
  height: 5rem;

  position: absolute;
  bottom: 0;

  display: flex;
  flex-direction: column;
  align-items: center;

  margin: 1rem 0;
  border-radius: 2.5rem;

  font-size: 1.6rem;
  padding-left: 2rem;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex: 1;
  border: 2px solid black;

  input {
    outline: none;
    border: 0;
    flex: 1;
    font-family: 'Interop';
    font-size: 1.6rem;
  }

  button {
    width: 4rem;
    height: 4rem;
    border: 0.2rem solid;
    border-radius: 2rem;
    background-color: transparent;
    margin: 0.5rem;
  }
`;