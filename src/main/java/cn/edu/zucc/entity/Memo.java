package cn.edu.zucc.entity;

import org.bson.types.BSONTimestamp;

import java.util.Date;

/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: chen hang
 * @create: 2022-03-08 14:45
 */
public class Memo {
    private BSONTimestamp date;
    // 内容
    private String text;
    // 0 医生 1
    private int type;
    // 有效 无效
    private boolean status;

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BSONTimestamp getDate() {
        return date;
    }

    public void setDate(BSONTimestamp date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
