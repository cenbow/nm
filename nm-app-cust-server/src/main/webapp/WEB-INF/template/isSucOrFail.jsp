<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2016/7/8
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8"/>
    <meta http-equiv="prama" content="no-cache"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="full-screen" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <style type="text/css">
        body {
            margin: 0px;
            border: 0px;
            padding: 0px;
        }
        div {width:100%; height:100%; background-color:#eee;
        text-align: center;
            position: absolute;
        }

        img {display:block;
            margin:0 auto;
            margin-top: 50%;}
    </style>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/accounting.js/0.3.2/accounting.min.js"></script>
    <script type="text/javascript">
        var type=${type}
        $(function(){
            if(type==0){
                $(".imga").attr("src","/img/img_success.png");
                $(".msg").text("签约成功！");
                $(".msg").css("color","green");
            }else {
                $(".imga").attr("src","/img/img_fail.png");
                $(".msg").text("签约失败！");
                $(".msg").css("color","red");
            }
        })

    </script>
</head>
<body style="width:100%;height:100%;font-family: 宋体;font-size: 35px;">
    <div id="ll">
        <img class="imga" src="" /><br/>
        <span>&nbsp;</span><span class="msg" style="font-weight:bold;"></span>
    </div>
</body>
</html>
