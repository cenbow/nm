(function($, appui){


    if ("undefined" == typeof appui) throw new ReferenceError("appui don't found!")

    window.bootbox = require( "bootbox");
    window.toastr = require( "toastr" );

    /*bootbox extend*/
    if ("undefined" == typeof bootbox) throw new ReferenceError("bootbox don't found!");
    else {
        if(typeof (top.appui.dialog) != "undefined") appui.dialog = top.appui.dialog;
        else {
            bootbox.setLocale('zh_CN');
            appui.dialog = $.extend({}, bootbox);
            appui.dialog.modal = appui.dialog.dialog;
        }
    }

    /*toastr extend*/
    if ("undefined" == typeof toastr) throw new ReferenceError("toastr don't found!");
    else {
        appui.message = $.extend({}, toastr);
        appui.message.options.closeButton = true;
        appui.message.options.closeHtml = '<button><i class="fa fa-power-off fa-lg"></i></button>';
    }


})(jQuery, appui);
