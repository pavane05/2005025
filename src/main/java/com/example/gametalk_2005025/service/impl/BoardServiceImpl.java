package com.example.gametalk_2005025.service.impl;

import com.example.gametalk_2005025.entitiy.board.Board;
import com.example.gametalk_2005025.repository.BoardRepository;
import com.example.gametalk_2005025.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;

    @Override
    public void write(Board board, MultipartFile file) throws Exception {

    }

    @Override
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    @Override
    public Board boardview(Integer id) {
        return boardRepository.findById(id).orElse(null);
    }

    @Override
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
