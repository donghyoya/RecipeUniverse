import { FILTER_OPTIONS, TAG_OPTIONS, SORT_OPTIONS } from './constants';

export const getSelectedOptions = (
  selectedFilterIds,
  selectedTagIds,
  selectedOrderId,
  handleFilterOnClick = () => {},
  handleTagOnClick = () => {}
) => {
  const options = [];

  // 필터 옵션 처리
  Object.entries(selectedFilterIds).forEach(([groupId, selectedIds]) => {
    if (selectedIds.length) {
      const labels = selectedIds.map(
        id => FILTER_OPTIONS[groupId].options.find(opt => opt.id === id).label
      );

      options.push({
        text: labels.join(' ~ '),
        key: groupId,
        id: groupId,
        icon: 'close',
        onClick: handleFilterOnClick,
      });
    }
  });

  // 태그 옵션 처리
  if (selectedTagIds.length) {
    const allTags = Object.values(TAG_OPTIONS).reduce(
      (acc, group) => [...acc, ...group.options],
      []
    );

    options.push(
      ...selectedTagIds.map(id => {
        const tag = allTags.find(tag => tag.id === id);
        return {
          text: tag.label,
          key: id,
          id,
          icon: 'close',
          onClick: handleTagOnClick,
        };
      })
    );
  }

  // 정렬 기준 처리
  const sortOption = SORT_OPTIONS.find(opt => opt.id === selectedOrderId);
  options.push({
    text: sortOption.label,
    key: selectedOrderId,
    id: selectedOrderId,
    icon: 'sort',
  });

  return options;
};
