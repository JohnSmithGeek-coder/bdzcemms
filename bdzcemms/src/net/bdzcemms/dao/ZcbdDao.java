package net.bdzcemms.dao;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import net.bdzcemms.model.Zcbd;

public class ZcbdDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/EMMS?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Lyc200101168315#";
	
	private static final Map<String, String> XZMAP = new HashMap<String, String>();
	private static final Map<String, String> SYFXMAP = new HashMap<String, String>();
	
	static {
		XZMAP.put("1", "在用");
		XZMAP.put("2", "不需用（闲置");
		XZMAP.put("3", "毁坏不能用（待修");
		XZMAP.put("4", "待报废");
		XZMAP.put("5", "丢失");
		XZMAP.put("6", "报废");
		XZMAP.put("7", "出售出让转让");
		XZMAP.put("8", "精仪降档");
		XZMAP.put("9", "其它");
		XZMAP.put("A", "校外调入");
		XZMAP.put("B", "校内转入");
		XZMAP.put("C", "校内转出");
		XZMAP.put("D", "注销");
		XZMAP.put("E", "盘亏");
		XZMAP.put("F", "调剂");
		XZMAP.put("G", "对外捐赠");
		XZMAP.put("H", "置换");
		XZMAP.put("I", "未使用");
		XZMAP.put("J", "未入财务账");
		XZMAP.put("P", "待审批报废");
		XZMAP.put("Q", "待审批盘亏");
		XZMAP.put("R", "待审批无偿划拨（划转）");
		XZMAP.put("S", "待审批出售");
		XZMAP.put("T", "待审批出让");
		XZMAP.put("U", "待审批转让");
		XZMAP.put("V", "待审批置换");
		XZMAP.put("W", "待审批货币性设备核销");
		XZMAP.put("X", "待审批对外捐赠");
		
		SYFXMAP.put("1", "教学");
		SYFXMAP.put("2", "科研");
		SYFXMAP.put("3", "行政");
		SYFXMAP.put("4", "生活与后勤");
		SYFXMAP.put("5", "生产");
		SYFXMAP.put("6", "技术开发");
		SYFXMAP.put("7", "社会服务");
		SYFXMAP.put("9", "其它");
		SYFXMAP.put("A", "借出");
		SYFXMAP.put("B", "出租");
		SYFXMAP.put("C", "对外投资");
		SYFXMAP.put("D", "设备担保");
		SYFXMAP.put("E", "经营");
	}
	
	private static final String QUERY_SQL_PREFIX = "select * from zcbdb ";
	
	public ZcbdDao() {}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public List<Zcbd> queryZcbd(String constraits) {
		String queryStatement = QUERY_SQL_PREFIX + constraits;
		System.out.println(queryStatement);
		List<Zcbd> zcbds = new ArrayList<>();
		try (Connection connection = getConnection();
			Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(queryStatement);
			while (rs.next()) {
				String lydwh = rs.getString("lydwh");
				String lydwm = rs.getString("lydwm");
				String zcbh = rs.getString("zcbh");
				String zcflh = rs.getString("zcflh");
				String zcmc = rs.getString("zcmc");
				String ppxh = rs.getString("ppxh");
				String gg = rs.getString("gg");
				Double je = rs.getDouble("JE");
				Integer bdsl = rs.getInt("bdsl");
				String cj = rs.getString("Cj");
				String ggrq = rs.getString("Ggrq");
				String xz = rs.getString("Xz");
				String syfx = rs.getString("syfx");
				String fph = rs.getString("fph");
				String ghs = rs.getString("ghs");
				String cfdbh = rs.getString("cfdbh");
				String cfdmc = rs.getString("cfdmc");
				String syrbh = rs.getString("SYRBH");
				String syr = rs.getString("SYR");
				String jsr = rs.getString("JSR");
				String shzt = rs.getString("SHZT");
				String jzr = rs.getString("jzr");
				String rzrq = rs.getString("Rzrq");
				String bz = rs.getString("Bz");
				String djh = rs.getString("Djh");
				String srrbh = rs.getString("srrbh");
				String srr = rs.getString("srr");
				String srrq = rs.getString("srrq");
				String bdrq = rs.getString("bdrq");
				String bdyy = rs.getString("bdyy");
				String zrdwh = rs.getString("zrdwh");
				String zrdwm = rs.getString("zrdwm");
				Double bddj = rs.getDouble("bddj");
				String sqrbh = rs.getString("sqrbh");
				String sqr = rs.getString("sqr");
				
				Zcbd zcbd = new Zcbd();
				zcbd.setLydwh(lydwh);
				zcbd.setLydwm(lydwm);
				zcbd.setZcbh(zcbh);
				zcbd.setZcflh(zcflh);
				zcbd.setZcmc(zcmc);
				zcbd.setPpxh(ppxh);
				zcbd.setGg(gg);
				zcbd.setJe(je);
				zcbd.setBdsl(bdsl);
				zcbd.setCj(cj);
				zcbd.setGgrq(ggrq);
				zcbd.setXz(XZMAP.get(xz));
				zcbd.setSyfx(SYFXMAP.get(syfx));
				zcbd.setFph(fph);
				zcbd.setGhs(ghs);
				zcbd.setCfdbh(cfdbh);
				zcbd.setCfdmc(cfdmc);
				zcbd.setSyrbh(syrbh);
				zcbd.setSyr(syr);
				zcbd.setJsr(jsr);
				zcbd.setShzt(shzt);
				zcbd.setJzr(jzr);
				zcbd.setRzrq(rzrq);
				zcbd.setBz(bz);
				zcbd.setDjh(djh);
				zcbd.setSrrbh(srrbh);
				zcbd.setSrr(srr);
				zcbd.setSrrq(srrq);
				zcbd.setBdrq(bdrq);
				zcbd.setBdyy(bdyy);
				zcbd.setZrdwh(zrdwh);
				zcbd.setZrdwm(zrdwm);
				zcbd.setBddj(bddj);
				zcbd.setSqrbh(sqrbh);
				zcbd.setSqr(sqr);
				
				zcbds.add(zcbd);
			}
		} catch(SQLException e) {
			printSQLException(e);
		}
		return zcbds;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
