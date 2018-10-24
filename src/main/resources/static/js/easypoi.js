$(function () {
    loadIndex();
    $("#addeasypoi").click(addeasypoi);
});

function loadIndex() {
    $.getJSON(
        "loadeasypoi",
        function (result) {
            showTable(result);
        }
    );
}

function showTable(list) {
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
                '<button type="button" class="btn btn-primary">修改</button>&nbsp;&nbsp;' +
                '<button type="button" class="btn btn-primary">删除</button>' +
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
    '<button type="button" class="btn btn-primary">修改</button>&nbsp;&nbsp;' +
    '<button type="button" class="btn btn-primary">删除</button>' +
    '</td>' +
    '</tr>'

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
                    window.location.reload();
                });
            });
        }
    }
}