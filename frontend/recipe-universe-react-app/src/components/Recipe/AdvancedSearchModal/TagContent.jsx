import { styled } from 'styled-components';

import { tagMap } from './constants';
import Tag from '../../UI/Tag';

const TagContent = ({ selectedTagIds, onChange }) => {
  const handleOnClick = e => {
    const clickedId = e.target.id;

    onChange(prevTags => {
      const isSelected = prevTags.includes(clickedId);
      if (isSelected) {
        return prevTags.filter(tag => tag !== clickedId);
      }

      return [...prevTags, clickedId];
    });
  };

  return (
    <ModalContent key="tag-modal">
      {Object.entries(tagMap).map(([groupId, group]) => (
        <TagListWarpper key={groupId}>
          <span>{group.groupName}</span>
          <ScrollArea>
            <TagList $scrollable>
              {Object.entries(group.tags).map(([id, text]) => (
                <Tag
                  text={text}
                  key={id}
                  id={id}
                  onClick={handleOnClick}
                  primary={selectedTagIds.includes(id)}
                />
              ))}
            </TagList>
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

const TagList = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  gap: 0.5rem;

  width: ${props => (props.$scrollable ? '100%' : 'fit-content')};
  height: fit-content;
  flex-wrap: ${props => (props.$scrollable ? 'nowrap' : 'wrap')};
  overflow-x: ${props => (props.$scrollable ? 'auto' : 'visible')};
`;
