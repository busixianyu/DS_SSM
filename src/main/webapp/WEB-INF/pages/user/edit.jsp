<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/head.jsp"%>
<html>
<head>
    <%@include file="../common/link.jsp"%>
    <title>${title}</title>
</head>
<body>
  <div class="container">
    <div class="panel panel-primary">
      <div class="panel-heading text-center">
        <h3 class="panel-title">${title}</h3>
      </div>
      <div class="panel-body">
        <form class="form-horizontal" id="userForm" action="user/save" method="post">
          <div class="form-group">
           <input type="hidden" name="id" value="${user.id}"/>
          </div>
          <div class="form-group">
            <label for="name" class="col-sm-2 control-label">姓名：</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="请输入姓名" tip/>
              <form:errors path="user.name" cssStyle="color:red;"></form:errors>
            </div>
          </div>
          <div class="form-group">
            <label for="age" class="col-sm-2 control-label">年龄：</label>
            <div class="col-sm-10">
              <input type="number" class="form-control" id="age" name="age" value="${user.age}" placeholder="年龄"/>
              <form:errors path="user.age" cssStyle="color:red;"></form:errors>
            </div>
          </div>
        </form>
      </div>
      <div class="panel-footer text-center">
        <button type="submit" class="btn btn-primary" onclick="save()">确定</button>
        <button class="btn btn-default" onclick="window.location.href='user/list';">取消</button>
      </div>
    </div>
  </div>
  <%@include file="../common/script.jsp"%>
  <script type="text/javascript" src="resources/js/jquery.validate-1.13.1.js"></script>
  <script type="text/javascript">
    $(function(){
      $("#userForm").validate({
        rules:{
          name:{
            required:true,
            minlength:2,
            maxlength:20,
            remote:{
              url:"user/findByName",
              type:"get",
              data:{
                id:function(){
                  return ${id};
                }
              }
            }
          },
          age:{
            required:true,
            min:1,
            max:200,
            number:true,
            digits:true
          }
        },
        messages:{
          name:{
            required:"姓名不能为空！",
            minlength:"姓名最小为1位！",
            maxlength:"姓名最大为20位！",
            remote:"用户名已存在！"

          },
          age:{
            required:"年龄不能为空！",
            min:"年龄最小值不能小于1岁！",
            max:"年龄最大值不能大于200岁！",
            number:"填写内容必须为数字！",
            digits:"必须输入整数！"
          }
        }
      });
    });
    function save(){
      $("#userForm").submit();
    }
  </script>
</body>
</html>
