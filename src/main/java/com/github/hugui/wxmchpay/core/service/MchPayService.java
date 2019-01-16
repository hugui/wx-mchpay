package com.github.hugui.wxmchpay.core.service;

import com.caiyi.sport.core.domain.req.MchPayReq;
import com.caiyi.sport.core.domain.resp.MchPayResp;

/**
 * 微信企业付款
 *
 * @author Mr.HuGui
 * @date 2018-08-14 18:47
 * @since 1.0.0
 */
public interface MchPayService {

    /**
     * 微信企业付款
     *
     * @return 微信返回结果转object
     */
    MchPayResp mchPay(MchPayReq payReq);
}
