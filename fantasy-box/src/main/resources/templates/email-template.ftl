<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>fantasy-box</title>
</head>
<body>
    你好，感谢你的注册，这是一封验证邮件，请点击下面的连接完成注册，感谢您的支持。
    <a href="#" th:href="@{https://github.com/${id}}">激活账户</a>
</body>
</html>