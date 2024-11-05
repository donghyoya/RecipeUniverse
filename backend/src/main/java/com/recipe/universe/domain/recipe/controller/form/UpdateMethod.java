package com.recipe.universe.domain.recipe.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

public enum UpdateMethod {
    @Schema(description = "해당 변경사항이 수정임(id속성 및 본문 필요)")
    UPDATE,
    @Schema(description = "해당 변경사항이 삭제임(id속성만 있으면 됨)")
    DELETE,
    @Schema(description = "해당 변경사항이 생성임(id 속성이 필요없으나 본문은 필요)")
    CREATE
}
