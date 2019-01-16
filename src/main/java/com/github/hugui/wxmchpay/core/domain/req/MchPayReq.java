package com.github.hugui.wxmchpay.core.domain.req;

import lombok.Data;

/**
 * @author Mr.HuGui
 * @date 2019-01-16 10:18
 * @since 1.0.0
 */
@Data
public class MchPayReq {

    private String appid;

    /**
     * 商户号
     */
    private String mchid;
    /**
     * 用户openid
     */
    private String openid;
    /**
     * 自己平台交易号
     */
    private String tradeNo;
    /**
     * 金额 (单位为分)
     */
    private int amount;
    /**
     * 描述 不能为空
     */
    private String desc;
    /**
     * spbill_create_ip
     */
    private String ip;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * NO_CHECK：不校验真实姓名  FORCE_CHECK：强校验真实姓名
     */
    private String checkName;
    /**
     * 认证文件路劲
     */
    private String certPath;
    /**
     * 秘钥
     */
    private String mchKey;

}
