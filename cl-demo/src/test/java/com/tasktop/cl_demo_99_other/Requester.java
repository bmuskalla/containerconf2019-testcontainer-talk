package com.tasktop.cl_demo_99_other;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class Requester {

	public static void doGet(String uri) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
		String body = client.send(request, BodyHandlers.ofString()).body();
		System.err.println(body);
		
	}
	
}
