import { styled } from 'styled-components';

const SortContent = ({ selectedOrderId, onChange }) => {
  const sortOptions = [
    { id: 'latest', label: '최신순' },
    { id: 'like-count', label: '좋아요 많은 순' },
    { id: 'review-count', label: '후기 많은 순' },
    { id: 'ratings', label: '별점순' },
  ];

  const handleOnChange = e => {
    onChange(e.target.id);
  };

  return (
    <ModalContent key="sort-modal">
      {sortOptions.map(sortOption => (
        <FormField key={sortOption.id}>
          <label>{sortOption.label}</label>
          <input
            type="radio"
            name="sort"
            onChange={handleOnChange}
            id={sortOption.id}
            checked={selectedOrderId === sortOption.id}
          />
        </FormField>
      ))}
    </ModalContent>
  );
};

export default SortContent;

const ModalContent = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`;

const FormField = styled.div`
  display: flex;
  flex-direction: row;
  gap: 2rem;
  justify-content: space-between;
  align-items: center;
  min-height: 4rem;

  & > label {
    font-size: 2rem;
  }

  & > input[type='radio'] {
    appearance: none;
    width: 1.5rem;
    height: 1.5rem;
    border: 2px solid #ccc;
    border-radius: 50%;

    &:checked {
      border-color: black;
      background: white;
      box-shadow: inset 0 0 0 4px black;
    }
  }
`;
