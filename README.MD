## 微信企业付款 Java SDK
#### SDK使用方式
```
@Test
    public void mchPayTest() {
        MchPayReq payReq = new MchPayReq();
        payReq.setAppid("wx11111111");
        payReq.setMchid("12333132312");
        payReq.setOpenid("oza1323132");
        ...

        MchPayResp payResp = mchPayService.mchPay(payReq);
    }
```