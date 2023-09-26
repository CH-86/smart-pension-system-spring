package cn.edu.zucc.python;

/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: chen hang
 * @create: 2022-03-12 12:04
 */
public class UserInfo {
    //    age:年龄
    //    sex:性别(1=男，0=女)
    //    cp:疼痛类型(0=典型心绞痛；1=非典型心绞痛；2=非心绞痛；3=没有症状)
    //    trestbps：静息血压(解释：静息心率指在清醒、不活动的安静状态下，每分钟心跳的次数。静息血压就是在此状态下的测量血压)
    //    chol：胆固醇
    //    fbs: 人的空腹血糖（> 120 mg/dl=1； 0=假）解释：空腹血糖(饭前/餐前血糖)： 80 - 130mg/dL. 餐后2小时血糖： 80 - 160mg/dL.
    //    restecg：心电图(0=正常，1=患有ST-T波异常，2=根据Estes的标准显示可能或确定的左心室肥大,严重的情况)
    //    thalach：最大的心率
    //    exang：运动引起的心绞痛(1=是；0=不是)
    //    oldpeak:相对于休息来说运动引起的ST段抑制 解释:(http://heart.dxy.cn/article/143557)
    //    slope:运动高峰的心电图(1=上坡，2=平坦，3=下坡)
    //    ca: 萤光显色的主要血管数目（0-4）荧光显色主要是跟糖尿病有一些关系
    //    thal: 一种称为地中海贫血的血液疾病（3=正常； 6=固定缺陷； 7=可逆缺陷）
    //    target: 心脏病（0=否，1=是）
    //  63,1,3,145,233,1,0,150,0,2.3,0,0,1,1
    // 13个字段 1个状态
    public static String[] Heart = {
            "age",
            "sex",
            "cp",
            "trestbps",
            "chol",
            "fbs",
            "restecg",
            "thalach",
            "exang",
            "oldpeak",
            "slope",
            "ca",
            "thal"};

}
