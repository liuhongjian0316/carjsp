/**
 * 车辆类型下拉框赋值
 */
$.ajax({
    type: 'get',
    url: window.location.protocol + '//' + window.location.host + '/cartype/getall',
    dataType: 'json',
    contentType: 'application/json',
    success: function (data) {
        $.each(data.data, function (index, item) {
            $('#addvehicleClassname').append(new Option(item.vehicleClassname, item.cartypeId));// 下拉菜单里添加元素
        });
    }
});

/**
 * 车辆品牌下拉框
 */
$.ajax({
    type: 'get',
    url: window.location.protocol + '//' + window.location.host + '/carmodel/getall',
    dataType: 'json',
    contentType: 'application/json',
    success: function (data) {
        $.each(data.data, function (index, item) {
            $('#addbrandModel').append(new Option(item.name, item.name));// 下拉菜单里添加元素
        });
    }
});

/**
 * 时间下拉框
 */
$('#data1').datetimepicker({
    format: 'yyyy-mm-dd',
    language: 'zh-CN',
    showClose:true,	//是否显示关闭 按钮
    viewMode: 'days',//天数模块展示，months则为以月展示
    daysOfWeekDisabled: false,//星期几 禁止选择,参数 [num],多个逗号隔开
    calendarWeeks: false,	//显示 周 是 今年第几周
    toolbarPlacement:'default', //工具摆放的位置，top 则为上，默认为底
    showTodayButton:false,	//是否工具栏 显示 直达今天天数的 按钮，默认false
    showClear:true, //是否 工具栏显示  清空 输入框  的按钮。默认false
});
$('#data2').datetimepicker({
    format: 'yyyy-mm-dd',
    language: 'zh-CN',
    showClose:true,	//是否显示关闭 按钮
    viewMode: 'days',//天数模块展示，months则为以月展示
    daysOfWeekDisabled: false,//星期几 禁止选择,参数 [num],多个逗号隔开
    calendarWeeks: false,	//显示 周 是 今年第几周
    toolbarPlacement:'default', //工具摆放的位置，top 则为上，默认为底
    showTodayButton:false,	//是否工具栏 显示 直达今天天数的 按钮，默认false
    showClear:true, //是否 工具栏显示  清空 输入框  的按钮。默认false
});
/**
* 添加页面校验
*/
function checkAny(obj,regx,flagId,iconId,msgId,msg){
    var obj =  $.trim($(obj).val());
    if(obj == ''){
        $('#'+msgId).html("此处不能为空");
        $('#'+flagId).attr("class","form-group has-error has-feedback");
        $('#'+iconId).attr("class","glyphicon glyphicon-remove form-control-feedback");
        return false;
    }else{
        if(regx.test(obj) == false){
            //不符合
            $('#'+msgId).html(msg);
            $('#'+flagId).attr("class","form-group has-error has-feedback");
            $('#'+iconId).attr("class","glyphicon glyphicon-remove form-control-feedback");
            return false;
        }else{
            $('#'+msgId).html("");
            $('#'+flagId).attr("class","form-group has-success has-feedback");
            $('#'+iconId).attr("class","glyphicon glyphicon-ok form-control-feedback");
            return true;
        }
    }
}
/**
 * 校验两次密码是否相同
 */
function pwdIsSame(){
    var pwd1 =  $.trim($('#addpwd').val());
    var pwd2 =  $.trim($('#addreppwd').val());
    if(pwd1 == ''){
        $('#addreppwdMsg').html("");
        $('#addreppwdflag').attr("class","form-group has-success has-feedback");
        $('#addreppwdicon').attr("class","glyphicon glyphicon-ok form-control-feedback");
        return false;
    }else{
        if(pwd1 !== pwd2){
            //不相同
            $('#addreppwdMsg').html("两次密码不相同");
            $('#addreppwdflag').attr("class","form-group has-error has-feedback");
            $('#addreppwdicon').attr("class","glyphicon glyphicon-remove form-control-feedback");
            return false;
        }else{
            $('#addreppwdMsg').html("");
            $('#addreppwdflag').attr("class","form-group has-success has-feedback");
            $('#addreppwdicon').attr("class","glyphicon glyphicon-ok form-control-feedback");
            return true;
        }
    }
}
function pwdIsSame2(){
    var pwd1 =  $.trim($('#editpwd').val());
    var pwd2 =  $.trim($('#editreppwd').val());
    if(pwd1 == ''){
        $('#editreppwdMsg').html("");
        $('#editreppwdflag').attr("class","form-group has-success has-feedback");
        $('#editreppwdicon').attr("class","glyphicon glyphicon-ok form-control-feedback");
        return false;
    }else{
        if(pwd1 !== pwd2){
            //不相同
            $('#editreppwdMsg').html("两次密码不相同");
            $('#editreppwdflag').attr("class","form-group has-error has-feedback");
            $('#editreppwdicon').attr("class","glyphicon glyphicon-remove form-control-feedback");
            return false;
        }else{
            $('#editreppwdMsg').html("");
            $('#editreppwdflag').attr("class","form-group has-success has-feedback");
            $('#editreppwdicon').attr("class","glyphicon glyphicon-ok form-control-feedback");
            return true;
        }
    }
}
/**
 * 城市三级联动
 */
