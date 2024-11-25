export const FILTER_OPTIONS = {
  difficulty: {
    groupName: '난이도',
    tags: {
      easy: '쉬움',
      medium: '중간',
      hard: '어려움',
    },
  },
  time: {
    groupName: '조리시간',
    tags: {
      under30: '30분 이하',
      60: '1시간',
      90: '1시간 30분',
      120: '2시간',
      over180: '3시간 이상',
    },
  },
  servings: {
    groupName: '인원수',
    tags: {
      1: '1인분',
      2: '2인분',
      '3plus': '3인분 이상',
    },
  },
};

export const SORT_OPTIONS = {
  latest: '최신순',
  'like-count': '좋아요 많은 순',
  'review-count': '후기 많은 순',
  ratings: '별점순',
};

export const tagMap = {
  type: {
    groupName: '종류',
    tags: {
      meal: '식사',
      dessert: '디저트',
      drink: '음료',
      'side-dish': '반찬',
      pickle: '장아찌',
      snack: '간식',
      bread: '빵',
    },
  },

  taste: {
    groupName: '맛',
    tags: {
      spicy: '매콤',
      sweet: '달달',
      clean: '깔끔',
      light: '담백',
      plain: '고소',
    },
  },

  health: {
    groupName: '건강',
    tags: {
      diet: '다이어트',
      protein: '단백질',
      'bulk-up': '벌크업',
      diabetes: '당뇨식단',
      osteoporosis: '골다공증',
      'weight-management': '체중관리',
      nutritious: '몸보신',
      anemia: '빈혈',
      postpartum: '산후조리',
    },
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
  Object.entries(selectedFilterIds).forEach(([id, values]) => {
    const filterLabels = values.map(value => FILTER_OPTIONS[id].tags[value]);
    if (filterLabels.length) {
      options.push({
        text: filterLabels.join(' ~ '),
        key: id,
        id,
        icon: 'close',
        onClick: handleFilterOnClick,
      });
    }
  });

  // 태그 옵션 처리
  if (selectedTagIds.length) {
    const flattenTagMap = Object.values(tagMap).reduce(
      (acc, group) => ({ ...acc, ...group.tags }),
      {}
    );
    options.push(
      ...selectedTagIds.map(id => {
        return {
          text: flattenTagMap[id],
          key: id,
          id,
          icon: 'close',
          onClick: handleTagOnClick,
        };
      })
    );
  }

  // 정렬 기준 처리
  options.push({
    text: SORT_OPTIONS[selectedOrderId],
    key: selectedOrderId,
    id: selectedOrderId,
    icon: 'sort',
  });

  return options;
};
