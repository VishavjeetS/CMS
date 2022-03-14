package com.example.db;

import java.util.ArrayList;

public interface DAO<T> {
	int insert(T object);
	int update(T object);
	int delete(int id);
	ArrayList<T> query();
}
