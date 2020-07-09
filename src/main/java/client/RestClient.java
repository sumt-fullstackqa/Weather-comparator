package client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. Get Method without Headers:

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);// this is http get request
		CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpget); // hit the GET url
		
		return closeablehttpresponse;	
}
	//2. Get Method with Headers:
	
	public CloseableHttpResponse get(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);// this is http get request
		
		for(Map.Entry<String,String> entry : headermap.entrySet()) {
			
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpget); // hit the GET url
		
		return closeablehttpresponse;	
}
	
	//3. Post Method:
	
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost= new HttpPost(url);// http post request 
		
		httppost.setEntity(new StringEntity(entityString));// for payload
		
		// For headers 
		
          for(Map.Entry<String,String> entry : headermap.entrySet()) {
			
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		
          CloseableHttpResponse closeablehttpresponse= httpClient.execute(httppost);
          
          return closeablehttpresponse;
		
	}
	
	//4. Put Method:
	
	public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(url);// http put request 
		
		httpPut.setEntity(new StringEntity(entityString));// for payload
		
		// For headers 
		
          for(Map.Entry<String,String> entry : headermap.entrySet()) {
			
        	  httpPut.addHeader(entry.getKey(), entry.getValue());
		}
		
		
          CloseableHttpResponse closeablehttpresponse= httpClient.execute(httpPut);
          
          return closeablehttpresponse;
		
	}
	
	//5. Delete Method:
	

	public CloseableHttpResponse delete(String url,HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(url);// http delete request 
		
		// For headers 
		
          for(Map.Entry<String,String> entry : headermap.entrySet()) {
			
        	  httpDelete.addHeader(entry.getKey(), entry.getValue());
		}
		
		
          CloseableHttpResponse closeablehttpresponse= httpClient.execute(httpDelete);
          
          return closeablehttpresponse;
		
	}
	
}
	

