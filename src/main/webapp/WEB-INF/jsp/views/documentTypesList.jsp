<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Manage Document Types | Parts Number System</title>

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
                        <h3>Manage Document Types</h3>
                    </div>

                    <div class="title_right">
                        <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#documentModal" data-whatever="0">Add New Document Type</button>
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

                                <table id="documentsTable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Document Type</th>
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
                                        <c:when test="${fn:length(documentTypesList) gt 0}">
                                            <c:forEach var="documentType" items="${documentTypesList}">
                                                <tr>
                                                    <td>${documentType.documentTypeNumber}</td>
                                                    <td>${documentType.documentTypeDescription}</td>
                                                    <td>${documentType.comment}</td>
                                                    <td>${documentType.typeStatusId.typeStatusName}</td>
                                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${documentType.dateCreated}" /></td>
                                                    <td>${documentType.createdByUserId.userName}</td>
                                                    <td>${documentType.updatedByUserId.userName}</td>
                                                    <td><a href="#" data-toggle="modal" data-target="#documentModal" data-whatever="${documentType.documentTypeId}"><i class="fa fa-pencil-square datatable_action"></i></a></td>
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
        <div class="modal fade" id="documentModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form id="documentForm" name="documentForm">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="exampleModalLabel">Add/Edit Document Type</h4>
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
                            <div class="form-group">
                                <label for="documentTypeNumber" class="control-label">Document Type</label>
                                <input type="text" class="form-control uppercase alpha-only editingNowAllowed onSaveDisabled" id="documentTypeNumber" name="documentTypeNumber" />
                            </div>
                            <div class="form-group">
                                <label for="documentTypeDescription" class="control-label">Description</label>
                                <textarea class="form-control onSaveDisabled" id="documentTypeDescription" name="documentTypeDescription"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="comment" class="control-label">Comment</label>
                                <textarea class="form-control onSaveDisabled" id="comment" name="comment"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="typeStatusId" class="control-label">Status</label>
                                <select id="typeStatusId" name="typeStatusId" class="form-control onSaveDisabled">
                                    <c:forEach var="documentTypeStatus" items="${documentTypeStatusList}">
                                    <option value="${documentTypeStatus.typeStatusId}">${documentTypeStatus.typeStatusName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button id="submitBtn" type="submit" class="btn btn-primary">Save</button>
                            <button id="cancelBtn" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button id="okBtn" type="button" class="btn btn-success" style="display: none;">Ok</button>
                            <input type="hidden" id="documentTypeId" name="documentTypeId" />
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

        $('#documentModal').on('show.bs.modal', function (event) {

            $('#successMessage').hide();

            var button = $(event.relatedTarget) // Button that triggered the modal
            var itemId = button.data('whatever') // Extract info from data-* attributes
            console.log("Item ID: " + itemId);
            $('#documentTypeId').val(itemId);

            //Enable, hide and show fields that have been modified on last save
            $('.onSaveDisabled').attr("disabled", false);
            $('#okBtn').hide();
            $('#submitBtn').show();
            $('#cancelBtn').show();
            $('#errorMessage').hide();
            $('#savedInfoMessage').hide();

            //Reset form and validation messages
            jQuery("#documentForm")[0].reset();
            var validator = jQuery( "#documentForm" ).validate();
            validator.resetForm();
            $(".error").removeClass("error");

            $(".editingNowAllowed").attr("disabled", false);
            if(itemId !== 'undefined' && itemId !== 0){
                $(".editingNowAllowed").attr("disabled", true);
            }

            $.ajax({
                dataType: "json",
                url: "/documenttype/load/" + itemId,
                success: function( response, status, xhr ) {

                    $('#documentTypeNumber').val(response.documentTypeNumber);
                    $('#documentTypeDescription').val(response.documentTypeDescription);
                    $('#comment').val(response.comment);
                    $('#typeStatusId').val(response.typeStatusId.typeStatusId);

                    var modal = $(this);
                }
            });

        });

        <c:choose>
        <c:when test="${fn:length(documentTypesList) gt 0}">
        jQuery('#documentsTable').dataTable({
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
            window.location = '/documenttype/list';
        });

        jQuery.validator.addMethod("regexOnly", function(value, element, param) {
            return value.match(new RegExp("" + param + "$"));
        });

        jQuery('#documentForm').validate({
            rules:{
                documentTypeNumber: {
                    required: true,
                    regexOnly: '[a-zA-Z]+',
                    maxlength: 4,
                    minlength: 1,
                    remote: '/documenttype/isavailable/'
                },
                documentTypeDescription: {
                    maxlength: 200
                },
                comment: {
                    maxlength: 200
                },
                typeStatusId: {
                    required: true
                }
            },
            messages: {
                documentTypeNumber: {
                    required: 'Please enter Document Type.',
                    regexOnly: 'Please enter alphabets only.',
                    maxlength: "Document Type can't be more than 4 characters.",
                    minlength: 'Please enter at least one character.',
                    remote: 'Document Type entered already exist.'
                },
                documentTypeDescription: {
                    maxlength: 'Please enter only 200 characters.'
                },
                comment: {
                    maxlength: 'Please enter only 200 characters.'
                },
                typeStatusId: {
                    required: 'Please select a value'
                }
            },
            submitHandler: function(form) {
                jQuery.ajax({
                    dataType: "json",
                    url: '/documenttype/save',
                    type: 'POST',
                    data: jQuery(form).serialize(),
                    success: function(response) {
                        if(response.result === 'Success'){
                            //window.location = '/documenttype/list/?msg=success';

                            //Disable fields
                            $('.onSaveDisabled').attr("disabled", true);
                            $('#submitBtn').hide();
                            $('#cancelBtn').hide();
                            $('#okBtn').show();

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