// $('#edit_s_county').attr('onchange','showArea()');
// $('#edit_s_province').attr('onchange','showArea()');
// $('#edit_s_city').attr('onchange','showArea()');
/**
 * 全选
 */
// $(function(){
// 	function initTableCheckbox() {
// 		var $thr = $('table thead tr');
// 		var $checkAllTh = $('<th><input type="checkbox" id="checkAll" name="checkAll" /></th>');
// 		/*将全选/反选复选框添加到表头最前，即增加一列*/
// 		$thr.prepend($checkAllTh);
// 		/*“全选/反选”复选框*/
// 		var $checkAll = $thr.find('input');
// 		$checkAll.click(function(event){
// 			/*将所有行的选中状态设成全选框的选中状态*/
// 			$tbr.find('input').prop('checked',$(this).prop('checked'));
// 			/*并调整所有选中行的CSS样式*/
// 			if ($(this).prop('checked')) {
// 				$tbr.find('input').parent().parent().addClass('warning');
// 			} else{
// 				$tbr.find('input').parent().parent().removeClass('warning');
// 			}
// 			/*阻止向上冒泡，以防再次触发点击操作*/
// 			event.stopPropagation();
// 		});
// 		/*点击全选框所在单元格时也触发全选框的点击操作*/
// 		$checkAllTh.click(function(){
// 			$(this).find('input').click();
// 		});
// 		var $tbr = $('table tbody tr');
// 		var $checkItemTd = $('<td><input type="checkbox" name="checkItem" /></td>');
// 		/*每一行都在最前面插入一个选中复选框的单元格*/
// 		$tbr.prepend($checkItemTd);
// 		/*点击每一行的选中复选框时*/
// 		$tbr.find('input').click(function(event){
// 			/*调整选中行的CSS样式*/
// 			$(this).parent().parent().toggleClass('warning');
// 			/*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
// 			$checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.length ? true : false);
// 			/*阻止向上冒泡，以防再次触发点击操作*/
// 			event.stopPropagation();
// 		});
// 		/*点击每一行时也触发该行的选中操作*/
// 		$tbr.click(function(){
// 			$(this).find('input').click();
// 		});
// 	}
// 	initTableCheckbox();
// });

/**
 * 设置全局变量 为了下面全选像后端传值（id 数组）
 */
var checkedId = new Array();
/**
 * 老账户 为了下面编辑框账号校验
 * @type {null}
 */
var oldaccount = null;

/**
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 * 给表格添加按钮和事件
 * 这段必须放在表格初始化之前。
 */
