package net.bdzcemms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;

import net.bdzcemms.dao.ZcbdDao;
import net.bdzcemms.model.Zcbd;
import net.bdzcemms.model.User;
/**
 * Servlet implementation class ZcbdServlet
 */
@WebServlet("/query")
public class ZcbdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ZcbdDao zcbdDao;
    private List<Zcbd> results = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public void ajaxRedirect(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setHeader("REDIRECT", "REDIRECT"); //表示重定向
            response.setHeader("CONTEXTPATH", request.getContextPath() +"/"+page); //重定向的路径
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); //拒绝访问.
        } else {
            response.sendRedirect(request.getContextPath() +"/"+page);
        }
    }
    
    public ZcbdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		zcbdDao = new ZcbdDao();
	}
    
    private void generateAttributes(HttpServletRequest request) {
    	// pageSize, pageIndex, pageCount
    	Integer recordSize = results.size();
    	Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
    	Integer pageIndex = Integer.valueOf(request.getParameter("pageIndex"));
    	Integer pageCount = recordSize / pageSize;
    	if (recordSize % pageSize > 0) {
    		pageCount += 1;
    	}
    	if (pageIndex < 1) {
    		pageIndex = 1;
    	}
    	if (pageIndex > pageCount) {
    		pageIndex = pageCount;
    	}
    	
    	Integer pageBegin = (pageIndex - 1) * pageSize + 1;
    	Integer pageEnd = pageBegin + pageSize - 1;
    	if (pageCount.equals(pageIndex) && recordSize % pageSize > 0) {
    		pageEnd = pageBegin + (recordSize % pageSize) - 1;
    	}
    	
    	if (recordSize.equals(0)) {
    		pageBegin = 0;
    		pageEnd = 0;
    	}
    	
    	request.setAttribute("recordSize", recordSize);
    	request.setAttribute("pageCount", pageCount);
    	request.setAttribute("pageBegin", pageBegin);
    	request.setAttribute("pageEnd", pageEnd);
    	request.setAttribute("pageIndex", pageIndex);
    	
    	Double totalJE = 0.0;
    	Integer totalNum = 0;
    	List<Zcbd> pageResults = new ArrayList<>();
    	Integer index = 0;
    	for (Iterator<Zcbd> iterator = results.iterator(); iterator.hasNext();) {
    		Zcbd record = iterator.next();
    		totalJE += record.getJe();
    		totalNum += record.getBdsl();
    		if (index >= pageBegin - 1 && index <= pageEnd - 1) {
    			pageResults.add(record);
    		}
    		index += 1;
    	}
    	request.setAttribute("results", pageResults);
    	request.setAttribute("totalJE", String.format("%.2f", totalJE));
    	request.setAttribute("totalNum", totalNum);
    }
    
    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<User> users = new ArrayList<>();
    	for (Iterator<Zcbd> iterator = results.iterator(); iterator.hasNext();) {
    		Zcbd record = iterator.next();
    		User user = new User();
    		user.setName(record.getSyr());
    		user.setNumber(record.getSyrbh());
    		users.add(user);
    	}
    	request.setAttribute("listUser", users);
    	request.getRequestDispatcher("user-table.jsp").forward(request, response);
    }

    private void generateExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	File file = new File("/home/john/eclipse-workspace/bdzcemms/WebContent/templates/template.xlsx");
   	  	//定义输入流对象
   	  	FileInputStream excelFileInputStream;
   	  
   	  	excelFileInputStream = new FileInputStream(file);
   	   // 拿到文件转化为JavaPoi可操纵类型
   	  	Workbook workbook = WorkbookFactory.create(excelFileInputStream);
   	  	excelFileInputStream.close();
    	String sheetName = request.getParameter("sheetname");
    	String fileName = request.getParameter("filename") + ".xlsx";
    	
    	System.out.println(sheetName + "," + fileName);
    	
    	Sheet sheet = workbook.getSheetAt(0);
    	CellAddress address = new CellAddress("A1");
	   // 获取行
    	Row row = sheet.getRow(address.getRow());
	   // 获取列
    	Cell cell = row.getCell(address.getColumn());
	   //设置单元的值
    	cell.setCellValue("中国化工大学" + sheetName);
    	
    	row = sheet.getRow(3);
    	cell = row.getCell(0);
    	CellStyle style = cell.getCellStyle();
    	
    	int rowNo = 3;
    	Double totalJE = 0.00;
    	Integer totalNum = 0;
    	for (Iterator<Zcbd> iterator = results.iterator(); iterator.hasNext();) {
    		Zcbd record = iterator.next();
    		String lydwm = record.getLydwm();
    		String zcbh = record.getZcbh();
    		String zcmc = record.getZcmc();
    		String ppxh = record.getPpxh();
    		String gg = record.getGg();
    		Double je = record.getJe();
    		Integer bdsl = record.getBdsl();
    		Double bddj = record.getBddj();
    		String bdrq = record.getBdrq();
    		String bdyy = record.getBdyy();
    		String zrdwm = record.getZrdwm();
    		int col = 0;
    		row = sheet.createRow(rowNo++);
    		cell = row.createCell(col++);
    		cell.setCellValue(lydwm); cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(zcbh);  cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(zcmc);  cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(ppxh + "/" + gg); cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(je); cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(bdsl);; cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(bddj); cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(bdrq); cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(bdyy); cell.setCellStyle(style);
    		cell = row.createCell(col++);
    		cell.setCellValue(zrdwm); cell.setCellStyle(style);
    		totalJE += je;
    		totalNum += bdsl;
    	}
    	
    	PropertyTemplate pt = new PropertyTemplate();
    	pt.drawBorders(new CellRangeAddress(2, 2 + results.size(), 0, 9), BorderStyle.MEDIUM, BorderExtent.OUTSIDE);
    	pt.drawBorders(new CellRangeAddress(2, 2 + results.size(), 0, 9), BorderStyle.THIN, BorderExtent.INSIDE);
    	pt.applyBorders(sheet);
    	row = sheet.createRow(rowNo);
    	CellStyle style1 = sheet.getRow(1).getCell(0).getCellStyle();
    	cell = row.createCell(0); cell.setCellStyle(style1);
    	Date date = new Date(System.currentTimeMillis());
    	cell.setCellValue("制表时间: " + date.toString());
    	cell = row.createCell(1);
    	
    	cell = row.createCell(2); cell.setCellStyle(style1);
    	cell.setCellValue("制表人: 李亮");
    	cell = row.createCell(3);
    	
    	cell = row.createCell(5); cell.setCellStyle(style1);
    	cell.setCellValue("总数量: " + totalNum.toString() + " 台/件");
    	cell = row.createCell(6);
    	
    	//CellStyle style2 = sheet.getRow(1).getCell(0).getCellStyle();
    	//style2.setAlignment(HorizontalAlignment.RIGHT);
    	cell = row.createCell(8); cell.setCellStyle(style1);
    	cell.setCellValue("总金额: " + String.format("%.2f", totalJE) + " 元");
    	cell = row.createCell(9);
    	
    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, 1));
    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 2, 3));
    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 5, 6));
    	sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 8, 9));
    	
    	response.setContentType("application/vnd.ms-excel");
    	response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
    	
    	workbook.write(response.getOutputStream());
    	workbook.close();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		String queryType = request.getParameter("queryType");
		System.out.println(queryType);
		
		if (queryType.equals("excel-table")) {
			generateExcel(request, response);
			return;
		}
		
		if (!queryType.equals("page") && !queryType.equals("user")) {
			results = new ArrayList<>();
			String constraits = new String();
			if (queryType.equals("basic")) {
				String reportType = request.getParameter("reportType");
				if (reportType.equals("0")) {
					constraits = "where Xz = \'B\' OR Xz = \'C\';";
				} else if (reportType.equals("1")) {
					constraits = "where Xz = \'6\';";
				}
				results = zcbdDao.queryZcbd(constraits);
			} else if (queryType.equals("common")) {
				String reportType = request.getParameter("reportType");
				if (reportType.equals("0")) {
					constraits = "where (Xz = \'B\' OR Xz = \'C\') ";
				} else if (reportType.equals("1")) {
					constraits = "where Xz = \'6\' ";
				}
				String zcflh = request.getParameter("zcflh");
				//System.out.println(zcflh);
				
				if (!zcflh.equals("00")) {
					constraits += "AND zcflh LIKE \'" + zcflh + "%\' ";
				}
				
				String Rzrq = request.getParameter("Rzrq");
				//System.out.println(Rzrq);
				
				String [] Rzrqs =  Rzrq.split(",");
				//System.out.println(Rzrqs.length);
				if (Rzrqs.length == 2) {
					if (!Rzrqs[0].equals("") && !Rzrqs[1].equals("")) {
						constraits += "AND Rzrq BETWEEN \'" + Rzrqs[0] + "\' AND \'" + Rzrqs[1] + "\' "; 
					}
				}
				
				String bdrq = request.getParameter("bdrq");
				//System.out.println(bdrq);
				
				String [] bdrqs =  Rzrq.split(",");
				System.out.println(bdrqs.length);
				if (bdrqs.length == 2) {
					if (!bdrqs[0].equals("") && !bdrqs[1].equals("")) {
						constraits += "AND bdrq BETWEEN \'" + bdrqs[0] + "\' AND \'" + bdrqs[1] + "\' "; 
					}
				}
				
				// TODO 使用单位
				
				// 使用人
				String syrbh = request.getParameter("SYR");
				if (!syrbh.equals("")) {
					constraits += "AND SYRBH = \'" + syrbh + "\' ";
				}
				
				// 使用方向
				String syfx = request.getParameter("syfx");
				if (!syfx.equals("0")) {
					constraits += "AND syfx = \'" + syfx + "\' ";
				}
				
				// TODO 转入单位
				
				String zcbh = request.getParameter("zcbh");
				//System.out.println(zcbh);
				
				String[] zcbhs = zcbh.split(",");
				//System.out.println(zcbhs.length);
				if (zcbhs.length == 2) {
					if (!zcbhs[0].equals("") && !zcbhs[1].equals("")) {
						constraits += "AND zcbh BETWEEN \'" + zcbhs[0] + "\' AND \'" + zcbhs[1] + "\' ";
					}
				}
				
				String zcmc = request.getParameter("zcmc");
				//System.out.println(zcmc);
				
				String[] zcmcs = zcmc.split(",");
				if (zcmcs.length == 2) {
					if (!zcmcs[1].equals("")) {
						if (zcmcs[0].equals("0")) {
							constraits += "AND zcmc LIKE \'" + zcmcs[1] + "%\' ";
						} else if (zcmcs[0].equals("1")) { 
							constraits += "AND zcmc LIKE \'%" + zcmcs[1] + "\'";
						} else if (zcmcs[0].equals("2")) {
							constraits += "AND zcmc LIKE \'%" + zcmcs[1] + "%\'";
						}
					}
				}
				
				constraits += ";";
				System.out.println(constraits);
				results = zcbdDao.queryZcbd(constraits);
			} else if (queryType.equals("bunch") || queryType.equals("special")) {
				String reportType = request.getParameter("reportType");
				if (reportType.equals("0")) {
					constraits = "where (Xz = \'B\' OR Xz = \'C\') AND ";
				} else if (reportType.equals("1")) {
					constraits = "where Xz = \'6\' AND ";
				}
				constraits += request.getParameter("condition");
				constraits += ";";
				System.out.println(constraits);
				results = zcbdDao.queryZcbd(constraits);
			}
		}
		if (queryType.equals("user")) {
			getUsers(request, response);
			return;
		}
		generateAttributes(request);
		request.getRequestDispatcher("table.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
