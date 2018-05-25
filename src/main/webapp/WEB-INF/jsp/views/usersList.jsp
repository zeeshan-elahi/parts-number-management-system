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

    <title>Manage Users | Parts Number System</title>

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
                        <h3>Manage Users</h3>
                    </div>

                </div>

                <div class="clearfix"></div>

                <div id="successMessage" class="alert alert-success alert-dismissable" style="display: none;">
                    <a href="#" class="close" data-hide="alert" aria-label="close">&times;</a>
                    <span id="successMessageText"></span>
                </div>

                <div id="errorMessage" class="alert alert-error alert-dismissable" style="display:none;">
                    <a href="#" class="close" data-hide="alert" aria-label="close">&times;</a>
                    <span id="errorMessageText"></span>
                </div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">

                            <div class="x_content">

                                <table id="usersTable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>User Login</th>
                                        <th>Last Login</th>
                                        <th>User Type</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:choose>
                                        <c:when test="${fn:length(usersList) gt 0}">
                                            <c:forEach var="userItem" items="${usersList}">
                                                <c:set var="changeTypeTitle" value="Staff" />
                                                <c:choose>
                                                    <c:when test="${userItem.userTypeId.userTypeId eq '1'}">
                                                        <c:set var="changeTypeTitle" value="Staff" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="changeTypeTitle" value="Administrator" />
                                                    </c:otherwise>
                                                </c:choose>

                                                <c:set var="changeAccess" value="Block" />
                                                <c:choose>
                                                    <c:when test="${userItem.isActive eq '1'}">
                                                        <c:set var="changeAccess" value="Block" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="changeAccess" value="Unblock" />
                                                    </c:otherwise>
                                                </c:choose>
                                                <tr>
                                                    <c:set var="userNameTokens" value="${fn:split(userItem.userName, ' ')}" />
                                                    <td>${userItem.userName}</td>
                                                    <td>${userItem.userLogin}</td>
                                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${userItem.dateLastLogin}" /></td>
                                                    <td class="td_user_${userItem.userId} ${userItem.userTypeId.userTypeName} isActive_${userItem.isActive}">${userItem.userTypeId.userTypeName}</td>
                                                    <td style="text-align:center;">
                                                        <c:if test="${loggedInUser.getUserId() ne userItem.getUserId()}">
                                                        <a href="#" data-toggle="modal" data-target="#typeConfirmationModal" data-whatever="${userItem.userId}" title="Make ${changeTypeTitle}" class="a_userType_${userItem.userId}" userName="${userNameTokens[0]}" userLogin="${userItem.userLogin}">
                                                            <c:choose>
                                                            <c:when test="${userItem.userTypeId.userTypeId eq '1'}">
                                                            <i class="fa fa-long-arrow-down"></i><i class="fa fa-long-arrow-down"></i>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <i class="fa fa-long-arrow-up"></i><i class="fa fa-long-arrow-up"></i>
                                                            </c:otherwise>
                                                            </c:choose>
                                                        </a>
                                                        &nbsp;&nbsp;&nbsp;
                                                        <a href="#" data-toggle="modal" data-target="#accessConfirmationModal" data-whatever="${userItem.userId}" title="${changeAccess} User" class="a_userAccess_${userItem.userId}" userName="${userNameTokens[0]}" userLogin="${userItem.userLogin}">
                                                            <c:choose>
                                                                <c:when test="${userItem.isActive eq '1'}">
                                                                    <i class="fa fa-user"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class="fa fa-user-times"></i>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </a>
                                                        </c:if>
                                                    </td>
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

        <!-- Confirmation Modals -->
        <div class="modal fade" id="typeConfirmationModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form id="typeForm" name="typeForm">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>&times;</span></button>
                            <h4 class="modal-title" id="typeConfirmationModalLabel">Confirm</h4>
                        </div>
                        <div class="modal-body">
                            Do you really want to change user type of this user?
                        </div>
                        <div class="modal-footer">
                            <button id="typeSubmitBtn" type="button" class="btn btn-primary">Yes</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                            <input type="hidden" id="typeUserId" name="typeUserId" value="" />
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal fade" id="accessConfirmationModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form id="accessForm" name="accessForm">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>&times;</span></button>
                            <h4 class="modal-title" id="accessConfirmationModalLabel">Confirm</h4>
                        </div>
                        <div class="modal-body">
                            Do you really want to block/unblock this user?
                        </div>
                        <div class="modal-footer">
                            <button id="accessSubmitBtn" type="button" class="btn btn-primary">Yes</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                            <input type="hidden" id="accessUserId" name="userId" value="" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- /Confirmation Modal -->

        <!-- footer content -->
        <jsp:include page="../layout/footer.jsp" />
        <!-- /footer content -->
    </div>
</div>

<jsp:include page="../includes/js.jsp" />

