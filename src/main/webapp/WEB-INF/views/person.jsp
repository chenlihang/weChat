<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
	<link rel="stylesheet" href="/css/weui.css">
	<link rel="stylesheet" href="/css/jquery-weui.css">
	<link rel="stylesheet" href="/css/style.css">
	<style>
	html,body{ background:#f4f4f4; }
	.weui-cells{ font-size: 15px; }
	.head{
	text-align: center;
	height: 45px;
	line-height: 45px;
	box-shadow: 0 1px 1px 1px #e4e4e4;
	background:#fff;
	position: fixed;
	left: 0;
	top: 0;
	z-index: 100;
	width: 100%;
}
.head .return{
	position: absolute;
	left: 0;
	top: 0;
	width: 45px;
	height: 45px;
}
.head .return:after{
	content: " ";
    display: inline-block;
    height: 13px;
    width: 13px;
    border-width: 2px 2px 0 0;
    border-color: #C8C8CD;
    border-style: solid;
    -webkit-transform: matrix(0.71, 0.71, -0.71, 0.71, 0, 0);
    transform: matrix(0.71, 0.71, -0.71, 0.71, 0, 0);
    position: absolute;
    transform:rotate(225deg);
    left: 16px;
    top: 14px;
}
.head .operation{
	position: absolute;
	right: 0;
	top: 0;
	width: 60px;
	height: 45px;
	color:#00d26d;
}

.personal .tx{
	width: 60px;
	height: 60px;
	border-radius: 50%;
}
.personal .weui-input{
	color: #999;
}
	</style>

</head>
<body>

   <div class="head"><a href="index.html" class="return"></a>个人信息 <a href="编辑个人信息.html" class="operation">编辑</a></div>

   <div class="personal f15" style="padding-top: 50px;">
          	<div class="weui-cells m0">
			  <div class="weui-cell">
			    <div class="weui-cell__bd">
			      头像
			    </div>
			    <div class=""><img class="tx" src="${headimgurl}" alt=""> </div>

			  </div>
			</div>

			<div class="weui-cells mt5">
			  <div class="weui-cell ">
			    <div class="weui-cell__bd">
			      昵称
			    </div>
			    <div class="weui-cell__price">${nickname}</div>
			  </div>
			  <div class="weui-cell">
			    <div class="weui-cell__bd">
			      国家
			    </div>
			    <div class="weui-cell__price">${country}</div>
			  </div>
			  <div class="weui-cell">
			    <div class="weui-cell__bd">
			      所在城市
			    </div>
			    <div class="">${province} ${city}</div>
			  </div>
			  <div class="weui-cell weui-cell_access">
			    <div class="weui-cell__bd">
			      姓别
			    </div>
			    <div class="weui-cell__price">
					<c:choose>
						<c:when test="${sex==2}">
							女
						</c:when>
						<c:when test="${sex==1}">
							男
						</c:when>
					</c:choose>
				</div>
			  </div>
			 
			  <div class="weui-cell">
			    <div class="weui-cell__bd">
			      手机
			    </div>
			    <div class="weui-cell__price"></div>
			  </div>
			  <div class="weui-cell">
			    <div class="weui-cell__bd">
			      QQ
			    </div>
			    <div class="weui-cell__price"></div>
			  </div>
			  <div class="weui-cell">
			    <div class="weui-cell__bd">
			      邮箱
			    </div>
			    <div class="weui-cell__price"></div>
			  </div>
			</div>

          </div>
    
    
   
    

	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery-weui.min.js"></script>
</body>
</html>