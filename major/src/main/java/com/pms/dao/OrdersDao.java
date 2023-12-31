package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pms.bean.Product;
import com.pms.resource.DbResource;

public class OrdersDao {
	public int placeOrders(Product product) {
		try {

			Connection con = DbResource.getDbConnection();
	PreparedStatement pstmt = con.prepareStatement("insert into orders(pid,orderdate,totalprice) values(?,sysdate(),?)");
	pstmt.setInt(1, product.getPid());
	pstmt.setFloat(2, product.getPrice());
	return pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("Product insert exception"+e);
			return 0;
		}
	}
	
	public List<Object[]> findOrderDetails() {
		List<Object[]> listOrderDetails = new ArrayList<Object[]>();
	try {

	Connection con = DbResource.getDbConnection();
		PreparedStatement pstmt = con.prepareStatement("select o.oid,o.orderdate,p.pname,p.price from orders o, product p where o.pid=p.pid;");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Object obj[]=new Object[4];		// depending upon the number of columns using join. 
			obj[0]=rs.getInt(1);
			obj[1]=rs.getString(2);
			obj[2]=rs.getString(3);
			obj[3]=rs.getFloat(4);
			listOrderDetails.add(obj);
		}
		} catch (Exception e) {
			System.err.println("Search product by id"+e);
		}
	return listOrderDetails;
	}

}
