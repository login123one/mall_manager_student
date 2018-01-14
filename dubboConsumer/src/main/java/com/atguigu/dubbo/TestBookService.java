package com.atguigu.dubbo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.dubbo.entities.Book;
import com.atguigu.dubbo.service.BookService;

public class TestBookService {
	private ApplicationContext ac = null;
	private BookService bookService = null;
	
	@Before
	public void setUp() {
		ac = new ClassPathXmlApplicationContext("classpath:dubbo/dubbo-*.xml");
		bookService = (BookService) ac.getBean("bookService");
	}
	
	@Test
	public void testBookService() {
		List<Book> queryAllBook = bookService.queryAllBook();
		for (Book book : queryAllBook) {
			System.out.println(book);
		}
	}
	
	
}
