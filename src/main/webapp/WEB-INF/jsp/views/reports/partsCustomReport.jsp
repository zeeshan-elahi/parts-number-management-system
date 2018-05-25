<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <link href="/assets/css/bootstrap-multiselect.css" rel="stylesheet">

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
                        <h3>Custom Report</h3>
                    </div>

                </div>

                <div class="clearfix"></div>

                <div class="row">

                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Search criteria</h2>
                                <ul class="nav navbar-right">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <form:form id="customReportSearchForm" name="customReportSearchForm" data-parsley-validate="" class="form-horizontal form-label-left" action="/report/parts/customreport" method="post" commandName="customReport" novalidate="">
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="selectedColumns" class="control-label">Display Columns</label>
                                        <div>
                                            <form:select path="selectedColumns" multiple="multiple"  data-error="#selectedColumns-error">
                                                <form:options items="${selectColumnsMap}"></form:options>
                                            </form:select>
                                        </div>
                                        <label id="selectedColumns-error" class="error" for="selectedColumns" style="margin-top: 7px;"></label>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group" style="padding-right: 0px !important;">
                                        <label for="keyword" class="control-label">Keyword</label>
                                        <div>
                                            <form:input path="keyword" type="text" class="form-control" style="float: left; width:90%;"></form:input>
                                            <div style="float: left; margin-left: 12px;">
                                                <i class="fa fa-long-arrow-right" style="font-size: 18px; margin-top:9px;"></i>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="keywordSearchTokens" class="control-label">Search Keyword In</label>
                                        <div>
                                            <form:select path="keywordSearchTokens" multiple="multiple">
                                                <form:options items="${keywordSearchTokensMap}"></form:options>
                                            </form:select>
                                        </div>
                                        <label id="keywordSearchTokens-error" class="error" for="keywordSearchTokens" style="margin-top: 7px;"></label>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="partNumber" class="control-label">Part Number</label>
                                        <form:input path="partNumber" type="text" class="form-control"></form:input>
                                    </div>
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="partStatus" class="control-label">Part Status</label>
                                        <form:select path="partStatus" name="partStatus[]" nmultiple="multiple">
                                            <form:options items="${partStatusMap}"></form:options>
                                        </form:select>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="projectNumber" class="control-label">Project Number</label>
                                        <form:select path="projectNumber" name="projectNumber[]" multiple="multiple">
                                            <form:options items="${projectsMap}"></form:options>
                                        </form:select>
                                    </div>
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="projectStatus" class="control-label">Project Status</label>
                                        <form:select path="projectStatus" name="projectStatus[]" multiple="multiple">
                                            <form:options items="${projectStatusesMap}"></form:options>
                                        </form:select>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="documentTypeNumber" class="control-label">Document Type</label>
                                        <form:select path="documentTypeNumber" name="documentTypeNumber[]" multiple="multiple">
                                            <form:options items="${documentTypesMap}"></form:options>
                                        </form:select>
                                    </div>
                                    <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                        <label for="documentTypeStatus" class="control-label">Document Type Status</label>
                                        <form:select path="documentTypeStatus" name="documentTypeStatus[]" multiple="multiple">
                                            <form:options items="${documentTypeStatusesMap}"></form:options>
                                        </form:select>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="ln_solid"></div>
                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                        <button type="submit" class="btn btn-primary">Update</button>
                                        <button type="reset" class="btn btn-cancel">Reset</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="clearfix"></div>

                <c:if test="${hideDataGrid eq false}">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">

                            <div class="x_content" style="overflow-x: auto;">

                                <table id="partsCutomReport" class="table table-striped table-bordered" style="width:100%;" width="100%">
                                    <thead>
                                    <tr>
                                    <c:choose>
                                        <c:when test="${fn:length(selectedColumns) gt 0}">
                                            <c:forEach var="column" items="${selectedColumns}">

                                                <c:choose>
                                                    <c:when test="${column eq 'partNumber'}">
                                                        <th style="white-space: nowrap">Part Number</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'partDescription'}">
                                                        <th style="white-space: nowrap;">Part Description</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'partComment'}">
                                                        <th style="white-space: nowrap;">Part Comment</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'partStatus'}">
                                                        <th>Part Status</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'projectNumber'}">
                                                        <th>Project Number</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'projectCode'}">
                                                        <th>Project Code</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'projectDescription'}">
                                                        <th style="white-space: nowrap;">Project Description</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'projectComment'}">
                                                        <th style="white-space: nowrap;">Project Comment</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'projectStatus'}">
                                                        <th>Project Status</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'documentTypeNumber'}">
                                                        <th>Document Type</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'documentTypeDescription'}">
                                                        <th style="white-space: nowrap;">Document Type Description</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'documentTypeComment'}">
                                                        <th style="white-space: nowrap;">Document Type Comment</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'documentTypeStatus'}">
                                                        <th>Document Type Status</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'createdBy'}">
                                                        <th style="white-space: nowrap;">Created By</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'dateCreated'}">
                                                        <th style="white-space: nowrap;">Date Created</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'updatedBy'}">
                                                        <th style="white-space: nowrap;">Updated By</th>
                                                    </c:when>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${column eq 'dateUpdated'}">
                                                        <th style="white-space: nowrap;">Date Updated</th>
                                                    </c:when>
                                                </c:choose>

                                            </c:forEach>
                                        </c:when>
                                        <%--c:otherwise>
                                            <th>Part Number</th>
                                        </c:otherwise--%>
                                    </c:choose>
                                    </tr>
                                    </thead>


                                    <tbody>
                                    <c:choose>
                                        <c:when test="${fn:length(partsList) gt 0}">
                                            <c:forEach var="part" items="${partsList}">

                                                <c:choose>
                                                    <c:when test="${fn:length(selectedColumns) gt 0}">
                                                        <tr>
                                                            <c:forEach var="column" items="${selectedColumns}">

                                                                <c:choose>
                                                                    <c:when test="${column eq 'partNumber'}">
                                                                        <td style="white-space: nowrap">${part.partNumberFull}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'partDescription'}">
                                                                        <td>${part.partDescription}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'partComment'}">
                                                                        <td>${part.comment}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'partStatus'}">
                                                                        <td>${part.partStatusId.partStatusName}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'projectNumber'}">
                                                                        <td><fmt:formatNumber pattern="0000" value="${part.projectId.projectNumber}" /></td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'projectCode'}">
                                                                        <td>${part.projectId.projectCode}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'projectDescription'}">
                                                                        <td>${part.projectId.projectDescription}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'projectComment'}">
                                                                        <td>${part.projectId.comment}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'projectStatus'}">
                                                                        <td>${part.projectId.projectStatusId.projectStatusName}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'documentTypeNumber'}">
                                                                        <td>${part.documentTypeId.documentTypeNumber}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'documentTypeDescription'}">
                                                                        <td>${part.documentTypeId.documentTypeDescription}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'documentTypeComment'}">
                                                                        <td>${part.documentTypeId.comment}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'documentTypeStatus'}">
                                                                        <td>${part.documentTypeId.typeStatusId.typeStatusName}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'createdBy'}">
                                                                        <td>${part.createdByUserId.userName}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'dateCreated'}">
                                                                        <td><fmt:formatDate pattern="MM/dd/yyyy" value="${part.dateCreated}" /></td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'updatedBy'}">
                                                                        <td>${part.updatedByUserId.userName}</td>
                                                                    </c:when>
                                                                </c:choose>

                                                                <c:choose>
                                                                    <c:when test="${column eq 'dateUpdated'}">
                                                                        <td><fmt:formatDate pattern="MM/dd/yyyy" value="${part.dateUpdated}" /></td>
                                                                    </c:when>
                                                                </c:choose>

                                                            </c:forEach>
                                                        </tr>
                                                    </c:when>
                                                    <%--c:otherwise>
                                                        <tr>
                                                            <td>${part.partNumberFull}</td>
                                                        </tr>
                                                    </c:otherwise--%>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr id="notFoundRow">
                                                <td colspan="${fn:length(selectedColumns)}" style="text-align: center;">No record found.</td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                </c:if>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <jsp:include page="../../layout/footer.jsp" />
        <!-- /footer content -->
    </div>
