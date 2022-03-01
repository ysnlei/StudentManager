package club.justca.studentmanager.utils;

import club.justca.studentmanager.entity.Score;

import java.util.List;

public class ScoreUtil {
    public static Double getGPA(List<Score> scoreList) {
        double gpa_average = 0;
        double credit_total = 0;
        for (Score score : scoreList) {
            Integer score1 = score.getScore();
            double gpa = (score1 / 10.0) - 5.0;
            credit_total+=Double.parseDouble(score.getCourseCredit());
            gpa_average = gpa_average + gpa * Double.parseDouble(score.getCourseCredit());
        }
        if (scoreList.size() > 0) {
            gpa_average /= credit_total;
        }
        return gpa_average;
    }
}
