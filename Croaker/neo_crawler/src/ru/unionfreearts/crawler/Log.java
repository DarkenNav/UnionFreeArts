package ru.unionfreearts.crawler;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yy > ");
	private PrintWriter writer;

	public Log() throws Exception {
		writer = new PrintWriter(new FileWriter("log.txt"));
	}

	public void appendLog(String msg) throws Exception {
		writer.write(dateFormat.format(new Date())+msg + "\n");
		writer.flush();
	}

	public void closeLog() throws Exception {
		writer.close();
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void flush() {
		writer.flush();
	}
}
