import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class PersonalNumAndPassEtiket extends JFrame {

	static Enumeration<?> portList = null;

	static String messageString = "Hello, world!\n";

	static OutputStream outputStream;

	private static final long serialVersionUID = 1L;
	JButton exit, print;

	JTextField userField, tileField, numberField;

	JLabel changesLabel, userLabel, tileLabel, tabLabel, formatBroiki;

	static String choseTab;

	PersonalNumAndPassEtiket() {
		super(" —  œ≈–—ŒÕ¿À≈Õ ÕŒÃ≈–");

		setLayout(null);
		setSize(292, 330);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		print = new JButton("œ–»Õ“»–¿…");
		print.setBounds(10, 245, 170, 45);
		print.addActionListener(new Printing());
		add(print);
		exit = new JButton("»«’Œƒ");
		exit.setBounds(190, 245, 85, 45);
		exit.addActionListener(new Exit());
		add(exit);

		setVisible(true);

		userField = new JTextField();
		add(userField);
		userField.setEditable(true);

		userField.setBounds(95, 40, 100, 50);

		userField.setBackground(Color.GREEN);
		userField.setFont(new Font("SansSerif", Font.BOLD, 18));

		userLabel = new JLabel("¬˙‚Â‰Ë ÔÂÒÓÌ‡ÎÂÌ ÌÓÏÂ");

		add(userLabel);

		userLabel.setBounds(65, 0, 275, 50);

		tileField = new JTextField();
		add(tileField);
		tileField.setEditable(true);

		tileField.setBounds(95, 130, 100, 50);

		tileField.setBackground(Color.GREEN);
		tileField.setFont(new Font("SansSerif", Font.BOLD, 18));

		tileLabel = new JLabel("¬˙‚Â‰Ë Ô‡ÓÎ‡");

		add(tileLabel);

		tileLabel.setBounds(95, 85, 275, 50);

		numberField = new JTextField();

		numberField.setEditable(true);

		numberField.setBounds(125, 195, 50, 35);

		numberField.setFont(new Font("SansSerif", Font.BOLD, 18));

		JLabel labelBroiki = new JLabel(
				"¬ ˙ ‚ Â ‰ Ë                               ·  Ó È Í Ë .");

		labelBroiki.setBounds(40, 180, 280, 35);

		formatBroiki = new JLabel("X X X");

		formatBroiki.setBounds(134, 175, 290, 25);

	}

	public class Exit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(getDefaultCloseOperation());

		}

	}

	public class Printing implements ActionListener {

		Enumeration<?> portList = null;
		CommPortIdentifier portId;
		String messageString = "Hello, world!\n";
		SerialPort serialPort;
		OutputStream outputStream;

		public void actionPerformed(ActionEvent e) {

			String label = "USER";

			String label1 = "PASSWORD";

			String USER = userField.getText();

			String pass = tileField.getText();

			String kraen = "N\n"

			+ "A020,330,0,4,1,1,N,\"" + label1 + "\"\n"
					+ "A040,80,0,4,1,1,N,\"" + label + "\"\n"
					+ "B1,120,0,1,2,2,80,B,\"" + USER + "\"\n"
					+ "B2,380,0,1,2,2,80,B,\"" + pass + "\"\n"
					+ "LO05,200,350,24\n" + "LO05,460,350,24\n" + "P1,1\n";

			portList = CommPortIdentifier.getPortIdentifiers();

			while (portList.hasMoreElements()) {
				portId = (CommPortIdentifier) portList.nextElement();
				if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					if (portId.getName().equals("COM1")) {
						// if (portId.getName().equals("/dev/term/a")) {

						try {
							serialPort = (SerialPort)

							portId.open("SimpleWriteApp", 2000);
						} catch (PortInUseException a) {
						}

						try {
							outputStream = serialPort.getOutputStream();
						} catch (IOException a) {
						}

						try {
							serialPort.setSerialPortParams(9600,
									SerialPort.DATABITS_8,
									SerialPort.STOPBITS_2,
									SerialPort.PARITY_NONE);
						} catch (UnsupportedCommOperationException a) {
						}

						try {
							outputStream.write(kraen.getBytes());

						} catch (IOException a) {
						}
						System.out.println(serialPort);

						serialPort.close();

					}

				}
			}

			try {
				outputStream.close();

			} catch (IOException a) {

				a.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new PersonalNumAndPassEtiket();
	}

}
