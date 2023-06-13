package com.maesquiv.task.service.readfiles;

import java.util.List;

public interface IReadFiles<T> {
	void readTxt();
	List<T> readTxtWithPath(String path);
	List<T> readCsvWithPath(String path);
}
