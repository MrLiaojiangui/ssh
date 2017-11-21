<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>无标题文档</title>

	<link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
	<%-- 点击高级查询, 也走查询所有的 action --%>
	<script>
		function onDeptSelected(value) {
			//输出value的值
			console.log(value)
			//根据value的值发送请求.
			//获取二级列表的json数据
			var data = new FormData();
			data.append("depId", value);

			var xhr = new XMLHttpRequest();
			xhr.withCredentials = true;

			xhr.addEventListener("readystatechange", function () {
				if (this.readyState === 4) {
					console.log(this.responseText);
					//对请求回来的数据进行解析
					json = eval('(' + this.responseText + ')');
					//获取职务的标签
					postSelect = document.getElementById("postSelectId");
					//获取option标签
					optionEle = postSelect.getElementsByTagName("option");
					//获取option的数量
					length = optionEle.length;
					//使用循环清空所有的option标签
					for (var i = 0; i < length; i++) {
						postSelect.removeChild(optionEle[0]);
					}
					postSelect.innerHTML = "<option value = '-1'>--选择职位--</option>";
					//将json数据插入到option中
					for (var i = 0; i < json.length; i++) {
						//创建一个option标签
						option = document.createElement("option")
						//设置value属性
						option.setAttribute("value", json[i].postId);
						//创建文本
						text = document.createTextNode(json[i].postName)
						//把文本信息添加到option中
						option.appendChild(text);
						//把option标签添加到servers的select中
						postSelect.appendChild(option);
					}
				}
			});
			xhr.open("POST", "${pageContext.request.contextPath}/staff/getPostByDepId.action");
			xhr.send(data);
		}
		function
		condition() {

			var deptId = document.getElementById("depId").value;
			var postId = document.getElementById("postSelectId").value;
			var staffName = document.getElementById("staffName").value;
			var data = new FormData();

			data.append("model.post.department.depId", deptId);
			data.append("model.post.postId", postId);
			data.append("staffName", staffName);

			var xhr = new XMLHttpRequest();
			xhr.withCredentials = true;

			xhr.addEventListener("readystatechange", function () {
				if (this.readyState === 4) {
					console.log(this.responseText);
					json = eval('(' + this.responseText + ')');

					var tableEle = document.getElementById("staffId");
					var length = tableEle.rows.length;
					for (var j = 0; j < length - 1; j++) {
						tableEle.deleteRow(1);
					}

					for (var j = 0; j < json.length; j++) {
						var tr = document.createElement("tr");
						getId = json[j].staffId;

						tr.appendChild(createTD(json[j].staffName));
						tr.appendChild(createTD(json[j].gender));
						tr.appendChild(createTD(json[j].onDutyDate));
						tr.appendChild(createTD(json[j].post.department.depName));
						tr.appendChild(createTD(json[j].post.postName));

						function createA() {
							var get_Id = json[j].staffId;
							var path = "${pageContext.request.contextPath}/staff/staffAction_editStaffPre?staffId=";
							path += get_Id;

							var td = document.createElement("td");
							td.setAttribute("align", "center");
							var a = document.createElement("a");
							a.setAttribute("href", path);
							var textNode = document.createElement("img");
							textNode.setAttribute("src", "${pageContext.request.contextPath}/images/button/modify.gif")
							textNode.setAttribute("class", "img");
							a.appendChild(textNode);
							td.appendChild(a);
							return td;
						}

						tr.append(createA());
						tableEle.appendChild(tr)
					}

					function createTD(text) {
						var td = document.createElement("td");
						td.setAttribute("align", "center")
						var textNode = document.createTextNode(text);
						td.appendChild(textNode);
						return td;
					}
				}
			});
			xhr.open("POST", "${pageContext.request.contextPath}/staff/getAllResult.action");
			xhr.send(data);
		}


	</script>

	<%--<script type="text/javascript">--%>
		<%--function condition() {--%>
			<%--document.getElementById("conditionFormId").submit();--%>
		<%--}--%>
	<%--</script>--%>


</head>

<body >
<table border="0" cellspacing="0" cellpadding="0" width="100%">
	<tr>
		<td class="topg"></td>
	</tr>
</table>

<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">
	<tr>
		<td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
		<td width="39%" align="left">[员工管理]</td>

		<td width="57%"align="right">
			<%--高级查询 --%>
			<a href="javascript:void(0)" onclick="condition()">
				<img src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif" /></a>
			<%--员工注入 --%>
			<a href="${pageContext.request.contextPath}/staff/findAllDept.action">
				<img src="${pageContext.request.contextPath}/images/button/tianjia.gif" />
			</a>

		</td>
		<td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
	</tr>
</table>

<!-- 查询条件：马上查询 -->
<form id="conditionFormId" action="${pageContext.request.contextPath}/staff/queryStaff.action" method="post">
	<table width="88%" border="0" style="margin: 20px;" >
		<tr>
			<td width="80px">部门：</td>
			<td width="200px">

				<select name="depId" onchange="onDeptSelected(value)" id="depId" >
					<option value="">--请选择部门--</option>
					<s:iterator value="allDept" var="dept">
						<option value="${dept.depId}">${dept.depName}</option>

					</s:iterator>
				</select>

			</td>
			<td width="80px">职务：</td>
			<td width="200px" >

				<select id="postSelectId" name="postId">
					<option value="">--请选择职务--</option>


				</select>

			</td>
			<td width="80px">姓名：</td>
			<td width="200px" ><input type="text" name="staffName" size="12" id="staffName"/></td>
			<td ></td>
		</tr>
	</table>
</form>


<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
	<tr>
		<td ><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
	</tr>
</table>

<table width="100%" border="1" id="staffId">
	<tr class="henglan" style="font-weight:bold;">
		<td width="10%" align="center">员工姓名</td>
		<td width="6%" align="center">性别</td>
		<td width="12%" align="center">入职时间</td>
		<td width="15%" align="center">所属部门</td>
		<td width="10%" align="center">职务</td>
		<td width="10%" align="center">编辑</td>
	</tr>

	<s:iterator value="staffs">
		<tr class="table">
			<td align="center"><s:property value="staffName"/></td>
			<td align="center"><s:property value="gender"/></td>
			<td align="center"><s:property value="onDutyDate"/></td>
			<td align="center"><s:property value="post.department.depName"/></td>
			<td align="center"><s:property value="post.postName"/></td>
			<td width="7%" align="center">
					<%--之后编辑放开--%>
				<s:a action="staffAction_editStaffPre">
					<s:param name="staffId" value="staffId"/>
					<img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
				</s:a>
			</td>
		</tr>
	</s:iterator>




</table>



<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right">
			<span>第1/3页</span>
			<span>
        	<a href="#">[首页]</a>&nbsp;&nbsp;
            <a href="#">[上一页]</a>&nbsp;&nbsp;
            <a href="#">[下一页]</a>&nbsp;&nbsp;
            <a href="#">[尾页]</a>
        </span>
		</td>
	</tr>
</table>

</body>
</html>
