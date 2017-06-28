<%--
  User: Mifeng.He(bee)
  Date: 2015/12/28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎页</title>
    <style type="text/css">
        .app-homepage-base {}
        .app-homepage-base img {position: absolute; top: 0; left: 0; width: 100%; height: 100%;}
    </style>
    <script type="text/javascript">
        $(function () { app.init(); });

        app.init('app.homepage.base', {
            vars: {
                index: 0
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};
                this.layout();
            },
            layout: function () {
                var that = this.global();

                var srcs = [
                    'static/js/homepage/images/welcome.1.png',
                    'static/js/homepage/images/welcome.2.png'
                ];

                var $imgs = [], $img;
                $.each(srcs, function (index, src) {
                    $img = $(appui.fmt('<img src="{0}" style="{1}"/>', [src, index == 0 ? '': 'display: none;'])).appendTo(that.selector);
                    $imgs.push($img);
                });

                var length = src.length, index = 0;
                setInterval(function () {
                    $imgs[index % length].fadeOut('slow');
                    $imgs[++index % length].fadeIn('slow');
                }, 1000 * 60);

            }
        });
    </script>
</head>
<body>
   <div class="app-homepage-base"></div>
</body>
</html>
