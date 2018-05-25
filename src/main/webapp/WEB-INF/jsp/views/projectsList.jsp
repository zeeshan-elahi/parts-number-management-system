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

    <title>Manage Projects | Parts Number System</title>

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
                        <h3>Manage Projects</h3>
                    </div>

                    <div class="title_right">
                        <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#projectModal" data-whatever="0">Add New Project</button>
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

                                <table id="projectsTable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Project Number</th>
                                        <th>Project Code</th>
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
                                        <c:when test="${fn:length(projectsList) gt 0}">
                                            <c:forEach var="project" items="${projectsList}">
                                                <tr>
                                                    <td><fmt:formatNumber pattern="0000" value="${project.projectNumber}" /></td>
                                                    <td>${project.projectCode}</td>
                                                    <td>${project.projectDescription}</td>
                                                    <td>${project.comment}</td>
                                                    <td>${project.projectStatusId.projectStatusName}</td>
                                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${project.dateCreated}" /></td>
                                                    <td>${project.createdByUserId.userName}</td>
                                                    <td>${project.updatedByUserId.userName}</td>
                                                    <td><a href="#" data-toggle="modal" data-target="#projectModal" data-whatever="${project.projectId}"><i class="fa fa-pencil-square datatable_action"></i></a></td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr id="notFoundRow">
                                                <td colspan="9" style="text-align: center;">No record found.</td>
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
        <div class="modal fade" id="projectModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form id="projectForm" name="projectForm">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="exampleModalLabel">Add/Edit Project</h4>
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
                                    <div>
                                        <label for="projectNumber" class="control-label">Project Number</label>
                                        <input type="text" class="form-control editingNowAllowed onSaveDisabled" id="projectNumber" name="projectNumber" readonly="readonly" value="0000" />
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input id="isLegacyProject" name="isLegacyProject" type="checkbox" value="1" class="editingNowAllowed onSaveDisabled" /> Is legacy project?
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group">
                                    <label for="projectCode" class="control-label">Project Code</label>
                                    <input type="text" class="form-control uppercase alpha-numeric-only onSaveDisabled" id="projectCode" name="projectCode" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="projectDescription" class="control-label">Description</label>
                                <textarea class="form-control onSaveDisabled" id="projectDescription" name="projectDescription"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="comment" class="control-label">Comment</label>
                                <textarea class="form-control onSaveDisabled" id="comment" name="comment"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="projectStatusId" class="control-label">Status</label>
                                <select id="projectStatusId" name="projectStatusId" class="form-control onSaveDisabled">
                                    <c:forEach var="projectStatus" items="${projectStatusList}">
                                        <option value="${projectStatus.projectStatusId}">${projectStatus.projectStatusName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="submitBtn" type="submit" class="btn btn-primary">Save</button>
                            <button id="cancelBtn" type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button id="okBtn" type="button" class="btn btn-success" style="display: none;">Ok</button>
                            <input type="hidden" id="projectId" name="projectId" />
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

        $('#projectModal').on('show.bs.modal', function (event) {

            $('#successMessage').hide();

            var button = $(event.relatedTarget) // Button that triggered the modal
            var itemId = button.data('whatever') // Extract info from data-* attributes
            console.log("Item ID: " + itemId);
            $('#projectId').val(itemId);

            //Enable, hide and show fields that have been modified on last save
            $('.onSaveDisabled').attr("disabled", false);
            $('#okBtn').hide();
            $('#submitBtn').show();
            $('#cancelBtn').show();
            $('#errorMessage').hide();
            $('#savedInfoMessage').hide();

            //Reset form and validation messages
            jQuery("#projectForm")[0].reset();
            var validator = jQuery( "#projectForm" ).validate();
            validator.resetForm();
            $(".error").removeClass("error");

            $(".editingNowAllowed").attr("disabled", false);
            if(itemId !== 'undefined' && itemId !== 0){
                $(".editingNowAllowed").attr("disabled", true);
            }else{
                $("#projectNumber").attr("readonly", true);
                $("#isLegacyProject").attr("checked", false);
            }

            $.ajax({
                dataType: "json",
                url: "/project/load/" + itemId,
                success: function( response, status, xhr ) {

                    $('#projectNumber').val(response.projectNumber);

                    if(response.isLegacyProject == 1){
                        $("#isLegacyProject").attr("checked", true);
                    }else{
                        $("#isLegacyProject").attr("checked", false);
                    }

                    $('#projectCode').val(response.projectCode);
                    $('#projectDescription').val(response.projectDescription);
                    $('#comment').val(response.comment);
                    $('#projectStatusId').val(response.projectStatusId.projectStatusId);

                    var modal = $(this);
                }
            });

        });

        <c:choose>
        <c:when test="${fn:length(projectsList) gt 0}">
        jQuery('#projectsTable').dataTable({
            "pageLength": 25,
            "order": [[ 0, "asc" ]],
            columnDefs: [
                { orderable: false, targets: -1 }
            ]
        });
        </c:when>
        </c:choose>

        $("#isLegacyProject").click( function(){
            if( $(this).is(':checked') ){
                $("#projectNumber").attr("readonly", false);
            }else{
                $("#projectNumber").attr("readonly", true);
            }
        });

        jQuery("[data-hide]").on("click", function(){
            jQuery(this).closest("." + jQuery(this).attr("data-hide")).hide();
        });

        jQuery("#okBtn").on("click", function(){
            window.location = '/project/list';
        });

        jQuery.validator.addMethod("regexOnly", function(value, element, param) {
            return (value !== 'undefined' && value !== '')? value.match(new RegExp("" + param + "$")) : true;
        });

        $.validator.addMethod('lessThanEqual', function(value, element, param) {
            //if (this.optional(element)) return true;
            if( $('[name="isLegacyProject"]:checked').val() === undefined) return true;
            var i = parseInt(value);
            var j = parseInt(param);
            return (i > 0 && i <= j);
        });

        jQuery('#projectForm').validate({
            rules:{
                projectNumber: {
                    required: {
                        depends: function(element){
                            var status = false;
                            if( jQuery('[name="isLegacyProject"]:checked').val() !== undefined){
                                status = true;
                            }
                            return status;
                        }
                    },
                    regexOnly: '[0-9]',
                    lessThanEqual: 999,
                    remote: {
                        url: '/project/isavailable/',
                        data: {
                            projectId: function() {
                                return $( "#projectId" ).val();
                            }
                        }
                    }
                },
                projectCode: {
                    required: true,
                    regexOnly: '[a-zA-Z0-9]',
                    maxlength: 8,
                    minlength: 1,
                    remote: {
                        url: '/project/isavailable/',
                        data: {
                            projectId: function() {
                                return $( "#projectId" ).val();
                            }
                        }
                    }
                },
                projectDescription: {
                    maxlength: 200
                },
                comment: {
                    maxlength: 200
                },
                projectStatusId: {
                    required: true
                }
            },
            messages: {
                projectNumber: {
                    required: 'Please enter Project Number.',
                    regexOnly: 'Please enter numbers only.',
                    lessThanEqual: 'Legacy Project Number should be between 1 and 999.',
                    remote: 'Project Number entered already exist.'
                },
                projectCode: {
                    required: 'Please enter Project Code',
                    regexOnly: 'Please enter alphabets and numbers only.',
                    maxlength: "Project Code can't be more than 8 characters",
                    minlength: 'Please enter at least one character.',
                    remote: 'Project Code entered already exist.'

                },
                projectDescription: {
                    maxlength: 'Please enter only 200 characters.'
                },
                comment: {
                    maxlength: 'Please enter only 200 characters.'
                },
                projectStatusId: {
                    required: 'Please select a value.'
                }
            },
            submitHandler: function(form) {
                jQuery.ajax({
                    dataType: "json",
                    url: '/project/save',
                    type: 'POST',
                    data: jQuery(form).serialize(),
                    success: function(response) {
                        if(response.result === 'Success'){
                            //window.location = '/project/list/?msg=success';

                            //Disable fields
                            $('.onSaveDisabled').attr("disabled", true);
                            $('#submitBtn').hide();
                            $('#cancelBtn').hide();
                            $('#okBtn').show();

                            $('#projectNumber').val(response.projectNumber);

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