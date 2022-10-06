<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST" />
<%@ include file="../common/head.jspf"%>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdn.tailgrids.com/tailgrids-fallback.css" />
<script defer src="https://unpkg.com/alpinejs@3.x.x/dist/cdn.min.js"></script>

<section class="bg-white py-20 ">
	<div class="container">
		<div class="flex flex-wrap -mx-4">
			<div class="w-full px-4">
				<div class="max-w-full overflow-x-auto">
					<table class="table-auto w-full">
						<thead>
							<tr class="bg-primary text-center">
								<th	class="  w-1/6 min-w-[160px] text-lg font-semibold text-white  py-4 lg:py-7  px-3 lg:px-4">
								번호
								</th>
								<th	class="  w-1/6 min-w-[160px] text-lg font-semibold text-white  py-4 lg:py-7  px-3 lg:px-4">
								날짜
								</th>
								<th	class="  w-1/6 min-w-[160px] text-lg font-semibold text-white  py-4 lg:py-7  px-3 lg:px-4">
								제목
								</th>
								<th	class="  w-1/6 min-w-[160px] text-lg font-semibold text-white  py-4 lg:py-7  px-3 lg:px-4">
								작성자
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="article" items="${articles}">
								<tr>
									<td	class=" text-center text-dark font-medium text-base py-5 px-2 bg-[#F3F6FF] border-b border-[#E8E8E8]">
									${article.id}
									</td>
									<td class="text-center text-dark font-medium text-base py-5 px-2 bg-[#F3F6FF] border-b border-[#E8E8E8]">
									${article.regDate.substring(0,10)}
									</td>
									<td	class=" text-center text-dark font-medium text-base py-5 px-2 bg-[#F3F6FF] border-b border-[#E8E8E8] hover:text-blue-700"
									onClick="location.href='../article/getArticle?id=${article.id}'" style="cursor:pointer;">
									${article.title}
									</td>
									<td class="text-center text-dark font-medium text-base py-5 px-2 bg-[#F3F6FF] border-b border-[#E8E8E8]">
									${article.loginedId}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>