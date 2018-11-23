package com.servlets.projects.Hibernate.Hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String[] args) {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		try {
	
		session.beginTransaction();
		Stock stock=new Stock();
		stock.setStockName("Milk");
		stock.setStockCode(2361);
		StockDetail stockDetail=new StockDetail();
		stockDetail.setCompName("Tops");
		stockDetail.setCompDesc("one stop shopping");
		stockDetail.setRemark("Good");
		stockDetail.setListedDate(new Date());
		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);
		session.save(stock);
		session.getTransaction().commit();
		System.out.println("done");
		session.close();
		sessionFactory.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error occured");
		}
		session.close();
		sessionFactory.close();
	}
}
