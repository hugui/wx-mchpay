package com.github.hugui.wxmchpay.core.domain.resp;

import lombok.Data;

/**
 * @author Mr.HuGui
 * @date 2018-08-15 10:43
 * @since 1.0.0
 */
@Data
public class MchPayResp {
    private String return_code;
    private String return_msg;
    private String mch_appid;
    private String mchid;
    private String device_info;
    private String nonce_str;
    private String result_code;
    private String err_code;
    private String err_code_des;
    private String partner_trade_no;
    private String payment_no;
    private String payment_time;
}