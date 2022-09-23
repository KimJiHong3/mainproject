package com.main.posts.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class PostDto {

    private String title;

    private String text;

    private String price;

    private String end_date;

    private String sheet_number;


}
