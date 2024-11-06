import { styled } from 'styled-components';

import googleLoginButton from '../../assets/google-login.svg'

const GoogleLoginButton = (props) => {
  return (
    <ButtonLayout onClick={props.onClick}>
      <img src={googleLoginButton} width='200px' alt='google login button'/>
    </ButtonLayout>
  )
}

export default GoogleLoginButton;

const ButtonLayout = styled.button`
  padding: 0;
  background-color: transparent;
  border: 0;
`