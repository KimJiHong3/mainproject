package com.main.posts.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPatchDto {

    private String title;

    private String text;

    private String price;

    private String end_date;

    private String sheet_number;
}
