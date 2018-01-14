package com.atguigu.dubbo;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.dubbo.entities.Book;
import com.atguigu.dubbo.service.BookService;

public class BookServiceImpl implements BookService {

	@Override
	public List<Book> queryAllBook() {
		List<Book> list = new ArrayList<>();
		
		list.add(new Book(1,"三国演义",100d));
		list.add(new Book(2,"水浒传",90100d));
		list.add(new Book(3,"西游记",80d));
		return list;
	}

}
