<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Parts Change Log | Parts Number System</title>

    <jsp:include page="../../includes/css.jsp" />

</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <!-- Left Menu -->
        <jsp:include page="../../layout/left_menu.jsp" />
        <!-- /Left Menu -->

        <!-- top navigation -->
        <jsp:include page="../../layout/top_nav.jsp" />
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Change Log</h3>
                    </div>

                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">

                            <div class="x_content">

                                <table id="changeLogTable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Part Number</th>
                                        <th>Data Before Change</th>
                                        <th>Data After Change</th>
                                        <th>Changed By</th>
                                        <th>Date Logged</th>
                                    </tr>
                                    </thead>


                                    <tbody>
                                    <c:choose>
                                        <c:when test="${fn:length(partsChangeLogList) gt 0}">
                                            <c:forEach var="partsChangeLog" items="${partsChangeLogList}">
                                                <tr>
                                                    <td>${partsChangeLog.partId.partNumberFull}</td>
                                                    <td><strong>Description:</strong> ${partsChangeLog.oldPartDescription}<br /><strong>Comment:</strong> ${partsChangeLog.oldComment}<br /><strong>Status:</strong> ${partsChangeLog.oldPartStatusId.partStatusName}</td>
                                                    <td><strong>Description:</strong> ${partsChangeLog.partDescription}<br /><strong>Comment:</strong> ${partsChangeLog.comment}<br /><strong>Status:</strong> ${partsChangeLog.partStatusId.partStatusName}</td>
                                                    <td>${partsChangeLog.updatedByUserId.userName}</td>
                                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${partsChangeLog.dateUpdated}" /></td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr id="notFoundRow">
                                                <td colspan="5" style="text-align: center;">No record found.</td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <jsp:include page="../../layout/footer.jsp" />
        <!-- /footer content -->
    </div>
</div>

<jsp:include page="../../includes/js.jsp" />

<script type="text/javascript">
    jQuery(document).ready(function(){

        <c:choose>
        <c:when test="${fn:length(partsChangeLogList) gt 0}">
        jQuery('#changeLogTable').dataTable({
            paging: false,
            order: [[ 4, "desc" ]]
        });
        </c:when>
        </c:choose>

    });
</script>

</body>
</html>