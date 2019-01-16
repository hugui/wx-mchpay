package com.github.hugui.wxmchpay.core.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * @author
 * @ClassName: XMLUtils
 * @Description: XML文件处理工具类
 * @date 2017年3月10日 下午12:50:35
 */
public class XmlUtil {
    private static String RT = "\r\n";


    /**
     * @param map
     * @return String 返回类型
     * @Title：simpleMapToXml
     * @Description：map转为简单的xml文件字符串（一级节点）
     */
    public static String simpleMapToXml(Map<String, String> map) {
        StringBuilder xmlString = new StringBuilder();
        xmlString.append("<?xml version='1.0' encoding='UTF-8'?>").append(RT);
        xmlString.append("<xml>").append(RT);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            xmlString.append("<" + key + ">" + entry.getValue() + "</" + key + ">").append(RT);
        }
        xmlString.append("</xml>").append(RT);
        return xmlString.toString();
    }

    public static Map<String, String> getMapFromXML(String xmlString) throws Exception {
        // 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(xmlString.getBytes());
        Document document = builder.parse(is);


        // 获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, String> map = new HashMap<String, String>();
        int i = 0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if (node instanceof Element) {
                map.put(node.getNodeName(), node.getTextContent());
            }
            i++;
        }
        return map;
    }

    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        // 创建输出流  
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml  
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口  
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

}