<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>fantasy-box</title>
</head>
<body>
<p style='margin-left:100px'></p>
        <div style='color:#ff5a5f;font-size:24px'>fantasy-box</div>
        <p>您好!</p>
        <p>欢迎加入fantasy-box！在开始使用fantasy-box之前，您必须先确认您的电子邮件地址。</p>
        <br>
        <a href='${url}?token=${token}&email=${email}' style='text-decoration: none'>
        <label style='background-color:#ff5a5f;color:white;cursor:pointer;padding: 6px 40px 6px 40px;'>激活账号</label>
        </a>
        <br>
        <br>
</p>



</body>
</html>