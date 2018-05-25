<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Login | Parts Number System</title>

    <!-- Bootstrap -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/assets/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="/assets/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="/assets/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="/assets/css/custom.min.css" rel="stylesheet">

    <link href="/assets/css/dev.css" rel="stylesheet">
</head>

<body class="login">
<div>

    <div class="login_wrapper">
        <div class="form login_form">
            <section class="login_content">
                <c:if test="${param.error ne null}">
                    <div id="errorMessage" class="alert alert-error alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        Login failed. Please try again.
                    </div>
                </c:if>
                <c:if test="${param.accessDenied ne null}">
                    <div id="accessMessage" class="alert alert-error alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        Your account have been blocked. Please contact with Administrator.
                    </div>
                </c:if>
                <c:if test="${param.logout ne null}">
                    <div id="logoutMessage" class="alert alert-info alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        You have been logged out.
                    </div>
                </c:if>

                <form id="login" name="login" method="post" action="/login">
                    <h1>Login Form</h1>
                    <div>
                        <input id="username" name="username" type="text" class="form-control" placeholder="Username" required="" />
                    </div>
                    <div>
                        <input id="password" name="password" type="password" class="form-control" placeholder="Password" required="" />
                    </div>
                    <div>
                        <button type="submit" class="btn btn-default submit">Log in</button>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">

                        <div>
                            <h1><i class="fa fa-cogs"></i> Parts Number System</h1>
                            <p>&copy;2017 All Rights Reserved.</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>

    </div>
</div>

<!-- jQuery -->
<script src="/assets/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/assets/js/bootstrap.min.js"></script>
<!-- jQuery Validate -->
<script src="/assets/js/jquery.validate.min.js"></script>

</body>
</html>
