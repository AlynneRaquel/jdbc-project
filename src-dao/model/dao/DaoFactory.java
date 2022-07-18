package model.dao;

import model.dao.impl.SellerDaoJDBD;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBD();
	}
}
