$(function () {
    loadIndexpage();
    $("#addeasypoi").click(addeasypoi);
    $("#exportstudent").click(exportstudent);
    $("#importstudent").click(importstudent);
    $("#previous").click(previousdemo);
    $("#next").click(nextdemo);
    $("#dc").click(dc);
});

var pageNo = 1;
var pageSize = 6;
var totalPages = 0;
// 打开页面时分页加载表格数据
function loadIndexpage() {
    var data = {pageNo:pageNo,pageSize:pageSize};
    $.post(
        "loadeasypoipage",
        data,
        function (result) {
            pages(result);
            showTable(result.list);
        }
    );
}

function showTable(list) {
    $("#tdata").html("");
    for(var i = 0;i < list.length;i++){
        var student = list[i];
        if(i % 2 == 0){
            var td = tdtemplate.replace('[name]',student.name);
        }else {
            var td = tdtemplate1.replace('[name]',student.name);
        }
        td = td.replace('[age]',student.age);
        td = td.replace('[gender]',student.gender);
        td = td.replace('[salary]',student.salary);
        td = td.replace('[address]',student.address);
        td = td.replace('[createtime]',student.createtime);
        td = td.replace('[id]',student.id);
        td = td.replace('[student]',student);
        $("#tdata").append(td);
    }
}

var tdtemplate =
        '<tr>' +
            '<td>' + '[name]' + '</td>' +
            '<td>' + '[age]' + '</td>' +
            '<td>' + '[gender]' + '</td>' +
            '<td>' + '[salary]' + '</td>' +
            '<td>' + '[address]' + '</td>' +
            '<td>' + '[createtime]' + '</td>' +
            '<td>' +
                '<button type="button" class="btn btn-primary">查看</button>&nbsp;&nbsp;' +
                '<button type="button" class="btn btn-primary" onclick = modifystudent(' + '[student]' + ')>修改</button>&nbsp;&nbsp;' +
                '<button type="button" class="btn btn-primary" onclick = deletestudent(\'' + '[id]' + '\')>删除</button>' +
            '</td>' +
        '</tr>'

var tdtemplate1 =
    '<tr class="danger">' +
    '<td>' + '[name]' + '</td>' +
    '<td>' + '[age]' + '</td>' +
    '<td>' + '[gender]' + '</td>' +
    '<td>' + '[salary]' + '</td>' +
    '<td>' + '[address]' + '</td>' +
    '<td>' + '[createtime]' + '</td>' +
    '<td>' +
    '<button type="button" class="btn btn-primary">查看</button>&nbsp;&nbsp;' +
    '<button type="button" class="btn btn-primary" onclick = modifystudent(\'' + '[student]' + '\')>修改</button>&nbsp;&nbsp;' +
    '<button type="button" class="btn btn-primary" onclick = deletestudent(\'' + '[id]' + '\')>删除</button>' +
    '</td>' +
    '</tr>'

// 添加表格数据
function addeasypoi() {
    var url = "/addeasypoi";
    var data = {
        "name":$("#name").val(),
        "age":$("#age").val(),
        "gender":$("input[name='sex']:checked").val(),
        "salary":$("#salary").val(),
        "address":$("#address").val()
    };
    if($("#name").val().trim() == "" || $("#age").val().trim() == "" || $("#salary").val().trim() == "" || $("#address").val().trim() == ""){
        alert("每条信息都需要填写哟!");
    }else{
        if(confirm("确定要添加该数据?")){
            $('#myModal').modal('hide');
            $('#myModal').on('hidden.bs.modal', function () {
                $.post(url,data,function(result){
                    alert("添加成功!!!");
                    // loadIndexpage();
                    window.location.reload();
                });
            });
        }
    }
}

// 导出表格数据
function exportstudent() {
    if(confirm("确定要导出全部数据?")){
        window.location.href="/exportstudent";
    }
}

// 导入
function importstudent() {
    $('#importModal').modal('hide');
    var formData = new FormData();
    formData.append("file",$("#file")[0].files[0]);
    $.ajax({
        url:"importData",
        type: "POST",
        data: formData,
        /**
         *必须false才会自动加上正确的Content-Type
         */
        contentType: false,
        /**
         * 必须false才会避开jQuery对 formdata 的默认处理
         * XMLHttpRequest会对 formdata 进行正确的处理
         */
        processData: false,
        success:function(data){
            alert("导入成功!!!");
            loadIndexpage();
            // window.location.reload();
        }
    });
}

// 删除
function deletestudent(id) {
    if(confirm("确定要删除此学生信息???")){
        $.post("deletestudent", {id:id}, function (result) {
            alert("删除成功!!!");
            loadIndexpage();
            // window.location.reload();
            });
    }
}

// 修改
function modifystudent(student) {
    alert(student.name);
}

function previousdemo() {
    if(pageNo > 1) pageNo -= 1;
    var data = {pageNo:pageNo,pageSize:pageSize};
    $.post(
        "loadeasypoipage",
        data,
        function (result) {
            pages(result);
            showTable(result.list);
        }
    );
}

function nextdemo() {
    if(pageNo < totalPages) pageNo += 1;
    var data = {pageNo:pageNo,pageSize:pageSize};
    $.post(
        "loadeasypoipage",
        data,
        function (result) {
            pages(result);
            showTable(result.list);
        }
    );
}

function pages(result) {
    $("#pageNumber").val(result.pageNumber);
    $("#totalPages").val(result.totalPages);
    $("#totalElements").val(result.totalElements);
    totalPages = result.totalPages;
}


function dc() {
    if(confirm("确定要导出测试数据吗?")){
        window.location.href="/dc";
    }
}