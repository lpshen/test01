package com.bms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bms.common.PaymentUtil;
//该Servlet处理支付请求并进行重定向
@Controller
@RequestMapping("/yeepay")
public class YeepayController extends HttpServlet {
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			this.doPost(request, response);
		}
		@RequestMapping("/pay")
		public void pay(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String 	p0_Cmd="Buy",
					p1_MerId="10001126856",
					p2_Order=request.getParameter("p2_Order"),
					p3_Amt=request.getParameter("p3_Amt"),
					p4_Cur="CNY",
					p5_Pid="",
					p6_Pcat="",
					p7_Pdesc="",
					p8_Url="http://localhost:8080/T_ms/yeepay/back.do",
					p9_SAF="",
					pa_MP="",
					pd_FrpId=request.getParameter("pd_FrpId"),
					pr_NeedResponse="1";
			String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
			String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
			String url="https://www.yeepay.com/app-merchant-proxy/node?"+
			"&p0_Cmd="+p0_Cmd+
			"&p1_MerId="+p1_MerId+
			"&p2_Order="+p2_Order+
			"&p3_Amt="+p3_Amt+
			"&p4_Cur="+p4_Cur+
			"&p5_Pid="+p5_Pid+
			"&p6_Pcat="+p6_Pcat+
			"&p7_Pdesc="+p7_Pdesc+
			"&p8_Url="+p8_Url+
			"&p9_SAF="+p9_SAF+
			"&pa_MP="+pa_MP+
			"&pd_FrpId="+pd_FrpId+
			"&pr_NeedResponse="+pr_NeedResponse+
			"&hmac="+hmac;
			response.sendRedirect(url);
		}
		@RequestMapping("/back")
		public void back(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String r1_Code=request.getParameter("r1_Code");
			PrintWriter pw=response.getWriter();
			if("1".equals(r1_Code))
			{
				String p1_MerId=request.getParameter("p1_MerId");
				String r3_Amt=request.getParameter("r3_Amt");
				String r6_Order=request.getParameter("r6_Order");
				String rp_PayDate=request.getParameter("rp_PayDate");
				pw.println("支付成功！<br/>"
						+ "商户编号："+p1_MerId+"<br/>"
						+ "支付金额："+r3_Amt+"<br/>"
						+"商户订单号："+r6_Order+"<br/>"
						+"支付成功时间："+rp_PayDate);
			}
			else
			{
				pw.println("支付失败！");
			}
		}
	}

