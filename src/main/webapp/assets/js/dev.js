/**
 * Created by Zeeshan on 23/08/2017.
 */

$(document).ready(function(){
    $(".alpha-only").on("keydown", function(event){
        var arr = [8,9,16,17,20,35,36,37,38,39,40,45,46,127];

        // Allow letters
        for(var i = 65; i <= 90; i++){
            arr.push(i);
        }

        // Prevent default if not in array
        if(jQuery.inArray(event.which, arr) === -1){
            event.preventDefault();
        }
    });

    $(".alpha-only").on("input", function(){
        var regexp = /[^a-zA-Z]/g;
        if($(this).val().match(regexp)){
            $(this).val( $(this).val().replace(regexp,'') );
        }
    });

    $(".alpha-numeric-only").on("keydown", function(event){
        var arr = [8,9,16,17,20,35,36,37,38,39,40,45,46,127];

        // Allow letters
        for(var i = 65; i <= 90; i++){
            arr.push(i);
        }

        //Allow Numbers
        for(var i = 48; i <= 57; i++){
            arr.push(i);
        }

        // Prevent default if not in array
        if(jQuery.inArray(event.which, arr) === -1){
            event.preventDefault();
        }
    });

    $(".alpha-numeric-only").on("input", function(){
        var regexp = /[^a-zA-Z0-9]/g;
        if($(this).val().match(regexp)){
            $(this).val( $(this).val().replace(regexp,'') );
        }
    });

});