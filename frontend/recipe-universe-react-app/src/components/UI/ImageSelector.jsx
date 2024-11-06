import { useRef, useState } from 'react';
import Slider from 'react-slick';
import styled from 'styled-components';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

const ImageSelector = () => {
  const fileInputRef = useRef(null);
  const [uploadedImages, setUploadedImages] = useState([]);

  const handleSelectImage = () => {
    console.log('select');
    fileInputRef.current?.click();  // input 클릭 트리거
  }

  const handleFileChange = (e) => {
    console.log('changed', e.target.files);
    const file = e.target.files?.[0];
  
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setUploadedImages((prevImages) => [...prevImages, imageUrl]);
    }
  }

  const settings = {
    className: "center",
    centerMode: true,          // 중앙 모드 활성화
    infinite: false,
    speed: 500,
    slidesToShow: 1,           // 한 번에 보여줄 슬라이드 수
    slidesToScroll: 1,         // 한 번에 스크롤할 슬라이드 수
  };

  return (
    <CarouselWrapper>
      <FileInput className='file-input' type='file' accept=".png, .jpg, .jpeg, .webp" onChange={handleFileChange} ref={fileInputRef}/>
      <div>
        <Slider {...settings}>
          {uploadedImages.map((uploadedImage, idx) => (
            <Slide key={idx} $imageUrl={uploadedImage}></Slide>
          ))}
          <Slide onClick={handleSelectImage}><div>사진 업로드</div></Slide>
        </Slider>
      </div>
    </CarouselWrapper>
  );
};

const CarouselWrapper = styled.div`
  width: 100%;

  .slick-slide {
    padding: 0 2rem;
    box-sizing: border-box;
    width: 50%;
  }

  .slick-slide > div {
    transition: all 0.3s ease;
    width: 100%;
    aspect-ratio: 1 / 1;
  }

  .file-input {
    display: none;
  }
`;

const Slide = styled.div`
  width: 100%;
  height: 100%;
  color: black;
  font-size: 1.5rem;
  
  border-radius: 2rem;

  display: flex;
  justify-content: center;
  align-items: center;
  
  border: ${props => props.$imageUrl ? 0 : '1px solid black'};
  ${props => props.$imageUrl && `
    background-image: url(${props.$imageUrl});
    background-size: contain; 
    background-position: center;
    background-repeat: no-repeat;
  `}

  div {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;

    font-size: 2.4rem;
  }
`;

const FileInput = styled.input`
  display: none;

`

export default ImageSelector;