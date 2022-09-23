package com.main.posts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostResponseDto {

    private String title;

    private String text;

    private String price;

    private String end_date;

    private String sheet_number;
}
