<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/head.jsp"%>
<html>
<head>
    <%@include file="common/link.jsp"%>
    <title>用户管理</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="panel text-center">
                <h3>用户管理</h3>
            </div>
        </div>
        <div class="row" style="margin-bottom: 5px">
            <div class="col-md-6">
                <label>姓名：</label>
                <input id="name" value="${name}" type="text"/>
                <button onclick="query()" class="btn btn-default btn-sm">查询</button>
            </div>
            <div class="col-md-6 text-right">
                <button class="btn btn-default btn-sm" onclick="window.location.href='user/add'">添加</button>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover table-bordered table-striped">
                    <thead>
                    <tr>
                        <td class="text-center"><input type="checkbox" id="checkAllUser"/></td>
                        <td>主键</td>
                        <td>姓名</td>
                        <td>年龄</td>
                        <td class="text-center">操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${datas.rows}">
                        <tr>
                            <td class="text-center"><input type="checkbox" name="checkOneUser"/></td>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.age}</td>
                            <td class="text-center"><span><a href="user/${user.id}/edit">编辑</a></span>
                                <span><a href="user/${user.id}/delete">删除</a></span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="row">
                <div class="col-md-12 text-right">
                    <ul  id="userPages"></ul>
                </div>
            </div>
        </div>
    </div>
    <%@include file="common/script.jsp"%>
    <script type="text/javascript" src="resources/js/bootstrap-paginator.js"></script>
    <script type="text/javascript">
        $(function(){
            var options = {
                bootstrapMajorVersion: 3, //版本
                currentPage: "${datas.page}", //当前页数
                totalPages: "${datas.pageSize}", //总页数
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                },
                onPageClicked: function (event, originalEvent, type, page) {
                   window.location.href="user/list?start="+page+"&limit=${datas.limit}";
                }
            }
            $("#userPages").bootstrapPaginator(options);
            $("#checkAllUser").on("click",function(){
                if(this.checked){
                    $("input[name='checkOneUser']").each(function(){
                        this.checked=true;
                        $(this).parents("tr").attr("style","background-color:#DDEFFF;");
                    });
                }else{
                    $("input[name='checkOneUser']").each(function(){
                        this.checked=false;
                        $(this).parents("tr").removeAttr("style");
                    });
                }

            });
        });
        function query(){
            var name = $("#name").val();
            window.location.href="user/list?name="+name;
        }
    </script>
</body>
</html>
