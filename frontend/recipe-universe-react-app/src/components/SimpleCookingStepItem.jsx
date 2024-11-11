import { styled } from 'styled-components';

const SimpleCookingStepItem = (props) => {
  return (
    <ItemLayout>
      <ImageWrapper>
        <img src={props.img} alt='cooking step'/>
        <span>{props.time}</span>
      </ImageWrapper>
      <p>{props.description}</p>
    </ItemLayout>
  )
}

export default SimpleCookingStepItem;

const ItemLayout = styled.div`
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: row;
  gap: 1.5rem;

  p {
    font-size: 1.6rem;
    margin: 0;
  }
`

const ImageWrapper = styled.div`
  width: 50%;
  aspect-ratio: 1 / 1;
  position: relative;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  span {
    position: absolute;
    bottom: 0;
    right: 0;
    margin: 0.5rem;
    font-size: 1.6rem;
  }
`