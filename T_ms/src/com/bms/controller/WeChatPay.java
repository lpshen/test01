package com.bms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.common.MD5;
import com.bms.common.SignUtils;
import com.bms.common.SwiftpassConfig;
import com.bms.common.XmlUtils;

/**
 * 
 * @Project：mybatisForSpring3
 * @ClassName：AccountController
 * @Description：TODO
 * @author：lixiu
 * @CreateTime：Jul 30, 2011 1:55:49 PM
 * @Modifier：lixiu
 * @ChangeTime：Jul 30, 2011 1:55:49 PM
 * @Remark：TODO
 * @version 1.0
 */
@Controller
@RequestMapping("/weChat")
public class WeChatPay {
    public static Map<String,String> orderResult; //用来存储订单的交易状态(key:订单号，value:状态(0:未支付，1：已支付))  ---- 这里可以根据需要存储在数据库中
    public static int orderStatus=0;
//	/**
//	 * 本地查询订单支付状态
//	 * @throws Exception 
//	 */
//
//	@RequestMapping("/PayResultQuery")
//	public ModelAndView add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		req.setCharacterEncoding("utf-8");
//        resp.setCharacterEncoding("utf-8");
//        //System.out.println("订单查询");
//        String out_trade_no = req.getParameter("out_trade_no");
//        
//        String res = null;//0表示未支付，1表示已支付
//        
//        if(this.orderResult != null && this.orderResult.containsKey(out_trade_no)){
//        	
//            res = this.orderResult.get(out_trade_no);
//            
//        }else{
//            res = "0";
//        }
////        if(res.startsWith("<")){
////            res.setHeader("Content-type", "text/xml;charset=UTF-8");
////        }else{
////            response.setHeader("Content-type", "text/html;charset=UTF-8");
////        }
//        resp.getWriter().print(res);
//		return null;
//    }

//	/**
//	 * 通知暂时不用
//	 */
//	@RequestMapping("/addSubmit")
//	public ModelAndView create( HttpServletRequest req,HttpServletResponse resp) {
//        try {
//            req.setCharacterEncoding("utf-8");
//            resp.setCharacterEncoding("utf-8");
//            resp.setHeader("Content-type", "text/html;charset=UTF-8");
//            String resString = XmlUtils.parseRequst(req);
//            System.out.println("通知内容：" + resString);
//            
//            String respString = "fail";
//            if(resString != null && !"".equals(resString)){
//                Map<String,String> map = XmlUtils.toMap(resString.getBytes(), "utf-8");
//                String res = XmlUtils.toXml(map);
//                System.out.println("通知内容：" + res);
//                if(map.containsKey("sign")){
//                    if(!SignUtils.checkParam(map, SwiftpassConfig.key)){
//                        res = "验证签名不通过";
//                        respString = "fail";
//                    }else{
//                        String status = map.get("status");
//                        if(status != null && "0".equals(status)){
//                            String result_code = map.get("result_code");
//                            if(result_code != null && "0".equals(result_code)){
//                                if(this.orderResult == null){
//                                	this.orderResult = new HashMap<String,String>();
//                                }
//                                String out_trade_no = map.get("out_trade_no");
//                                this.orderResult.put(out_trade_no, "1");
//                                //System.out.println(TestPayServlet.orderResult);
//                            } 
//                        } 
//                        respString = "success";
//                    }
//                }
//            }
//            resp.getWriter().write(respString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		return null;
//
//	}
	//支付
	@RequestMapping("/pay")
	public ModelAndView findAll(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        
        SortedMap<String,String> map = XmlUtils.getParameterMap(req);
        
        map.put("mch_id", SwiftpassConfig.mch_id);
        //重复提交的时候直接查询本地的状态
       /* if(orderResult != null && orderResult.containsKey(map.get("out_trade_no"))){
            String status = "0".equals(orderResult.get(map.get("out_trade_no"))) ? "未支付" : "已支付";
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            resp.getWriter().write(status);
        }else{*/
            map.put("notify_url", SwiftpassConfig.notify_url);
            map.put("nonce_str", String.valueOf(new Date().getTime()));
            
            Map<String,String> params = SignUtils.paraFilter(map);
            StringBuilder buf = new StringBuilder((params.size() +1) * 10);
            SignUtils.buildPayParams(buf,params,false);
            String preStr = buf.toString();
            String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
            map.put("sign", sign);
            
            String reqUrl = SwiftpassConfig.req_url;
            System.out.println("reqUrl：" + reqUrl);
            
            System.out.println("reqParams:" + XmlUtils.parseXML(map));
            CloseableHttpResponse response = null;
            CloseableHttpClient client = null;
            String res = null;
            try {
                HttpPost httpPost = new HttpPost(reqUrl);
                StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
                httpPost.setEntity(entityParams);
                httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                if(response != null && response.getEntity() != null){
                    Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                    res = XmlUtils.toXml(resultMap);
                    if(resultMap.containsKey("sign")){
                        if(!SignUtils.checkParam(resultMap, SwiftpassConfig.key)){
                            res = "验证签名不通过";
                        }else{
                            if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))){
                                if(orderResult == null){
                                    orderResult = new HashMap<String,String>();
                                }
                                orderResult.put(map.get("out_trade_no"), "0");//初始状态
                                String code_img_url = resultMap.get("code_img_url");
                                req.setAttribute("code_img_url", code_img_url);
                                req.setAttribute("out_trade_no", map.get("out_trade_no"));
                                req.setAttribute("total_fee", map.get("total_fee"));
                                req.setAttribute("body", map.get("body"));
                                return new ModelAndView("/ots/QRCode.jsp");
                            }else{
                                req.setAttribute("result", res);
                            }
                        }
                    } 
                }else{
                    res = "操作失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
                res = "系统异常";
            } finally {
                if(response != null){
                    response.close();
                }
                if(client != null){
                    client.close();
                }
            }
