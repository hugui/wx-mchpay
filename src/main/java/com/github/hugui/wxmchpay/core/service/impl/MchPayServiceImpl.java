package com.github.hugui.wxmchpay.core.service.impl;

import com.caiyi.sport.core.domain.req.MchPayReq;
import com.caiyi.sport.core.domain.resp.MchPayResp;
import com.caiyi.sport.core.service.MchPayService;
import com.caiyi.sport.core.util.*;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Mr.HuGui
 * @date 2018-08-14 18:52
 * @since 1.0.0
 */
@Service
@Slf4j
public class MchPayServiceImpl implements MchPayService {

    private static final String MCH_PAY_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 微信企业付款
     *
     * @param payReq
     * @return 微信返回结果转object
     */
    @Override
    public MchPayResp mchPay(MchPayReq payReq) {
        SortedMap<String, String> paramMap = getParamsMap(payReq);

        String xmlStr = null;
        try {
            xmlStr = new String(XmlUtil.simpleMapToXml(paramMap).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("map转为简单的xml文件字符串异常");
        }
        log.info("mchUrl:{},mchId:{},certPath:{}", MCH_PAY_URL, payReq.getMchid(), payReq.getCertPath());
        String s = CertHttpUtil.postData(MCH_PAY_URL, xmlStr, payReq.getMchid(), payReq.getCertPath());

        Document doc = null;
        try {
            doc = DocumentHelper.parseText(s);
        } catch (DocumentException e) {
            log.error("返回结果解析出错，{}", e);
        }
        Map<String, Object> resultParams = MapUtil.dom2Map(doc);
        MchPayResp resp = null;
        try {
            resp = (MchPayResp) MapUtil.mapToObject(resultParams, MchPayResp.class);
            log.info("微信返回结果：{}", resp.toString());
        } catch (Exception e) {
            log.error("map转object异常");
        }
        return resp;
    }

    private SortedMap<String, String> getParamsMap(MchPayReq req) {
        SortedMap<String, String> paramMap = new TreeMap<>();
        paramMap.put("mch_appid", req.getAppid());
        paramMap.put("mchid", req.getMchid());//商户号
        paramMap.put("nonce_str", UniqueStrCreator.getRandomString(30));//随机字符串
        paramMap.put("partner_trade_no", req.getTradeNo());//商户订单号
        paramMap.put("openid", req.getOpenid());//商户appid下，某用户的openid
        paramMap.put("check_name", req.getCheckName());//NO_CHECK：不校验真实姓名  FORCE_CHECK：强校验真实姓名
        paramMap.put("re_user_name", req.getRealName());
        paramMap.put("amount", req.getAmount() + "");//企业付款金额，单位为分(最低30)
        paramMap.put("desc", req.getDesc());//企业付款操作说明信息。必填。
        paramMap.put("spbill_create_ip", req.getIp());//ip
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            log.info("paramMap参数,key=:{}，value=:{}", entry.getKey(), entry.getValue());
        }

        String sign = WXSignature.createSign("UTF-8", paramMap, req.getMchKey());
        paramMap.put("sign", sign);//签名
        log.info("sign:{}", sign);

        return paramMap;
    }
}