package cn.edu.whu.ashman.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;



/*
 * 计算两个字符串(英文字符)的相似度，简单的余弦计算，未添权重
 */

/**
 * @Author Liu WeiFan
 * @create 2020年7月11日 20：56
 */
@Component
public class CosineSimilar {
    Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();

    List<String> questionList;
    List<String> resultList = new ArrayList<String>();
    int[] tempArray = null;

    public CosineSimilar() throws Exception {
        File file = new File("src\\main\\resources\\question.txt");
        InputStream in = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        questionList = new ArrayList<String>();
        String line = null;
        while ((line = br.readLine()) != null) {
            questionList.add(line);
        }

    }

    private void setString1(String string1) {

        for (Character character1 : string1.toCharArray()) {  //拆解为向量
            if (vectorMap.containsKey(character1)) {
                vectorMap.get(character1)[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(character1, tempArray);
            }
        }
    }

    private void setString2(String string2) {
        for (Character character2 : string2.toCharArray()) {
            if (vectorMap.containsKey(character2)) {
                vectorMap.get(character2)[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(character2, tempArray);
            }
        }
    }

    // 求余弦相似度
    private double sim() {   //分子分母相除
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;    //余弦度结果返回,因为是余弦，返回结果越大，夹角越小，两个向量方向越接近，即两个字符串越相似
    }

    private Boolean isSimString(String s) {
        setString2(s);
        if (sim() > 0.8) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> sims(String message) {
        setString1(message);
        for (String s : questionList) {
            if (isSimString(s))
                resultList.add(s);
        }
        return resultList;
    }

    //求平方根
    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);  //先求平方和
        result = Math.sqrt(result);  //再开根号，就是求模
        return result;
    }

    // 求平方和，分母上，向量求模的平方
    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);  //获取key对应的值--数组
            result1 += (temp[0] * temp[0]);   //temp[0]存储的是第一个字符串对应的向量
            result2 += (temp[1] * temp[1]);   //temp[1]存储的是第二个字符串对应的向量
        }
        return result1 * result2;
    }

    // 点乘法，在分子上，向量相乘
    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();  //返回map中所有key值的列表
        for (Character character : keySet) {    //存储的key值都是不重复的
            int temp[] = paramMap.get(character);   //获取key对应的值
            result += (temp[0] * temp[1]);
        }
        return result;

    }
}