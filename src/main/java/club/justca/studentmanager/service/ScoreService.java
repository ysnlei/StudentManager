package club.justca.studentmanager.service;

import club.justca.studentmanager.entity.Score;

import java.util.List;

public interface ScoreService {
    List<Score> findByStudentNum(String studentNum);

    Integer updateScore(String username, String password);

    Integer deleteByStudentNum(String studentNum);
}
