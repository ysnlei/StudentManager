package club.justca.studentmanager.utils;

import club.justca.studentmanager.entity.Score;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class GetScoreUtil {
    /**
     * 登录并获取成绩页面的全部html
     *
     * @param loginUrl 登录请求地址
     * @param scoreUrl 成绩页面地址
     * @param username 学号
     * @param password 密码
     * @return 如果密码正确，返回成绩页面，如果密码不正确，返回null
     */
    public static Element getHtmlElement(String loginUrl, String scoreUrl,
                                         String username, String password) {
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        PostMethod postMethod = new PostMethod(loginUrl);
        NameValuePair[] postData = {
                new NameValuePair("USERNAME", username),
                new NameValuePair("PASSWORD", password)
        };
        postMethod.setRequestBody(postData);
        try {
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            httpClient.executeMethod(postMethod);
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuilder stringBuilder = new StringBuilder();
            for (Cookie c : cookies) {
                stringBuilder.append(c.toString()).append(";");
            }
            GetMethod getMethod = new GetMethod(scoreUrl);
            getMethod.setRequestHeader("Cookie", stringBuilder.toString());
            httpClient.executeMethod(getMethod);
            String result = getMethod.getResponseBodyAsString();
            Document html = Jsoup.parse(result);
            if (html.getElementsByTag("title").get(0).text().equals("学生个人考试成绩")) {
                return html;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取成绩表格<table></table>代码
     *
     * @param html 全文html
     * @return 表格html
     */
    public static Element getScoreTable(Element html) {
        return html.getElementById("dataList");
    }

    /**
     * 获取成绩表表头
     *
     * @param table <table></table>标签的Element元素
     * @return 表头所有名称列表
     */
    public static List<String> getTableHead(Element table) {
        List<String> theadList = new ArrayList<>();
        Elements ths = table.getElementsByTag("th");
        for (Element th : ths) {
            theadList.add(th.text());
        }
        return theadList;
    }

    /**
     * 获取分数列表
     *
     * @param table    <table></table>标签的Element元素
     * @param username 学生学号
     * @return 所有成绩列表
     */
    public static List<Score> getScoreList(Element table, String username) {
        List<Score> scoreList = new ArrayList<>();
        Elements trs = table.getElementsByTag("tr");
        for (int i = 1; i < trs.size(); i++) {
            Elements tds = trs.get(i).getElementsByTag("td");
            Score score = new Score();
            score.setStudentNum(username);
            score.setCourseTerm(tds.get(1).text());
            score.setCourseNum(tds.get(2).text());
            score.setCourseName(tds.get(3).text());
            String courseScore = tds.get(4).text();
            switch (courseScore) {
                case "优":
                    score.setScore(95);
                    break;
                case "良":
                    score.setScore(85);
                    break;
                case "中":
                case "通过":
                case "合格":
                    score.setScore(75);
                    break;
                case "及格":
                    score.setScore(65);
                    break;
                case "不及格":
                case "不合格":
                case "不通过":
                    score.setScore(0);
                    break;
                default:
                    score.setScore(Integer.parseInt(courseScore));
                    break;
            }
            score.setCourseCredit(tds.get(5).text());   //学分
            score.setCourseHour(tds.get(6).text());     //学时
            score.setExamination(tds.get(7).text());
            score.setCourseNature(tds.get(8).text());   //课程性质
            scoreList.add(score);
        }
        return scoreList;
    }

    /**
     * 获取教务系统中学生的学号
     *
     * @param html 成绩页面的html
     * @return 学生的学号
     */
    public static String getStudentNum(Element html) {
        String studentInfo = html.getElementById("Top1_divLoginName").text();
        int start = studentInfo.indexOf("(");
        return studentInfo.substring(start + 1, studentInfo.length() - 1);
    }
}