function addFunctionAlty(value, row, index) {
    return [
        '<button id="del" type="button" class="btn btn-danger">删除</button>',
        '<button id="update" type="button" class="btn btn-warning">修改</button>',
    ].join('');
}
window.operateEvents = {
    'click #del': function (e, value, row, index) {
        $('#delmodel').modal({
            show:true
        });
        $("#SureToDel").click(function () {
            $.ajax({
                type: 'get',
                url: window.location.protocol + '//' + window.location.host + '/user/delUser?id=' + row.id,
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    if (data.status == 200) {
                        $('#handlersuccess').modal({
                            show:true
                        })
                        jQuery(document).ready(function(){
                            setTimeout(function () {
                                $("#handlersuccess").modal("hide");
                                window.location.href = '/userPage.html';
                            }, 2000);
                        });
                    } else {
                        $('#handlerfail').modal({
                            show:true
                        })
                        jQuery(document).ready(function(){
                            setTimeout('$("#handlerfail").modal("hide")', 1000);
                        });
                    }
                },
                error: function () {
                    window.alert("网络超时");
                }
            });
        });
    }
    , 'click #update': function (e, value, row, index) {
        //编辑流程
        //打开模态框
        $("#editPage").modal({
            show: true
        });
        //根据id获取数据
        $.ajax({
            type:'get',
            url:window.location.protocol+'//'+window.location.host+'/user/getUserById?id='+row.id,
            dataType:'json',
            contentType:'application/json',
            async: false, //同步两个ajax
            success:function (data) {
                if (data.status == 200){
                    //给模态框赋值
                    $("#editid").val(data.data.id);
                    $("#edituseraccount").val(data.data.useraccount);
                    $("#editpwd").val(data.data.password);
                    $("#editrealname").val(data.data.realname);
                    $("#editreppwd").val(data.data.password);
                    $("#editsex").val(data.data.sex);
                    $("#edittel").val(data.data.tel);
                    $("#editrole").val(data.data.role);
                    oldaccount = $("#edituseraccount").val();
                    //将老账号发到后端
                    $.ajax({
                        type:'get',
                        url:window.location.protocol+'//'+window.location.host+'/user/oldaccount?useraccount='+oldaccount,
                        dataType:'json',
                        contentType:'application/json',
                        success:function (data) {
                        }
                    });
                    var addr = data.data.addr;
                    if(data.data.addr.indexOf("省")>0){
                        var split1 = addr.split("省");
                        var split2 = split1[1].split("市");
                        $("#edit_s_province").prepend("<option value=" +split1[0]+ '省'+ ">" + split1[0]+ "省" + "</option>");
                        $("#edit_s_city").prepend("<option value=" +split2[0]+ '市'+ ">" + split2[0]+ "市" + "</option>");
                        $("#edit_s_county").prepend("<option value=" +split2[1]+ ">" + split2[1] + "</option>");

                        $("#edit_s_province").val(split1[0]+"省");
                        $("#edit_s_city").val(split2[0]+"市");
                        $("#edit_s_county").val(split2[1]);
                        return;
                    }else if(data.data.addr.indexOf("北京市")>0 ||data.data.addr.indexOf("上海市")>0||data.data.addr.indexOf("天津市")>0||data.data.addr.indexOf("重庆市")){
                        var split = addr.split("市");
                        $("#edit_s_province").prepend("<option value=" +split[0]+ '市'+ ">" + split[0]+ "市" + "</option>");
                        $("#edit_s_city").prepend("<option value=" +split[1]+ '市'+ ">" + split[1]+ "市" + "</option>");
                        $("#edit_s_county").prepend("<option value=" +split[2]+ ">" + split[2] + "</option>");

                        $("#edit_s_province").val(split[0]+"市");
                        $("#edit_s_city").val(split[1]+"市");
                        $("#edit_s_county").val(split[2]);
                        return;
                    }
                }else{
                    window.alert("获取数据失败");
                }
            },
            error:function () {
                window.alert("网络超时");
            }
        });
    }
};
/**
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 * 性别处理
 */
function sexhandler(value, row, index){
    if(value == '1'){
        return [
            '<button type="button" class="btn btn-default">男</button>',
        ]
    }
    return [
        '<button type="button" class="btn btn-success">女</button>',
    ]
}
/**
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 * 角色处理
 */
function rolehandler(value, row, index){
    if(value == '1'){
        return [
            '<button type="button" class="btn btn-default">普通用户</button>',
        ]
    }else if(value == '2'){
        return [
            '<button type="button" class="btn btn-success">管理员</button>',
        ]
    }
    return [
        '<button type="button" class="btn btn-danger">超级管理员</button>',
    ]
}
/**
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 * 状态处理
 */
function statehandler(value, row, index){
    if(value == '0'){
        return [
            '<button type="button" class="btn btn-success">损坏</button>',
        ]
    }else if(value == '1'){
        return [
            '<button type="button" class="btn btn-success">正常</button>',
        ]
    }
    return [
        '<button type="button" class="btn btn-danger">借出</button>',
    ]
}
/**
 * 渲染表格
 */
