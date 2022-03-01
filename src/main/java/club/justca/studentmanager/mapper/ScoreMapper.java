package club.justca.studentmanager.mapper;

import club.justca.studentmanager.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {
    List<Score> findByStudentNum(String studentNum);

    Integer insertScore(Score score);

    Integer deleteByStudentNum(String studentNum);
}
