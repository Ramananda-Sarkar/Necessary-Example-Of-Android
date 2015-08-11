package com.example.csvreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReadFile {
	InputStream inputStream;

	public CSVReadFile(InputStream is) {
		inputStream = is;
	}

	public List<String[]> read() {
		List<String[]> resultList = new ArrayList<String[]>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));

		String csvLine;
		try {
			while ((csvLine = reader.readLine()) != null) {
				String[] row = csvLine.split(",");
				resultList.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return resultList;
	}
}
