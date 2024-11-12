import { styled } from 'styled-components';

import TagList from '../../UI/TagList';

const TagContent = () => {
  const tagGroups = [
    {
      groupName: '종류',
      tags: [
        { text: '식사' },
        { text: '디저트' },
        { text: '음료' },
        { text: '반찬' },
        { text: '젓갈' },
        { text: '과자' },
        { text: '간식' },
        { text: '빵' },
      ],
    },
    {
      groupName: '입맛',
      tags: [
        { text: '매콤' },
        { text: '달달' },
        { text: '깔끔' },
        { text: '담백' },
        { text: '고소' },
      ],
    },
    {
      groupName: '건강',
      tags: [
        { text: '다이어트' },
        { text: '단백질' },
        { text: '벌크업' },
        { text: '당뇨식단' },
        { text: '골다공증' },
        { text: '체중관리' },
        { text: '몸보신' },
        { text: '빈혈' },
        { text: '산후조리' },
      ],
    },
  ];

  return (
    <ModalContent key="tag-modal">
      {tagGroups.map((tapGroup, idx) => (
        <TagListWarpper key={idx}>
          <span>{tapGroup.groupName}</span>
          <ScrollArea>
            <TagList tags={tapGroup.tags} scrollable />
          </ScrollArea>
        </TagListWarpper>
      ))}
    </ModalContent>
  );
};

export default TagContent;

const ModalContent = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`;

const TagListWarpper = styled.div`
  width: 100%;
  height: max-content;
  display: flex;
  flex-direction: row;
  align-items: center;
  margin: 0.7rem 0;

  & > span {
    width: 5rem;
    font-size: 1.8rem;
  }
`;

const ScrollArea = styled.div`
  width: calc(100% - 5rem);
`;
