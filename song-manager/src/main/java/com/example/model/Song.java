package com.example.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Lombok: 自动生成 Getters, Setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: 自动生成无参构造函数
@AllArgsConstructor // Lombok: 自动生成全参构造函数
public class Song {
    private Long id;
    private String title; // 歌曲名
    private String artist; // 歌手
    private String album; // 专辑
    private String genre; // 流派
}
