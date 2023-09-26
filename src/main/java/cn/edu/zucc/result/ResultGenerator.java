package cn.edu.zucc.result;


/**
 * 接口返回数据生成
 */
public class ResultGenerator {
    //private static final String DEFAULT_OK = "OK";

    public static Result genOkResult() {
        return (new Result()).setCode(ResultCode.OK).setMessage("OK");
    }

    public static Result genOkResult(Object data) {
        return (new Result()).setCode(ResultCode.OK).setMessage("OK").setData(data);
    }

    public static Result genOkResult(Object data, String message) {
        return (new Result()).setCode(ResultCode.OK).setMessage(message).setData(data);
    }

    public static Result genFailureResult(String message) {
        return (new Result()).setCode(ResultCode.FALURE).setMessage(message);
    }
}
