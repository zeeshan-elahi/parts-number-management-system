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

    <title>Help | Parts Number System</title>

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
                        <h3>Help</h3>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Document Type Status</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <ul>
                                    <li><strong>Active:</strong>  Currently used and useable Document type</li>
                                    <li><strong>Inactive:</strong> A document type that was used in the past but it is not useable anymore (deprecated type)</li>
                                    <li><strong>Obsolete:</strong> A document type that is never been used and should not be used (invalid type)</li>
                                </ul>
                            </div>
                        </div>
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Project Status</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <ul>
                                    <li><strong>Active:</strong> Currently active and supported project (i.e. FREDI)</li>
                                    <li><strong>Inactive:</strong> Project for which no further development has been scheduled. It can be purchased still, but no development is being done on it (i.e. EDI, other legacy projects)</li>
                                    <li><strong>Obsolete:</strong> A project for which no development is being done, it is not possible to have clients order this project anymore, but it may still be possible to provide customer support</li>
                                    <li><strong>Closed:</strong> A project for which no support, development or possibility of ordering products is possible. It's just for historical purposes (i.e. PIP)</li>
                                </ul>
                            </div>
                        </div>
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Part Status</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <ul>
                                    <li><strong>Active:</strong> Currently used and supported</li>
                                    <li><strong>Inactive:</strong> Used, but not supported anymore (documents can be produced for customer support - i.e. EDI or other legacy parts)</li>
                                    <li><strong>Obsolete:</strong> Not in use anymore and not supported any longer. Should not be used in any other part assemblies. No customer support available for it. No new documents will be created for its support (i.e. EDI)</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- /page content -->

        <!-- Modal -->
        <div class="modal fade" id="partModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form id="partForm" name="partForm">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="exampleModalLabel">Add/Edit Part</h4>
                        </div>
                        <div class="modal-body">
                            <div id="errorMessage" class="alert alert-error alert-dismissable" style="display:none;">
                                <a href="#" class="close" data-hide="alert" aria-label="close">&times;</a>
                                <span id="errorMessageText"></span>
                            </div>
                            <div id="savedInfoMessage" class="alert alert-success alert-dismissable" style="display:none;">
                                <a href="#" class="close" data-hide="alert" aria-label="close">&times;</a>
                                <span id="savedInfoMessageText"></span>
                            </div>
                            <div class="row">
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                    <label for="projectId" class="control-label">Project Number</label>
                                    <select class="form-control editingNowAllowed onSaveDisabled" id="projectId" name="projectId">
                                        <option value="">Please select</option>
                                        <c:forEach var="project" items="${projectsList}">
                                            <option value="${project.projectId}"><fmt:formatNumber pattern="0000" value="${project.projectNumber}" /> (${project.projectCode})</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                    <label for="documentTypeId" class="control-label">Document Type</label>
                                    <select class="form-control editingNowAllowed onSaveDisabled" id="documentTypeId" name="documentTypeId">
                                        <option value="">Please select</option>
                                        <c:forEach var="documentType" items="${documentTypesList}">
                                            <option value="${documentType.documentTypeId}">${documentType.documentTypeNumber}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="partDescription" class="control-label">Description:</label>
                                <textarea class="form-control editingNowAllowed onSaveDisabled" id="partDescription" name="partDescription"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="comment" class="control-label">Comment:</label>
                                <textarea class="form-control onSaveDisabled" id="comment" name="comment"></textarea>
                            </div>
                            <div class="row">
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                    <label for="partNumberFull" class="control-label">Part Number</label>
                                    <input type="text" class="form-control editingNowAllowed onSaveDisabled" id="partNumberFull" name="partNumberFull" readonly="readonly">
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                    <label for="partStatusId" class="control-label">Status</label>
                                    <select id="partStatusId" name="partStatusId" class="form-control onSaveDisabled">
                                        <c:forEach var="partStatus" items="${partStatusList}">
                                            <option value="${partStatus.partStatusId}">${partStatus.partStatusName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="submitBtn" type="submit" class="btn btn-primary">Save</button>
                            <button id="cancelBtn" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button id="okBtn" type="button" class="btn btn-success" style="display: none;">Ok</button>
                            <input type="hidden" id="partId" name="partId" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- /Modal -->

        <!-- footer content -->
        <jsp:include page="../../layout/footer.jsp" />
        <!-- /footer content -->
    </div>
</div>

<jsp:include page="../../includes/js.jsp" />


</body>
</html>