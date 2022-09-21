<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="data-table" class="panel panel-success" style="width: 70%; height: 100%; margin-left: 20px;">
    <div class="panel-heading" style="display: flex;">
        <h3 class="panel-title" style="padding: 6px; font-weight: bold;">资产信息</h3>
        <a id="pdfPreview" class="btn btn-primary btn-sm" style="margin-left: 50px;">
            <span class="glyphicon glyphicon-print"></span> 打印预览 
        </a>
    </div>
    <div class="panel-body" style="padding-bottom: 0px; margin-bottom: 0px;">
        <div style="overflow-x: auto;">
            <table class="table table-responsive table-striped">
                <thead>
                <tr>
                    <th class="order-th" order="ASC">设备编号</th>
					<th class="order-th" order="ASC">设备名称</th>
					<th class="order-th" order="ASC">设备分类号</th>
					<th class="order-th" order="ASC">品牌型号</th>
					<th class="order-th" order="ASC">规格</th>
					<th class="order-th" order="ASC">变动数量</th>
					<th class="order-th" order="ASC">金额</th>
					<th class="order-th" order="ASC">厂家</th>
					<th class="order-th" order="ASC">供货商</th>
					<th class="order-th" order="ASC">发票号</th>
					<th class="order-th" order="ASC">现状</th>
					<th class="order-th" order="ASC">使用单位号</th>
					<th class="order-th" order="ASC">使用单位</th>
					<th class="order-th" order="ASC">使用人编号</th>
					<th class="order-th" order="ASC">使用人</th>
					<th class="order-th" order="ASC">使用方向</th>
					<th class="order-th" order="ASC">存放地编号</th>
					<th class="order-th" order="ASC">存放地名称</th>
					<th class="order-th" order="ASC">经手人</th>
					<th class="order-th" order="ASC">购置日期</th>
					<th class="order-th" order="ASC">单据号</th>
					<th class="order-th" order="ASC">记账人</th>
					<th class="order-th" order="ASC">入账日期</th>
					<th class="order-th" order="ASC">审核状态</th>
					<th class="order-th" order="ASC">输入人编号</th>
					<th class="order-th" order="ASC">输入人</th>
					<th class="order-th" order="ASC">输入日期</th>
					<th class="order-th" order="ASC">变动原因</th>
					<th class="order-th" order="ASC">变动日期</th>
					<th class="order-th" order="ASC">变动单价</th>
					<th class="order-th" order="ASC">申请人编号</th>
					<th class="order-th" order="ASC">申请人</th>
					<th class="order-th" order="ASC">调入单位号</th>
					<th class="order-th" order="ASC">调入单位</th>
					<th class="order-th" order="ASC">备注</th>
                </tr>
                </thead>
                <tbody>
                	<c:forEach var="record" items="${results}">
                     <tr>
                         <td><c:out value="${record.zcbh}" /></td>
                         <td><c:out value="${record.zcmc}" /></td>
                         <td><c:out value="${record.zcflh}"/></td>
                         <td><c:out value="${record.ppxh}"/></td>
                         <td><c:out value="${record.gg}"/></td>
                         <td><c:out value="${record.bdsl}"/></td>
                         <td><c:out value="${record.je}"/></td>
                         <td><c:out value="${record.cj}" /></td>
                         <td><c:out value="${record.ghs}"/></td>
                         <td><c:out value="${record.fph}"/></td>
                         <td><c:out value="${record.xz}"/></td>
                         <td><c:out value="${record.lydwh}"/></td>
                         <td><c:out value="${record.lydwm}"/></td>
                         <td><c:out value="${record.syrbh}"/></td>
                         <td><c:out value="${record.syr}"/></td>
                         <td><c:out value="${record.syfx}"/></td>
                         <td><c:out value="${record.cfdbh}"/></td>
                         <td><c:out value="${record.cfdmc}"/></td>
                         <td><c:out value="${record.jsr}"/></td>
                         <td><c:out value="${record.ggrq}"/></td>
                         <td><c:out value="${record.djh}"/></td>
                         <td><c:out value="${record.jzr}"/></td>
                         <td><c:out value="${record.rzrq}"/></td>
                         <td><c:out value="${record.shzt}"/></td>
                         <td><c:out value="${record.srrbh}"/></td>
                         <td><c:out value="${record.srr}"/></td>
                         <td><c:out value="${record.srrq}"/></td>
                         <td><c:out value="${record.bdyy}"/></td>
                         <td><c:out value="${record.bdrq}"/></td>
                         <td><c:out value="${record.bddj}"/></td>
                         <td><c:out value="${record.sqrbh}"/></td>
                         <td><c:out value="${record.sqr}"/></td>
                         <td><c:out value="${record.zrdwh}"/></td>
                         <td><c:out value="${record.zrdwm}"/></td>
                         <td><c:out value="${record.bz}"/></td>
                     </tr>
                	</c:forEach> 
                </tbody>
                
            </table>
        </div>
    </div>
    <div class="panel-footer" style="padding-top: 0px; padding-bottom: 0px;">
        <div class="row">
            <div class="col-sm-4">
                <ul class="pagination">
                    <li id="previousPage"><a>«</a></li>
                     <c:forEach var="i" begin="1" end="${pageCount}">
                         <c:choose>
                             <c:when test="${pageIndex == i}">
                                 <li class="active"><a href="#"><c:out value="${i}"/></a></li>
                             </c:when>
                             <c:otherwise>
                                 <li class="passive"><a href="#"><c:out value="${i}"/></a></li>
                             </c:otherwise>
                         </c:choose>
                     </c:forEach>
                    <li id="nextPage"><a>»</a></li>
                </ul>
            </div>

            <div class="col-sm-2" style="padding-top: 24px; font-size: larger;">
                <span>数量(件/套): </span><span style="color:dodgerblue; font-weight: bold;"><c:out value="${totalNum}"/></span>
            </div>

            <div class="col-sm-2" style="padding-top: 24px; font-size: larger;">
                <span>金额(元): </span><span style="color:tomato; font-weight: bold;">￥<c:out value="${totalJE}"/></span>
            </div>

            <div class="col-sm-4" style="padding-top: 24px; font-size: larger; text-align: right;">
                当前显示第<span style="color: green; font-weight: bold;"><c:out value="${pageBegin}"/>-<c:out value="${pageEnd}"/></span>条记录,共<span style="color: green; font-weight: bold;"><c:out value="${recordSize}"/></span>条记录
            </div>

        </div>
    </div>
    <script src="javascripts/table.js"></script>
</div>
        