var $table1= $('#table1').bootstrapTable({
    url: '/car/carTable',
    method: 'GET',
    toolbar: '#toolbar',
    height: 500,
    striped: true, //是否显示行间隔色
    cache:false,
    sortable: true,
    sortOrder: "asc",
    pagination: true,
    sidePagination: "server",//server client
    pageNumber:1,
    pageSize: 5,
    pageList: [5, 10, 15, 20],
    uniqueId:'id',
    clickToSelect: true,
    trimOnSearch: true,
    showColumns: true,
    showRefresh: true,
    showToggle: true,
    cardView: false,
    showJumpto: true,
    rowStyle:function(row, index){
        // var classes = ['bg-blue', 'bg-green','bg-red']
        //     if (index % 2 === 0 && index / 2 < classes.length) {
        //         return {  classes: classes[index / 2] }
        //     }
        // 	return { css: { color: 'red'} }
        return {css:{height: '70px'}}
    },
    columns: [
        { field: "status", checkbox:true},
        { field: 'carinfoId', title: 'ID'  },
        { field: 'plateNumber', title: '车牌号' },
        { field: 'vehicleClassname', title: '车辆类型' },
        { field: 'frameNumber', title: '车架号' },
        { field: 'factoryNumber', title: '出厂编号'},
        { field: 'brandModel', title: '车辆品牌' },
        { field: 'regTime', title: '注册时间' },
        { field: 'examinationTime', title: '年检时间' },
        { field: 'ratedPassengers', title: '额定载客' },
        { field: 'state', title: '状态',formatter: statehandler},
        { field: 'commentInfo', title: '备注信息',},
        { field: 'operate', title: '操作',events: operateEvents,formatter: addFunctionAlty },
    ],
    queryParams: function (params) {
        var param={
            page: params.offset,
            limit: params.limit,
            //useraccount: $('#accountsearch').val(),
            //realname: $('#namesearch').val(),
        }
        return param;
    },
    //处理数据格式
    responseHandler:function(data){
        return{
            rows : data.rows,
            total: data.total,
        }
    },
    //点击一行事件
    onClickRow : function(row, tr,flied){
        //里面空
        //如果写会与下面onCheck方法冲突
    },
    //全选按钮事件
    onCheckAll:function(rows){
        //删除之前的所有数组 有许多方法 赋值长度0，或给数组赋值[], splice(0,checkedId.length);
        checkedId.length = 0;
        //添加所有元素
        for(var i=0;i<rows.length;i++){
            checkedId.push(rows[i].id);
        }
    },
    //选择一行操作
    onCheck:function(row){
        if($.inArray(row.id,checkedId) == 1){
            //元素存在
        }else{
            checkedId.push(row.id);
        }
    },
    //取消一行操作
    onUncheck:function(row){
        //获取要删除元素的下标
        var index = checkedId.indexOf(row.id);
        //删除此元素
        checkedId.splice(index,1);
    },
    //取消所有操作
    onUncheckAll: function (rows) {
        checkedId.length = 0;
    },
});

/**
 * 搜索添加参数
 */
function Search() {
    var accountsearch= $('#accountsearch').val();
    var namesearch= $('#namesearch').val();
    param = {'accountsearch':accountsearch,'namesearch':namesearch};
    $('#table1').bootstrapTable('refresh', param);
}
/**
 * 搜素重置按钮
 */
function searchReset(){
    $('#accountsearch').val('');
    $('#namesearch').val('');
}

/**
 * 工具栏点击事件
 */
$('#del2').click(function(){
    if(checkedId.length==0){
        $('#pleasechoicemode').modal({
            show:true
        })
        jQuery(document).ready(function(){
            setTimeout('$("#pleasechoicemode").modal("hide")', 1000);
        });
    }else{
        $('#delmodel').modal({
            show:true
        });
        $("#SureToDel").click(function () {
            console.log(JSON.stringify(checkedId));
            $.ajax({
                type:'post',
                url:window.location.protocol+'//'+window.location.host+'/user/delUser',
                dataType:'json',
                data:JSON.stringify(checkedId), //数组数据
                contentType:'application/json',
                success:function (data) {
                    if (data.status == 200){
                        $('#handlersuccess').modal({
                            show:true
                        })
                        jQuery(document).ready(function(){
                            setTimeout(function () {
                                $("#handlersuccess").modal("hide");
                                window.location.href = '/userPage.html';
                            }, 2000);
                        });
                    }else{
                        $('#handlerfail').modal({
                            show:true
                        })
                        jQuery(document).ready(function(){
                            setTimeout('$("#handlerfail").modal("hide")', 1000);
                        });
                    }
                },
                error:function () {
                    window.alert("网络超时");
                }
            });
        });
        //alert(checkedId);
    }
});

