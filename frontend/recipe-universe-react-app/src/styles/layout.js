import { styled } from "styled-components";

export const PageLayout = styled.div`
  display: flex;
  flex-direction: column;

  width: 100%;
  height: 100%;
  padding: 2.4rem;
  box-sizing: border-box;
`;

export const HeaderLayout = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;

  h1 {
    font-size: 2.4rem;
    font-weight: bold;
    margin: 0;
  }
`;

export const FooterButtonWrapper = styled.div`
  width: 100%;
  width: 100%;
  display: flex;
  gap: 1.6rem;
  padding: 0 1.6rem;
  box-sizing: border-box;
  flex: 1;
  align-items: flex-end;
  margin-top: 1.6rem;
`;

export const LikeButton = styled.button`
  &::before {
    content: ${(props) => (props.isLiked ? "♥" : "♡")};
  }
  border: 0;
  background-color: transparent;
  font-family: "Interop";
  font-size: 2rem;
  font-weight: bold;
`;
