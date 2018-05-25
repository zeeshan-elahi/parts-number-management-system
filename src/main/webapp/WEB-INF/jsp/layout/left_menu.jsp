<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="/part/list" class="site_title"><i class="fa fa-cogs"></i> <span>Parts Number System</span></a>
        </div>

        <div class="clearfix"></div>

        <br />

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <ul class="nav side-menu">
                    <li><a><i class="fa fa-cog"></i> Manage Parts <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="/part/list">Parts</a></li>
                            <c:if test="${loggedInUser.getUserTypeId().getUserTypeId() eq 1}">
                            <li><a href="/project/list">Projects</a></li>
                            <li><a href="/documenttype/list">Document Types</a></li>
                            </c:if>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-bar-chart"></i> Reports <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <c:if test="${loggedInUser.getUserTypeId().getUserTypeId() eq 1}">
                            <li><a href="/report/parts/changelog">Change Log</a></li>
                            </c:if>
                            <li><a href="/report/parts/customreport">Custom Report</a></li>
                        </ul>
                    </li>
                    <c:if test="${loggedInUser.getUserTypeId().getUserTypeId() eq 1}">
                    <li><a><i class="fa fa-users"></i> Manage <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="/manage/users">Users</a></li>
                        </ul>
                    </li>
                    </c:if>
                </ul>
            </div>

        </div>
        <!-- /sidebar menu -->

    </div>
</div>