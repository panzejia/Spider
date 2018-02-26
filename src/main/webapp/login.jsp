<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>课题部落--后台管理系统</title>
  <link rel="stylesheet" href="css/admin/login.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
  <section class="container" style="text-align: center;">
	<img src="images/logo/logo_onlyWord.png" width="270" height="129">

    <div class="login">
      <h1>课题部落--后台管理系统</h1>
      <form name="regForm" action="user/login" method="post">
        <p><input type="text" name="username" value="" id="username" placeholder="Username or Email"></p>
        <p><input type="password" name="password" id="password" value="" placeholder="Password"></p>
        <p class="submit"><input type="submit" name="commit" value="登录"></p>
      </form>
    </div>

    <div class="login-help">
      <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p>
    </div>
  </section>

  <section class="about">
    <p class="about-links">
      <a href="https://www.iktbl.com" target="_parent">课题部落</a>
    </p>
    <p class="about-author">
      &copy; 2017&ndash;2018 <a href="http://thibaut.me" target="_blank">iktbl.com</a>
  </section>
</body>
</html>
