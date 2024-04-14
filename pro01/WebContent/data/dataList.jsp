<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="path0" value="<%=request.getContextPath() %>" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title }</title>
<%@ include file="/head.jsp" %>
<script src="${path0 }/js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="${path0 }/css/jquery.dataTables.css" >
<style>
.container { width:1400px; }
.page { clear:both; height:100vh; }
#page1 { background-color:#ececec; }
#page2 { background-color:#42bcf5; }
.page_title { font-size:36px; padding-top:2em; text-align:center; }

th.item1 { width:8%; }
th.item2 { width:20%; }
th.item3 { width:40%; }
th.item4 { width:10%; }
th.item5 { width:14%; }
td.msg { text-align:center; }
</style>
</head>
<body>
<div id="header">
	<%@ include file="/header.jsp" %>
</div>
<div id="contents">
	<section class="page" id="page1">
		<div style="width:1400px; margin:0 auto;">
			<nav aria-label="breadcrumb" style="text-align:right">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="#">Home</a></li>
			    <li class="breadcrumb-item"><a href="${path0 }/GetDataList.do">자료실</a></li>
			    <li class="breadcrumb-item active" aria-current="page">자료실 목록</li>
			  </ol>
			</nav>
			<hr>
		</div>	
		<div style="width:1400px; margin:0 auto;">
			<h3 class="page_title">자료실 목록</h3>
			<div>
				<table class="table" id="tb1">
					<thead>
						<tr>
							<th class="item1">번호</th>
							<th class="item2">제목</th>
							<th class="item3">내용</th>
							<th class="item4">자료</th>
							<th class="item5">작성일</th>
							<th class="item6">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty dataList }">
							<c:forEach var="dto" items="${dataList }">
							<tr>
								<td>${dto.no }</td>
								<td>
									<c:if test="${empty sid }">
									<strong>${dto.title }</strong>
									</c:if>
									<c:if test="${not empty sid }">
									<a href="${path0 }/GetData.do?no=${dto.no }">${dto.title }</a>
									</c:if>
								</td>								
								<td>${dto.content }</td>
								<td>${dto.datafile }</td>
								<td>${dto.resdate }</td>
								<td>${dto.visited }</td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty dataList }">
							<tr>
								<td colspan="6" class="msg"><strong>데이터가 존재하지 않습니다.</strong></td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<script>
				$(document).ready(function(){
					$("#tb1").DataTable({
						order:[[0,"desc"]]
					});
				});
				</script>
				<hr>
				<c:if test="${sid.equals('admin') }">
				<div class="btn-group">
				  <a href="${path0 }/data/data_ins.jsp" class="btn btn-secondary">글 등록</a>
				</div>
				</c:if>
			</div>
		</div>
	</section>
	<section class="page" id="page2">
		<div style="width:1400px; margin:0 auto;">	
			<h3 class="page_title"></h3>

		</div>	
	</section>	
</div>
<div id="footer">
	<%@ include file="/footer.jsp" %>
</div>
</body>
</html>