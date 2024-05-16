package kr.jay.pilotproject.interfaces.dto;

import kr.jay.pilotproject.domain.builder.BuilderPost;

/**
 * PostResponse
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */
public record PostResponse(
    Long id,
    String title,
    String content,
    String authorName
) {

    public PostResponse(final BuilderPost builderPost) {
        this(builderPost.getId(), builderPost.getTitle(), builderPost.getContent(),
            builderPost.getBuilderUser().getName());
    }
}
