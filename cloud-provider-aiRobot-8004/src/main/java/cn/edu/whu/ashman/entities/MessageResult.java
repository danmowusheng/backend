package cn.edu.whu.ashman.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Liu WeiFan
 * @create 2020年7月11日 20：56
 */
@Data
@NoArgsConstructor
public class MessageResult<T> {
    //错误码：404
    private Integer code;
    //状态信息
    private String message;
    //json数据对象
    private T data;
    //问题预测结果
    private List<String> predictQuestion;
    public MessageResult(Integer code,String message){
        this.code = code;
        this.message = message;
        this.data = null;
        this.predictQuestion = null;
    }
    public MessageResult(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
        this.predictQuestion = null;
    }
    public MessageResult(Integer code,List<String> predictQuestion){
        this.code = code;
        this.message = null;
        this.data = null;
        this.predictQuestion = predictQuestion;
    }
}