<script type="text/javascript">
    jQuery(document).ready(function(){

        $('#typeConfirmationModal').on('show.bs.modal', function (event) {

            //$('#successMessage').hide();

            var button = $(event.relatedTarget) // Button that triggered the modal
            var userId = button.data('whatever') // Extract info from data-* attributes
            $("#typeUserId").val(userId);
            console.log("Access User ID: " + $("#typeUserId").val());
            // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
            // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            var confirmationModal = $(this)
            //confirmationModal.find('.modal-title').text('New message to ' + recipient)
            //confirmationModal.find('.modal-body input').val(recipient)
            var userName = $('.a_userType_' + userId).attr('userName');
            var userLogin = $('.a_userType_' + userId).attr('userLogin');

            var dialogText = "";

            if($('.td_user_' + userId).hasClass('Administrator')){
                dialogText = "Do you want downgrade " + userName + " (" + userLogin + ") from Administrator to Staff?";
            }else{
                dialogText = "Do you want upgrade " + userName + " (" + userLogin + ") from Staff to Administrator?";
            }


            confirmationModal.find('.modal-body').html(dialogText);
        });

        $('#accessConfirmationModal').on('show.bs.modal', function (event) {

            //$('#successMessage').hide();

            var button = $(event.relatedTarget) // Button that triggered the modal
            var userId = button.data('whatever') // Extract info from data-* attributes
            $("#accessUserId").val(userId);
            console.log("Access User ID: " + $("#accessUserId").val());
            // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
            // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            var accessModal = $(this)
            //accessModal.find('.modal-title').text('New message to ' + recipient)
            //accessModal.find('.modal-body input').val(recipient)
            var userName = $('.a_userAccess_' + userId).attr('userName');
            var userLogin = $('.a_userAccess_' + userId).attr('userLogin');
            var actionText = ($('.td_user_' + userId).hasClass('isActive_1'))? 'block' : 'unblock';
            var dialogText = 'Do you want to ' + actionText + ' ' + userName + " (" + userLogin + ")?";
            accessModal.find('.modal-body').html(dialogText);
        });

        jQuery('#usersTable').dataTable({
            paging: false,
            order: [[ 0, "asc" ]]
        });

        jQuery('#typeSubmitBtn').on('click', function (e) {

            e.preventDefault();

            var userId = $('#typeUserId').val();
            var newUserTypeId = ($('.td_user_' + userId).hasClass('Administrator'))? "2" : "1";

            var upButtonHTML = '<i class="fa fa-long-arrow-up"><i class="fa fa-long-arrow-up"></i>';
            var downButtonHTML = '<i class="fa fa-long-arrow-down"><i class="fa fa-long-arrow-down"></i>';

            $.ajax({
                dataType: "json",
                url: "/manage/changeusertype/?userId=" + userId + "&newUserTypeId=" + newUserTypeId,
                success: function( response, status, xhr ) {

                    if(response.result === 'Success'){

                        $('#errorMessage').hide();
                        $('#successMessageText').html("User type have been changed successfully.");
                        $('#successMessage').show();

                        if($('.td_user_' + userId).hasClass('Administrator')){

                            $('.a_userType_' + userId).html(upButtonHTML);
                            $('.a_userType_' + userId).attr('title', 'Make Administrator');

                            $('.td_user_' + userId).html('Staff');
                            $('.td_user_' + userId).removeClass('Administrator');
                            $('.td_user_' + userId).addClass('Staff');
                        }else{

                            $('.a_userType_' + userId).html(downButtonHTML);
                            $('.a_userType_' + userId).attr('title', 'Make Staff');

                            $('.td_user_' + userId).html('Administrator');
                            $('.td_user_' + userId).removeClass('Staff');
                            $('.td_user_' + userId).addClass('Administrator');
                        }

                    }else if(response.result === 'Failure'){
                        $('#successMessage').hide();
                        $('#errorMessageText').html(response.message);
                        $('#errorMessage').show();
                    }
                }
            });

            $('#typeConfirmationModal').modal('hide')

        });

        jQuery('#accessSubmitBtn').on('click', function (e) {

            e.preventDefault();

            var userId = $('#accessUserId').val();
            var newAccountStatus = ($('.td_user_' + userId).hasClass('isActive_1'))? "0" : "1";

            var blockButtonHTML = '<i class="fa fa-user-times"></i>';
            var unblockButtonHTML = '<i class="fa fa-user"></i>';

            $.ajax({
                dataType: "json",
                url: "/manage/changeacountstatus/?userId=" + userId + "&newAccountStatus=" + newAccountStatus,
                success: function( response, status, xhr ) {

                    if(response.result === 'Success'){

                        $('#errorMessage').hide();
                        $('#successMessageText').html("User account status have been changed successfully.");
                        $('#successMessage').show();

                        if($('.td_user_' + userId).hasClass('isActive_1')){

                            $('.a_userAccess_' + userId).html(blockButtonHTML);
                            $('.a_userAccess_' + userId).attr('title', 'Unblock User');

                            $('.td_user_' + userId).removeClass('isActive_1');
                            $('.td_user_' + userId).addClass('isActive_0');
                        }else{

                            $('.a_userAccess_' + userId).html(unblockButtonHTML);
                            $('.a_userAccess_' + userId).attr('title', 'Block User');

                            $('.td_user_' + userId).removeClass('isActive_0');
                            $('.td_user_' + userId).addClass('isActive_1');
                        }

                    }else if(response.result === 'Failure'){
                        $('#successMessage').hide();
                        $('#errorMessageText').html(response.message);
                        $('#errorMessage').show();
                    }
                }
            });

            $('#accessConfirmationModal').modal('hide');

        });

        jQuery("[data-hide]").on("click", function(){
            jQuery(this).closest("." + jQuery(this).attr("data-hide")).hide();
        });

    });
</script>

</body>
</html>