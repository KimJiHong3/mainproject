package com.main.posts.repository;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.persistence.*;


@Table(name = "post")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor/// 애러 난부분
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String end_date;

    @Column(nullable = false)
    private String sheet_number;



    public void changePosts(String title, String text, String price,
                 String end_date, String sheet_number) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.end_date = end_date;
        this.sheet_number = sheet_number;
    }


}
