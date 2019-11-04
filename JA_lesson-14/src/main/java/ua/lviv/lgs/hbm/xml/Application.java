package ua.lviv.lgs.hbm.xml;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Application {

	public static void main(String[] args) {

		Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();

        SessionFactory factory = config.buildSessionFactory(serviceRegistry);

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();
		
		Cart cart = new Cart(3, "Cart-1");
		Item item1 = new Item(147);
		Item item2 = new Item(258);
		Item item3 = new Item(369);
		Item item4 = new Item(123);
		cart.setItems(new HashSet<Item>(Arrays.asList(item1, item2, item3, item4)));
		
		Cart cart2 = new Cart(6, "Cart-2");
		Item item5 = new Item(369);
		Item item6 = new Item(258);
		Item item7 = new Item(147);
		Item item8 = new Item(987);
		cart2.setItems(new HashSet<Item>(Arrays.asList(item5, item6, item7, item8)));
		
		session.persist(cart);
		session.persist(cart2);
		
		transaction.commit();
		session.close();

	}

}