</div>

<jsp:include page="../../includes/js.jsp" />

<script src="/assets/js/bootstrap-multiselect.js"></script>

<script type="text/javascript">
    jQuery(document).ready(function(){

        $('#selectedColumns').multiselect({
            buttonWidth: '100%',
            includeSelectAllOption: true,
            selectAllValue: 'select-all-value'
        });
        $('#keywordSearchTokens').multiselect({
            buttonWidth: '100%',
            includeSelectAllOption: true,
            selectAllValue: 'select-all-value'
        });
        $('#partStatus').multiselect({
            buttonWidth: '100%',
            includeSelectAllOption: true,
            selectAllValue: 'select-all-value'
        });
        $('#projectNumber').multiselect({
            buttonWidth: '100%',
            includeSelectAllOption: true,
            selectAllValue: 'select-all-value'
        });
        $('#projectStatus').multiselect({
            buttonWidth: '100%',
            includeSelectAllOption: true,
            selectAllValue: 'select-all-value'
        });
        $('#documentTypeNumber').multiselect({
            buttonWidth: '100%',
            includeSelectAllOption: true,
            selectAllValue: 'select-all-value'
        });
        $('#documentTypeStatus').multiselect({
            buttonWidth: '100%',
            includeSelectAllOption: true,
            selectAllValue: 'select-all-value'
        });

        jQuery("#customReportSearchForm").on('reset', function() {

            $('#selectedColumns option:selected').each(function() {
                $(this).prop('selected', false);
            });
            $('#selectedColumns').multiselect('refresh');

            $('#keywordSearchTokens option:selected').each(function() {
                $(this).prop('selected', false);
            });
            $('#keywordSearchTokens').multiselect('refresh');

            $('#partStatus option:selected').each(function() {
                $(this).prop('selected', false);
            });
            $('#partStatus').multiselect('refresh');

            $('#projectNumber option:selected').each(function() {
                $(this).prop('selected', false);
            });
            $('#projectNumber').multiselect('refresh');

            $('#projectStatus option:selected').each(function() {
                $(this).prop('selected', false);
            });
            $('#projectStatus').multiselect('refresh');

            $('#documentTypeNumber option:selected').each(function() {
                $(this).prop('selected', false);
            });
            $('#documentTypeNumber').multiselect('refresh');

            $('#documentTypeStatus option:selected').each(function() {
                $(this).prop('selected', false);
            });
            $('#documentTypeStatus').multiselect('refresh');

        });

        jQuery("#customReportSearchForm").validate({
            ignore: ':hidden:not(".multiselect")',
            rules:{
                selectedColumns: {
                    required: true
                },
                keywordSearchTokens: {
                    required: function(element) {
                        return $("#keyword").val() != '';
                    }
                }
            },
            messages: {
                selectedColumns: {
                    required: 'Please select at least one column to continue.'
                },
                keywordSearchTokens: {
                    required: 'Please select at least one option to continue.'
                }
            }
        });

        <c:choose>
        <c:when test="${fn:length(partsList) gt 0}">
        jQuery('#partsCutomReport').dataTable({
            paging: false,
            searching: false,
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'pdf',
                    orientation: 'landscape',
                    pageSize: 'TABLOID',
                    title: 'Parts Number System Report'
                },
                {
                    extend: 'excel',
                    title: 'Parts Number System Report'
                },
                {
                    extend: 'csv',
                    title: 'Parts Number System Report'
                },
                {
                    extend: 'print',
                    title: 'Parts Number System Report'
                }
            ]
        });
        </c:when>
        </c:choose>

    });
</script>

</body>
</html>