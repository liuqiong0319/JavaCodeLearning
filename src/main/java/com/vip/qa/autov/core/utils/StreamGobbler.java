package com.vip.qa.autov.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

/**
 * A utility class that helps in gathering stream data.
 *
 */
public class StreamGobbler implements Runnable {
	private final InputStreamReader stream;
	private StreamGrobblerHandler handler;

	public StreamGobbler(StreamGrobblerHandler handler, InputStream stream) {
		super();
		this.stream = new InputStreamReader(stream);
		this.handler = handler;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(stream);
			String line = null;
			while ((line = br.readLine()) != null) {
				handler.handle(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}
	}

	public interface StreamGrobblerHandler {
		void handle(String line);
	}

}
