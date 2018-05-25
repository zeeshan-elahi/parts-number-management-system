<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="userNameTokens" value="${fn:split(loggedInUser.getUserName(), ' ')}" />
<div class="top_nav">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        Welcome <strong><c:out value="${userNameTokens[0]}" /></strong>&nbsp;&nbsp;<span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                    </ul>
                </li>

                <li role="presentation" style="margin-top: 5px;">
                    <a href="/page/help" class="dropdown-toggle info-number" title="Help">
                        <i class="fa fa-question-circle" style="font-size: 18px;"></i>
                    </a>
                </li>

            </ul>
        </nav>
    </div>
</div>