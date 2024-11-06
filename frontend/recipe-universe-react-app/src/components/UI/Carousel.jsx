import React from 'react';
import Slider from 'react-slick';
import styled from 'styled-components';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const CarouselWrapper = styled.div`
  width: 100%;
  margin: 0;
  overflow: hidden;

  .slick-dots {
    bottom: 1rem;

    li button:before {
      color: white;
    }

    li.slick-active button:before {
      color: white;
    }
  }
`;

const Slide = styled.div`
  width: 100%;
  aspect-ratio: 1 / 1;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 1.5rem;
  background-color: ${props => props.$backgroundColor};
  margin: 0;
`;

const Carousel = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
  };

  return (
    <CarouselWrapper>
      <Slider {...settings}>
        <Slide $backgroundColor='#3b82f6'>슬라이드 1</Slide>
        <Slide $backgroundColor='#22c55e'>슬라이드 2</Slide>
        <Slide $backgroundColor='#ef4444'>슬라이드 3</Slide>
      </Slider>
    </CarouselWrapper>
  );
};

export default Carousel;