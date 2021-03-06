package edu.ycp.cs320.sme.main;

import java.util.Scanner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import edu.ycp.cs320.sme.sql.DatabaseProvider;
import edu.ycp.cs320.sme.sql.DBmain;

public class Main {
	@SuppressWarnings("static-access")
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8081);

		// Create and register a webapp context
		WebAppContext handler = new WebAppContext();
		handler.setContextPath("/sme");
		handler.setWar("./war"); // web app is in the war directory of the project
		server.setHandler(handler);
		
		// Use 20 threads to handle requests
		server.setThreadPool(new QueuedThreadPool(20));
		
		// Start the server
		server.start();
		
		//Initialize database tables
		//DBmain.initTables();
		DBmain db = new DBmain();
		db.main(null);
	
		DatabaseProvider.setInstance(db);
		
		// Wait for the user to type "quit"
		System.out.println("Web server started, type quit to shut down - http://localhost:8081/sme");
		Scanner keyboard = new Scanner(System.in);
		while (keyboard.hasNextLine()) {
			String line = keyboard.nextLine();
			if (line.trim().toLowerCase().equals("quit")) {
				break;
			}
		}
		
		
		
		keyboard.close();
		System.out.println("Shutting down...");
		server.stop();
		server.join();
		System.out.println("Server has shut down, exiting");
	}
}