//            if(res.startsWith("<")){
//                resp.setHeader("Content-type", "text/xml;charset=UTF-8");
//            }else{
//                resp.setHeader("Content-type", "text/html;charset=UTF-8");
//            }
//            resp.getWriter().write(res);
        //}
			return new ModelAndView("/ots/error.jsp").addObject("res", res);
    

	}
	//订单查询 使用
	@RequestMapping("/payResultQuery")
	public ModelAndView update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        
        SortedMap<String,String> map = XmlUtils.getParameterMap(req);
        System.out.println("map"+XmlUtils.toXml(map));
        map.put("mch_id", SwiftpassConfig.mch_id);
        String key = SwiftpassConfig.key;
        String res = null;
        String reqUrl = SwiftpassConfig.req_url;
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        Map<String,String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        map.put("sign", sign);
        
        System.out.println("reqUrl:" + reqUrl);
        
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if(response != null && response.getEntity() != null){
                Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                res = XmlUtils.toXml(resultMap);
                if ("SUCCESS".equals(resultMap.get("trade_state"))) {
                	 res = "1";
				}
                if(resultMap.containsKey("sign") && !SignUtils.checkParam(resultMap, key)){
                    res = "验证签名不通过";
                }
            }else{
                res = "操作失败!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "操作失败";
        } finally {
            if(response != null){
                response.close();
            }
            if(client != null){
                client.close();
            }
        }
//        if(res.startsWith("<")){
//            resp.setHeader("Content-type", "text/xml;charset=UTF-8");
//        }else{
//            resp.setHeader("Content-type", "text/html;charset=UTF-8");
//        }
        resp.getWriter().write(res);
		return null;
    }

	
//	//退款查询
//	@RequestMapping("/update")
//	public ModelAndView re(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        req.setCharacterEncoding("utf-8");
//        resp.setCharacterEncoding("utf-8");
//        
//        SortedMap<String,String> map = XmlUtils.getParameterMap(req);
//        System.out.println(XmlUtils.toXml(map));
//        String key = SwiftpassConfig.key;
//        String res = null;
//        String reqUrl = SwiftpassConfig.req_url;
//        map.put("mch_id", SwiftpassConfig.mch_id);
//        map.put("nonce_str", String.valueOf(new Date().getTime()));
//        
//        Map<String,String> params = SignUtils.paraFilter(map);
//        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
//        SignUtils.buildPayParams(buf,params,false);
//        String preStr = buf.toString();
//        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
//        map.put("sign", sign);
//        
//        System.out.println("reqUrl:" + reqUrl);
//        
//        CloseableHttpResponse response = null;
//        CloseableHttpClient client = null;
//        try {
//            HttpPost httpPost = new HttpPost(reqUrl);
//            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
//            httpPost.setEntity(entityParams);
//            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
//            client = HttpClients.createDefault();
//            response = client.execute(httpPost);
//            if(response != null && response.getEntity() != null){
//                Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
//                res = XmlUtils.toXml(resultMap);
//                System.out.println("请求结果：" + res);
//                
//                if(resultMap.containsKey("sign") && !SignUtils.checkParam(resultMap, key)){
//                    res = "验证签名不通过";
//                }
//            }else{
//                res = "操作失败!";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            res = "操作失败";
//        } finally {
//            if(response != null){
//                response.close();
//            }
//            if(client != null){
//                client.close();
//            }
//        }
//        if(res.startsWith("<")){
//            resp.setHeader("Content-type", "text/xml;charset=UTF-8");
//        }else{
//            resp.setHeader("Content-type", "text/html;charset=UTF-8");
//        }
//        resp.getWriter().write(res);
//		return null;
//    
//	}
	//退款
	@RequestMapping("/refund")
	public ModelAndView listByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        
        SortedMap<String,String> map = XmlUtils.getParameterMap(req);
        System.out.println(XmlUtils.toXml(map));
        String key = SwiftpassConfig.key;
        String res = null;
        String reqUrl = SwiftpassConfig.req_url;
        map.put("mch_id", SwiftpassConfig.mch_id);
        map.put("op_user_id", SwiftpassConfig.mch_id);
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        
        Map<String,String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        map.put("sign", sign);
        
        System.out.println("reqUrl:" + reqUrl);
        
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if(response != null && response.getEntity() != null){
                Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                res = XmlUtils.toXml(resultMap);
                System.out.println("请求结果：" + res);
                
                if(resultMap.containsKey("sign") && !SignUtils.checkParam(resultMap, key)){
                    res = "验证签名不通过";
                }
            }else{
                res = "操作失败!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "操作失败";
        } finally {
            if(response != null){
                response.close();
            }
            if(client != null){
                client.close();
            }
        }
        if(res.startsWith("<")){
            resp.setHeader("Content-type", "text/xml;charset=UTF-8");
        }else{
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
        }
        resp.getWriter().write(res);
		return null;
    
	}
	

	@ExceptionHandler(Exception.class)
	public String exception(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("exception", e);
		return "/error.jsp";
	}
}
