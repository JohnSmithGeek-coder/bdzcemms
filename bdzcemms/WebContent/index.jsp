<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html id="index">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="javascripts/main.js"></script>
        <script src="javascripts/setAjax.js"></script>
        <title>变动资产明细表打印</title>
        <style>
            .panel-heading .accordion-toggle:after {
                font-family: 'Glyphicons Halflings';
                content: "\e114";
                float: right;
                color: white;
            }
            .panel-heading .accordion-toggle.collapsed:after {
                content: "\e080";
            }
            th {
                min-width: 120px;
                text-align: left;
            }
            .check-col{
            	min-width: 30px;
            }
            td {
            	text-align: left;
            }
        </style>
    </head>
    <body>
        <div style="display: flex; margin: 30px;">
            <div class="panel panel-primary" style="width: 30%;">
                <div class="panel-heading">
                    <h4 class="panel-title">报表查询</h4>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">请选择报表类型</label>
                            <div class="col-sm-9">
                                <select id="reportTypeSelect" class="form-control">
                                    <option value=""></option>
                                    <option value="0">资产报废明细表</option>
                                    <option value="1">校内调拨明细表</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse1">常用条件</a>
                                </h4>
                            </div>
                            <div id="collapse1" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <form class="form-horizontal">
                                        
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">设备所属分类</label>
                                            <div class="col-sm-9">
                                                <select id="equipCate" class="form-control" value="04">
                                                    <option value="00">全部</option>
                                                    <option value="01">房屋及构筑物</option>
                                                    <option value="02">土地及植物</option>
                                                    <option value="03">仪器仪表</option>
                                                    <option value="04">机电设备</option>
                                                    <option value="05">电子设备</option>
                                                    <option value="06">印刷机械</option>
                                                    <option value="07">卫生医疗器械</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">资产入账日期</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-5" style="padding-left: 0px; padding-right: 0px;">
                                                    <input id="equipEaStartDate" type="date" class="form-control" value="2015-01-01">
                                                </div>
                                                <div class="col-sm-2"><hr></div>
                                                <div class="col-sm-5" style="padding-right: 0px; padding-left: 0px;">
                                                    <input id="equipEaEndDate" type="date" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">资产变动日期</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-5" style="padding-left: 0px; padding-right: 0px;">
                                                    <input id="equipChangeStartDate" type="date" class="form-control" value="2015-01-01">
                                                </div>
                                                <div class="col-sm-2"><hr></div>
                                                <div class="col-sm-5" style="padding-right: 0px; padding-left: 0px;">
                                                    <input id="equipChangeEndDate" type="date" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">使用单位</label>
                                            <div class="col-sm-9">
                                                <select id="useDepartSelect" class="form-control">
                                                    <option value="0">全部</option>
                                                    <!-- TODO -->
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">使用人</label>
                                            <div class="col-sm-9">
                                            	<div class="input-group">
													<input id="syr" number="" type="text" class="form-control" aria-label="Text input with segmented button dropdown" disabled>
													<div class="input-group-btn">
														<button id="user-clear" class="btn btn-default"><span class="glyphicon glyphicon-trash"></span></button>
														<button  id="userSearchBtn" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
															&nbsp;<span class="caret"></span>
															<span class="sr-only">Toggle Dropdown</span>
														</button>
														<div id="userSaerchDropDown" class="dropdown-menu dropdown-menu-right" style="overflow-y: auto; max-height: 350px;">
															<input id="userSearchInput" onkeyup="myFunction()" type="search" class="form-control" placeholder="Search for names..">
															
															<table id="user-table" class="table table-responsive table-striped">
											                    <thead>
											                        <tr>
											                            <th>使用人编号</th>
											                            <th>使用人</th>
											                        </tr>
                    											</thead>
                    											<tbody>
                    												<tr> 
                    													
                    												</tr>
                    											</tbody>
											                </table>
														</div>
													</div>
												</div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">使用方向</label>
                                            <div class="col-sm-9">
                                                <select id="useDerictionSelect" class="form-control">
                                                    <option value="0">全部</option>
                                                    <option value="1">教学</option>
                                                    <option value="2">科研</option>
                                                    <option value="3">行政</option>
                                                    <option value="4">生活与后勤</option>
                                                    <option value="5">生产</option>
                                                    <option value="6">技术开发</option>
                                                    <option value="7">社会服务</option>
                                                    <option value="9">其他</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">调入单位</label>
                                            <div class="col-sm-9">
                                                <select id="toDepartSelect" class="form-control">
                                                    <option value="0">全部</option>
                                                    <!-- TODO -->
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">资产编号</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-5" style="padding-left: 0px; padding-right: 0px;">
                                                    <input id="equipNumStart" type="text" class="form-control">
                                                </div>
                                                <div class="col-sm-2"><hr></div>
                                                <div class="col-sm-5" style="padding-right: 0px; padding-left: 0px;">
                                                    <input id="equipNumEnd" type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">资产名称</label>
                                            <div class="col-sm-9">
                                                <div class="col-sm-5" style="padding-left: 0px; padding-right: 0px;">
                                                    <select id="equipNameQType" class="form-control">
                                                        <option value="0">前缀</option>
                                                        <option value="1">后缀</option>
                                                        <option value="2">包含</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-2"><hr></div>
                                                <div class="col-sm-5" style="padding-right: 0px; padding-left: 0px;">
                                                    <input id="equipNameSub" type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4"></div>
                                            <div class="col-sm-4" style="text-align: right;">
                                                <button id="common-reset" type="button" class="btn btn-danger" style="width: 70%;">重置</button>
                                            </div>
                                            <div class="col-sm-4" style="text-align: right;">
                                                <button id="commonQueryBtn" type="button" class="btn btn-success" style="width: 70%;">查询🔍</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse2">批量查询</a>
                                </h4>
                            </div>
                            <div id="collapse2" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" style="text-align: left;">&nbsp;请选择查询字段</label>
                                            <div class="col-sm-8">
                                                <select id="bunchSelect" class="form-control">
                                                    <option>设备编号</option>
													<option>设备名称</option>
													<option>设备分类号</option>
													<option mytype="numerical">变动数量</option>
													<option mytype="numerical">金额</option>
													<option>厂家</option>
													<option>供货商</option>
													<option>发票号</option>
													<option>现状</option>
													<option>使用单位号</option>
													<option>使用单位</option>
													<option>使用人编号</option>
													<option>使用人</option>
													<option>使用方向</option>
													<option>购置日期</option>
													<option>单据号</option>
													<option>审核状态</option>
													<option>变动原因</option>
													<option>变动日期</option>
													<option mytype="numerical">变动单价</option>
													<option>调入单位号</option>
													<option>调入单位</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin: 5px;">
                                            <label for="comment" class="control-label">请填入批量查询的内容，以逗号进行分隔</label>
                                            <textarea class="form-control" rows="8" id="bunch-input" style="margin-top:10px;"></textarea>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4"></div>
                                            <div class="col-sm-4" style="text-align: right;">
                                                <button id="bunch-reset" type="button" class="btn btn-danger" style="width: 70%;">重置</button>
                                            </div>
                                            <div class="col-sm-4" style="text-align: right;">
                                                <button id="bunch-search" type="button" class="btn btn-success" style="width: 70%;">查询🔍</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse3">特殊查询</a>
                                </h4>
                            </div>
                            <div id="collapse3" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <select id="conSelect" class="form-control">
                                                    <option>设备编号</option>
													<option>设备名称</option>
													<option>设备分类号</option>
													<!-- 数值 -->
													<option mytype="numerical">变动数量</option>
													<!-- 数值 -->
													<option mytype="numerical">金额</option>
													<option>厂家</option>
													<option>供货商</option>
													<option>发票号</option>
													<option>现状</option>
													<option>使用单位号</option>
													<option>使用单位</option>
													<option>使用人编号</option>
													<option>使用人</option>
													<option>使用方向</option>
													<option>购置日期</option>
													<option>单据号</option>
													<option>审核状态</option>
													<option>变动原因</option>
													<option>变动日期</option>
													<option mytype="numerical">变动单价</option>
													<option>调入单位号</option>
													<option>调入单位</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <select id="opSelect" class="form-control">
                                                	<option value="=">等于</option>
                                                    <option value="LIKE">包含</option>
                                                    <option value="<=>">绝对等于</option>
												    <option value="!=">不等于</option>
												    <option value=">=">大于等于</option>
												    <option value=">">大于</option>
												    <option value="<=">小于等于</option>
												    <option value="<">小于</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <input id="searchField" type="text" class="form-control" style="overflow-x:auto;">
                                            </div>
                                            
                                            <div class="col-sm-2">
                                                <button id="addBtn" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span></button>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                       		<label class="col-sm-3 control-label" style="text-align:left;">&nbsp;逻辑运算符</label>
                                       		<div class="col-sm-9">
                                       		<div class="btn-group">
                                       			<button id="bracketBtn" type="button" class="btn btn-warning">括号()</button>
	                                            <button id="bracketLeftBtn" type="button" class="btn btn-warning">(</button>
	                                            <button id="bracketRightBtn" type="button" class="btn btn-warning">)</button>
	                                            <button id="andBtn" type="button" class="btn btn-warning">与 AND</button>
	                                            <button id="orBtn" type="button" class="btn btn-warning">或 OR</button>
	                                            <button id="notBtn" type="button" class="btn btn-warning">非 NOT</button>
                                       		</div>
	                                                 
                                        	</div>
                                        </div>
                                        
                                        
                                        <div class="form-group" style="margin: 5px;">
                                            <label for="condition-textarea" class="control-label">当前条件</label>
                                            <!-- textarea class="form-control" rows="8" id="condition-textarea" style="margin-top:10px;"></textarea -->
                                            <div contenteditable="true" id="condition-iptdiv" style="background-color:floralwhite; height:200px; padding: 10px;"></div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4"></div>
                                            <div class="col-sm-4" style="text-align: right;">
                                                <button id="special-reset" type="button" class="btn btn-danger" style="width: 70%;">重置</button>
                                            </div>
                                            <div class="col-sm-4" style="text-align: right;">
                                                <button id="special-search" type="button" class="btn btn-success" style="width: 70%;">查询🔍</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
                                <li id="previousPage"><a href="#">«</a></li>
                                <li id="nextPage"><a href="#">»</a></li>
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
            </div>
        </div>
    </body>
</html>