$('#edit2').click(function(){
    if(checkedId.length==0){
        $('#pleasechoicemode').modal({
            show:true
        })
        jQuery(document).ready(function(){
            setTimeout('$("#pleasechoicemode").modal("hide")', 1000);
        });
    }else if(checkedId.length==1){
        //打开模态框
        $("#editPage").modal({
            show: true
        });
        //根据id获取数据
        $.ajax({
            type:'get',
            url:window.location.protocol+'//'+window.location.host+'/user/getUserById?id='+checkedId[0],
            dataType:'json',
            contentType:'application/json',
            async: false, //同步两个ajax
            success:function (data) {
                if (data.status == 200){
                    //给模态框赋值
                    $("#editid").val(data.data.id);
                    $("#edituseraccount").val(data.data.useraccount);
                    $("#editpwd").val(data.data.password);
                    $("#editrealname").val(data.data.realname);
                    $("#editreppwd").val(data.data.password);
                    $("#editsex").val(data.data.sex);
                    $("#edittel").val(data.data.tel);
                    $("#editrole").val(data.data.role);
                    oldaccount = $("#edituseraccount").val();
                    //将老账号发到后端
                    $.ajax({
                        type:'get',
                        url:window.location.protocol+'//'+window.location.host+'/user/oldaccount?useraccount='+oldaccount,
                        dataType:'json',
                        contentType:'application/json',
                        success:function (data) {
                        }
                    });
                    var addr = data.data.addr;
                    if(data.data.addr.indexOf("省")>0){
                        var split1 = addr.split("省");
                        var split2 = split1[1].split("市");
                        $("#edit_s_province").prepend("<option value=" +split1[0]+ '省'+ ">" + split1[0]+ "省" + "</option>");
                        $("#edit_s_city").prepend("<option value=" +split2[0]+ '市'+ ">" + split2[0]+ "市" + "</option>");
                        $("#edit_s_county").prepend("<option value=" +split2[1]+ ">" + split2[1] + "</option>");

                        $("#edit_s_province").val(split1[0]+"省");
                        $("#edit_s_city").val(split2[0]+"市");
                        $("#edit_s_county").val(split2[1]);
                        return;
                    }else if(data.data.addr.indexOf("北京市")>0 ||data.data.addr.indexOf("上海市")>0||data.data.addr.indexOf("天津市")>0||data.data.addr.indexOf("重庆市")){
                        var split = addr.split("市");
                        $("#edit_s_province").prepend("<option value=" +split[0]+ '市'+ ">" + split[0]+ "市" + "</option>");
                        $("#edit_s_city").prepend("<option value=" +split[1]+ '市'+ ">" + split[1]+ "市" + "</option>");
                        $("#edit_s_county").prepend("<option value=" +split[2]+ ">" + split[2] + "</option>");

                        $("#edit_s_province").val(split[0]+"市");
                        $("#edit_s_city").val(split[1]+"市");
                        $("#edit_s_county").val(split[2]);
                        return;
                    }
                }else{
                    window.alert("获取数据失败");
                }
            },
            error:function () {
                window.alert("网络超时");
            }
        });


    }else{
        $('#choiceonemode').modal({
            show:true
        })
        jQuery(document).ready(function(){
            setTimeout('$("#choiceonemode").modal("hide")', 1000);
        });
    }
});


