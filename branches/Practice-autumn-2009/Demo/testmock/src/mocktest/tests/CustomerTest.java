/* 
 * Mock tests in Java
 * Victor Polozov (c) 2009 
 */
package mocktest.tests;

import mocktest.*;

import org.junit.*;
import org.junit.runner.RunWith;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;

@RunWith(JMock.class)
public class CustomerTest {
	Mockery context = new JUnit4Mockery();

	class ShopVodkaExistsMock implements IShop {
		@Override
		public boolean isInStock(Goods goods) {
			return true;
		}

		@Override
		public void buy(Goods goods) {
			throw new NotImplementedException();
		}
	}

	class ShopVodkaIsAbsentMock implements IShop {
		@Override
		public boolean isInStock(Goods goods) {
			return false;
		}

		@Override
		public void buy(Goods goods) {
			// TODO Auto-generated method stub			
		}
	}

	@Test
	public void VisitShopTest() {
		final IShop shop = context.mock(IShop.class);
		
		Customer customer = new Customer(shop);
		
		context.checking(new Expectations() {
			{
				oneOf(shop).isInStock(Goods.Vodka);
					will(returnValue(true));
				oneOf(shop).buy(Goods.Vodka);
				oneOf(shop).buy(Goods.Iriska);
				oneOf(shop).buy(Goods.Vodka);
			}
		});
		
		customer.VisitShop();
	}
}
