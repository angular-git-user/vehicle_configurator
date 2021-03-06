package app.hibernate1;

import org.hibernate.Session;

import app.connection.HibernateConnectionUtil;


public class App {
	public static void main1(String[] args) {
		
    System.out.println("Hibernate one to many (Annotation)");
	Session session = HibernateConnectionUtil.getConnectionFactory().openSession();

	session.beginTransaction();

	Stock stock = new Stock();
    stock.setStockCode("7052");
    stock.setStockName("PADINI");
    session.save(stock);
    
    StockDailyRecord stockDailyRecords = new StockDailyRecord();
    stockDailyRecords.setPriceOpen(new Float("1.2"));
    stockDailyRecords.setPriceClose(new Float("1.1"));
    stockDailyRecords.setPriceChange(new Float("10.0"));
    stockDailyRecords.setVolume(3000000L);
    
    stockDailyRecords.setStock(stock);        
    stock.getStockDailyRecords().add(stockDailyRecords);

    session.save(stockDailyRecords);

	session.getTransaction().commit();
	System.out.println("Done");
	System.out.println("Done");
	}
}