//刷新方法
$('#heartBtn').click(function () {
    ////刷新处理，指定query 的参数，注：此地方指定的参数，仅在当次刷新时使用
    //$table1.bootstrapTable('refresh', {
    //    query: {
    //        name: '张三'
    //    }
    //});
    $table1.bootstrapTable('refresh');
});
//账号是否重复
function accountIsRep(){
    var flag = false;
    $.ajax({
        type: 'get',
        url: window.location.protocol + '//' + window.location.host + '/user/useraccountIsRep?useraccount=' + $("#adduseraccount").val(),
        dataType: 'json',
        contentType: 'application/json',
        async: false,
        success: function (data) {
            if (data.status == 200) {
                if (data.data) {
                    $("#addaccountMsg").html("账号可用");
                    $("#addaccountflag").attr("class", "form-group has-success has-feedback");
                    $("#addacounticon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
                    flag = true;
                } else {
                    $("#addaccountMsg").html("账号重复");
                    $("#addaccountflag").attr("class", "form-group has-error has-feedback");
                    $("#addacounticon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
                    flag = false;
                }
            }
        }
    });
    return flag;
}
//编辑账号是否重复
function accountIsRepEdit(){
    var flag = false;
    $.ajax({
        type: 'get',
        url: window.location.protocol + '//' + window.location.host + '/user/useraccountIsRepEdit?useraccount=' + $("#edituseraccount").val(),
        dataType: 'json',
        contentType: 'application/json',
        async: false,
        success: function (data) {
            if (data.status == 200) {
                if (data.data) {
                    $("#editaccountMsg").html("账号可用");
                    $("#editaccountflag").attr("class", "form-group has-success has-feedback");
                    $("#editacounticon").attr("class", "glyphicon glyphicon-ok form-control-feedback");
                    flag = true;
                } else {
                    $("#editaccountMsg").html("账号重复");
                    $("#editaccountflag").attr("class", "form-group has-error has-feedback");
                    $("#editacounticon").attr("class", "glyphicon glyphicon-remove form-control-feedback");
                    flag = false;
                }
            }
        }
    });
    return flag;
}
//增加车辆
$("#addCar").click(function () {
    //获取所有数据
    var plateNumber =  $("#addplateNumber").val();
    var vehicleClassname = $("#addvehicleClassname").val();
    var frameNumber = $("#addframeNumber").val();
    var factoryNumber = $("#addfactoryNumber").val();
    var brandModel = $("#addbrandModel").val();
    var regTime = $("#addregTime").val();
    var examinationTime = $("#addexaminationTime").val();
    var ratedPassengers = $("#addratedPassengers").val();
    var state = $("#addstate").val();
    var commentInfo = $("#addcommentInfo").val();

    var carinfo = {};
    carinfo.plateNumber = plateNumber;
    carinfo.vehicleClassname = vehicleClassname;
    carinfo.frameNumber = frameNumber;
    carinfo.factoryNumber = factoryNumber;
    carinfo.brandModel = brandModel;
    carinfo.regTime = regTime;
    carinfo.examinationTime = examinationTime;
    carinfo.ratedPassengers = ratedPassengers;
    carinfo.state = state;
    carinfo.commentInfo = commentInfo;
    if(
        checkAny('#addplateNumber',/(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)/,'addplateNumberflag','addplateNumbericon','addplateNumberMsg','数据不合法')
        && checkAny('#addvehicleClassname',/^[\s\S]*.*[^\s][\s\S]*$/,'addvehicleClassnameflag','addvehicleClassnameicon','addvehicleClassnameMsg','请选择')
        && checkAny('#addframeNumber',/^[A-Za-z0-9]{17}$/,'addframeNumberflag','addframeNumbericon','addframeNumberMsg','数据不合法')
        && checkAny('#addfactoryNumber',/^[0-9]{6}$/,'addfactoryNumberflag','addfactoryNumbericon','addfactoryNumberMsg','数据不合法')
        && checkAny('#addbrandModel',/^[\s\S]*.*[^\s][\s\S]*$/,'addbrandModelflag','addbrandModelicon','addbrandModelMsg','请选择')
        && checkAny('#addregTime',/^[\s\S]*.*[^\s][\s\S]*$/,'addregtimeflag','addregtimeicon','addregtimeMsg','请选择')
        && checkAny('#addexaminationTime',/^[\s\S]*.*[^\s][\s\S]*$/,'addexaminationTimeflag','addexaminationTimeicon','addexaminationTimeMsg','请选择')
        && checkAny('#addratedPassengers', /(^[1-9]{2}$)|(^[1-9]{1}$)/,'addratedPassengersflag','addratedPassengersicon','addratedPassengersMsg','数据不合法')
        && checkAny('#addstate',/^[\s\S]*.*[^\s][\s\S]*$/,'addstateflag','addstateicon','addstateMsg','请选择')
    ){
        //提交
        $.ajax({
            type:'post',
            url:window.location.protocol+'//'+window.location.host+'/car/addCar',
            dataType:'json',
            data:JSON.stringify(carinfo),
            contentType:'application/json',
            success:function (data) {
                if (data.status == 200){
                    $('#handlersuccess').modal({
                        show:true
                    })
                    jQuery(document).ready(function(){
                        setTimeout(function () {
                            $("#handlersuccess").modal("hide");
                            window.location.href = '/carinfoPage.html';
                        }, 2000);
                    });
                }else{
                    $('#handlerfail').modal({
                        show:true
                    })
                    jQuery(document).ready(function(){
                        setTimeout('$("#handlerfail").modal("hide")', 1000);
                    });
                }
            },
            error:function () {
                window.alert("网络超时");
            }
        });
    }
});
//编辑用户
$("#editUser").click(function () {
    var id = $("#editid").val();
    var useraccount = $("#edituseraccount").val();
    var password = $("#editpwd").val();
    var realname =$("#editrealname").val();
    var sex = $("#editsex").val();
    var tel = $("#edittel").val();
    var addr = $("#edit_s_province").val()+$("#edit_s_city").val()+$("#edit_s_county").val();
    var role = $("#editrole").val();
    var user = {};
    user.id = id;
    user.useraccount = useraccount;
    user.password = password;
    user.realname = realname;
    user.sex = sex;
    user.tel = tel;
    user.addr = addr;
    user.role = role;
    if(
        checkAny("#edituseraccount",/^[a-zA-Z0-9_-]{4,10}$/,'editaccountflag','editacounticon','editaccountMsg','数据不合法')
        && checkAny("#editpwd",/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/,'editpwdflag','editpwdicon','editpwdMsg','数据不合法')
        && checkAny("#editrealname",/^[\u4e00-\u9fa5]{2,4}$/,'editrealnameflag','editrealnameicon','editrealnameMsg','数据不合法')
        && checkAny("#editreppwd",/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/,'editreppwdflag','editreppwdicon','editreppwdMsg','数据不合法')
        && pwdIsSame2()
        && checkAny("#editsex",/^[\s\S]*.*[^\s][\s\S]*$/,'editsexflag','editsexicon','editsexMsg','请选择')
        && checkAny("#edittel",/^1(3|4|5|6|7|8|9)\d{9}$/,'edittelflag','edittelicon','edittelMsg','数据不合法')
        && checkAny('#edit_s_province',/^[\s\S]*.*[^\s][\s\S]*$/,'editprovinceflag','editprovinceicon','editprovinceMsg','请选择')
        && checkAny('#edit_s_city',/^[\s\S]*.*[^\s][\s\S]*$/,'editprovinceflag','editprovinceicon','editprovinceMsg','请选择')
        && checkAny('#edit_s_county',/^[\s\S]*.*[^\s][\s\S]*$/,'editprovinceflag','editprovinceicon','editprovinceMsg','请选择')
        && checkAny("#editrole",/^[\s\S]*.*[^\s][\s\S]*$/,'editroleflag','editroleicon','editroleMsg','请选择')
        &&accountIsRepEdit()
    ){
        //提交
        $.ajax({
            type:'post',
            url:window.location.protocol+'//'+window.location.host+'/user/editUser',
            dataType:'json',
            data:JSON.stringify(user),
            contentType:'application/json',
            success:function (data) {
                if (data.status == 200){
                    $('#handlersuccess').modal({
                        show:true
                    })
                    jQuery(document).ready(function(){
                        setTimeout(function () {
                            $("#handlersuccess").modal("hide");
                            window.location.href = '/userPage.html';
                        }, 1000);
                    });
                }else{
                    $('#handlerfail').modal({
                        show:true
                    })
                    jQuery(document).ready(function(){
                        setTimeout('$("#handlerfail").modal("hide")', 1000);
                    });
                }
            },
            error:function () {
                window.alert("网络超时");
            }
        });
    }
});