export const FILTER_OPTIONS = {
  difficulty: {
    groupName: '난이도',
    options: [
      { id: 'easy', label: '쉬움' },
      { id: 'medium', label: '중간' },
      { id: 'hard', label: '어려움' },
    ],
  },
  time: {
    groupName: '조리시간',
    options: [
      { id: 'under30', label: '30분\n이하' },
      { id: '60', label: '1시간' },
      { id: '90', label: '1시간\n30분' },
      { id: '120', label: '2시간' },
      { id: 'over180', label: '3시간\n이상' },
    ],
  },
  servings: {
    groupName: '인원수',
    options: [
      { id: '1', label: '1인분' },
      { id: '2', label: '2인분' },
      { id: '3plus', label: '3인분\n이상' },
    ],
  },
};

export const SORT_OPTIONS = [
  { id: 'latest', label: '최신순' },
  { id: 'like-count', label: '좋아요 많은 순' },
  { id: 'review-count', label: '후기 많은 순' },
  { id: 'ratings', label: '별점순' },
];

export const TAG_OPTIONS = {
  type: {
    groupName: '종류',
    options: [
      { id: 'meal', label: '식사' },
      { id: 'dessert', label: '디저트' },
      { id: 'drink', label: '음료' },
      { id: 'side-dish', label: '반찬' },
      { id: 'pickle', label: '장아찌' },
      { id: 'snack', label: '간식' },
      { id: 'bread', label: '빵' },
    ],
  },
  taste: {
    groupName: '맛',
    options: [
      { id: 'spicy', label: '매콤' },
      { id: 'sweet', label: '달달' },
      { id: 'clean', label: '깔끔' },
      { id: 'light', label: '담백' },
      { id: 'plain', label: '고소' },
    ],
  },
  health: {
    groupName: '건강',
    options: [
      { id: 'diet', label: '다이어트' },
      { id: 'protein', label: '단백질' },
      { id: 'bulk-up', label: '벌크업' },
      { id: 'diabetes', label: '당뇨식단' },
      { id: 'osteoporosis', label: '골다공증' },
      { id: 'weight-management', label: '체중관리' },
      { id: 'nutritious', label: '몸보신' },
      { id: 'anemia', label: '빈혈' },
      { id: 'postpartum', label: '산후조리' },
    ],
  },
};

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
