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

    <title>Manage Parts | Parts Number System</title>

    <jsp:include page="../includes/css.jsp" />

</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <!-- Left Menu -->
        <jsp:include page="../layout/left_menu.jsp" />
        <!-- /Left Menu -->

        <!-- top navigation -->
        <jsp:include page="../layout/top_nav.jsp" />
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Manage Parts</h3>
                    </div>

                    <div class="title_right">
                        <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#partModal" data-whatever="0">Add New Part</button>
                    </div>
                </div>

                <div class="clearfix"></div>
                <c:choose>
                    <c:when test="${showMessage == 'success' }">
                        <div id="successMessage" class="alert alert-success alert-dismissable">
                            <a href="#" class="close" data-hide="alert" aria-label="close">&times;</a>
                            Record has been saved successfully.
                        </div>
                    </c:when>
                </c:choose>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">

                            <div class="x_content">

                                <table id="partsTable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th style="white-space:nowrap;">Part Number</th>
                                        <th>Description</th>
                                        <th>Comments</th>
                                        <th>Status</th>
                                        <th style="white-space:nowrap;">Date Added</th>
                                        <th style="white-space:nowrap;">Added By</th>
                                        <th style="white-space:nowrap;">Modified By</th>
                                        <th></th>
                                    </tr>
                                    </thead>


                                    <tbody>
                                    <c:choose>
                                        <c:when test="${fn:length(partsList) gt 0}">
                                            <c:forEach var="part" items="${partsList}">
                                                <tr>
                                                    <td>${part.partNumberFull}</td>
                                                    <td>${part.partDescription}</td>
                                                    <td>${part.comment}</td>
                                                    <td>${part.partStatusId.partStatusName}</td>
                                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${part.dateCreated}" /></td>
                                                    <td>${part.createdByUserId.userName}</td>
                                                    <td>${part.updatedByUserId.userName}</td>
                                                    <td><a href="#" data-toggle="modal" data-target="#partModal" data-whatever="${part.partId}"><i class="fa fa-pencil-square datatable_action"></i></a></td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr id="notFoundRow">
                                                <td colspan="8" style="text-align: center;">No record found.</td>
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
                                <textarea class="form-control onSaveDisabled" id="partDescription" name="partDescription"></textarea>
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
        <jsp:include page="../layout/footer.jsp" />
        <!-- /footer content -->
    </div>
</div>

<jsp:include page="../includes/js.jsp" />

<script type="text/javascript">
    jQuery(document).ready(function(){

        $('#partModal').on('show.bs.modal', function (event) {

            var button = $(event.relatedTarget) // Button that triggered the modal
            var itemId = button.data('whatever') // Extract info from data-* attributes
            console.log("Item ID: " + itemId);
            $('#partId').val(itemId);

            //Enable, hide and show fields that have been modified on last save
            $('.onSaveDisabled').attr("disabled", false);
            $('#okBtn').hide();
            $('#submitBtn').show();
            $('#cancelBtn').show();
            $('#errorMessage').hide();
            $('#savedInfoMessage').hide();

            //Reset form and validation messages
            jQuery("#partForm")[0].reset();
            var validator = jQuery( "#partForm" ).validate();
            validator.resetForm();
            $(".error").removeClass("error");

            //Disable fields
            $(".editingNowAllowed").attr("disabled", false);
            if(itemId !== 'undefined' && itemId !== 0){
                $(".editingNowAllowed").attr("disabled", true);
            }

            $.ajax({
                dataType: "json",
                url: "/part/load/" + itemId,
                success: function( response, status, xhr ) {

                    if(response.projectId.projectId > 0){
                        console.log(response.projectId.projectId);
                        $('#projectId').val(response.projectId.projectId);
                    }

                    if(response.documentTypeId.documentTypeId > 0){
                        console.log(response.documentTypeId.documentTypeId);
                        $('#documentTypeId').val(response.documentTypeId.documentTypeId);
                    }

                    $('#partDescription').val(response.partDescription);
                    $('#comment').val(response.comment);
                    $('#partNumberFull').val(response.partNumberFull);
                    $('#partStatusId').val(response.partStatusId.partStatusId);

                    var modal = $(this);
                }
            });
        });

        <c:choose>
        <c:when test="${fn:length(partsList) gt 0}">
        jQuery('#partsTable').dataTable({
            "pageLength": 25,
            "order": [[ 0, "asc" ]],
            columnDefs: [
                { orderable: false, targets: -1 }
            ]
        });
        </c:when>
        </c:choose>

        jQuery("[data-hide]").on("click", function(){
            jQuery(this).closest("." + jQuery(this).attr("data-hide")).hide();
        });

        jQuery("#okBtn").on("click", function(){
            window.location = '/part/list';
        });

        jQuery.validator.addMethod("dropdownRequired", function(value, element, param) {
            return (value === 'undefined' || value === '')? false : true;
        });

        jQuery('#partForm').validate({
            rules:{
                projectId: {
                    required: true
                },
                documentTypeId: {
                    required:  true
                },
                partDescription: {
                    maxlength: 200
                },
                comment: {
                    maxlength: 200
                },
                partStatusId: {
                    required: true
                }
            },
            messages: {
                projectId: {
                    required: 'Please select a value.'
                },
                documentTypeId: {
                    required: 'Please select a value.'
                },
                partDescription: {
                    maxlength: '200 characters only.'
                },
                comment: {
                    maxlength: '200 characters only.'
                },
                partStatusId: {
                    required: 'Please select a value'
                }
            },
            submitHandler: function(form) {
                jQuery.ajax({
                    dataType: "json",
                    url: '/part/save',
                    type: 'POST',
                    data: jQuery(form).serialize(),
                    success: function(response) {
                        if(response.result === 'Success'){
                            //window.location = '/part/list/?msg=success';

                            //Disable fields
                            $('.onSaveDisabled').attr("disabled", true);
                            $('#submitBtn').hide();
                            $('#cancelBtn').hide();
                            $('#okBtn').show();

                            $('#partNumberFull').val(response.partNumberFull);

                            //Show Success Message
                            $('#errorMessage').hide();
                            $('#savedInfoMessageText').html(response.message);
                            $('#savedInfoMessage').show();

                        }else if(response.result === 'Failure'){
                            $('#savedInfoMessage').hide();
                            $('#errorMessageText').html(response.message);
                            $('#errorMessage').show();
                        }
                    }
                });
            }
        });

    });
</script>

</body>
</html>