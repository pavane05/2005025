package com.example.gametalk_2005025.service;

import com.example.gametalk_2005025.entitiy.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
    void write(Board board, MultipartFile file) throws Exception;

    Page<Board> boardList(Pageable pageable);

    Page<Board> boardSearchList(String searchKeyword, Pageable pageable);

    Board boardview(Integer id);

    void boardDelete(Integer id);
}
