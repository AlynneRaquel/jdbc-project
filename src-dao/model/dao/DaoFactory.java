package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBD;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBD(DB.getConnection());
	}
}
