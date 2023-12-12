package com.example.gametalk_2005025.dto.post;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long post_id;                  // PK
    private String title;                  // 제목
    private String content;                // 내용
    private String writer;                 // 작성자
    private int viewCnt;                   // 조회 수
    private Boolean noticeYn;              // 공지글 여부
    private Boolean deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;     // 글 작성일
    private LocalDateTime modifiedDate;    // 최종 수정일시

}