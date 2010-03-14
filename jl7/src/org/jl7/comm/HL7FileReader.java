/**
 * 
 */
package org.jl7.comm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jl7.hl7.HL7Message;
import org.jl7.hl7.HL7Parser;

/**
 * @author henribenoit
 * 
 */
public class HL7FileReader implements HL7Reader {
	private File file;
	private BufferedReader reader;

	/**
	 * @param file
	 */
	public HL7FileReader(File file) {
		super();
		this.file = file;
	}

	/**
	 * @param pathname
	 */
	public HL7FileReader(String pathname) {
		super();
		this.file = new File(pathname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#close()
	 */
	public void close() throws IOException {
		try {
			reader.close();
		} finally {
			reader = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#getMessage()
	 */
	public HL7Message getMessage() throws IOException {
		StringBuffer sb = new StringBuffer();
		for (;;) {
			int read = reader.read();
			if (read == -1) {
				break;
			}
			sb.append((char) read);
		}
		return HL7Parser.parseMessage(sb.toString(), true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#isOpen()
	 */
	public boolean isOpen() throws IOException {
		return (reader != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#open()
	 */
	public void open() throws IOException {
		FileInputStream inputStream;

		inputStream = new FileInputStream(file);
		reader = new BufferedReader(new InputStreamReader(inputStream));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jl7.comm.HL7Reader#getParameters()
	 */
	public Object[] getParameters() {
		return new Object[] { file.getAbsolutePath() };
	}

}
