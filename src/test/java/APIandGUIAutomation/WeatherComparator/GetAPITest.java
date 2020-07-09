package APIandGUIAutomation.WeatherComparator;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import client.RestClient;
import testbase.Testbase;
import utility.TestUtil;

public class GetAPITest extends Testbase {

	Testbase testbase;
	String serviceurl;
	String apiurl;
	String url;
	client.RestClient restClient;
	CloseableHttpResponse closeablehttpresponse;

	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {

		testbase = new Testbase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("ServiceURL");

		// https://reqres.in//api/users

		url = serviceurl + apiurl;
	}

	@Test(priority=1)

	public void getAPITestwithoutHeaders() throws ClientProtocolException, IOException {

		restClient = new RestClient();
		closeablehttpresponse= restClient.get(url);
		
		// 2. Status code
				int Statuscode = closeablehttpresponse.getStatusLine().getStatusCode();// to get the status code
				System.out.println("Status code----------->" + Statuscode);
				
				Assert.assertEquals(Statuscode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

				// 3. JSON String

				String responsestring = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");// complete JSON string

				JSONObject responsejson = new JSONObject(responsestring);// to convert into JSONOBJECT

				System.out.println("Response Json from API-------->" + responsejson);// to print response JSON
				
				//single value assertion:
				//per_page:
				String perPageValue= TestUtil.getValueByJPath(responsejson, "/per_page");
				System.out.println("value of per page is--->"+ perPageValue );
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				
				//total:
				String totalvalue= TestUtil.getValueByJPath(responsejson, "/total");
				System.out.println("value of total is--->"+ totalvalue);
				Assert.assertEquals(Integer.parseInt(totalvalue), 12);
				
				//get the value from JSON Array:
				
				String lastname= TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
				String id= TestUtil.getValueByJPath(responsejson, "/data[0]/id");
				String avatar= TestUtil.getValueByJPath(responsejson, "/data[0]/avatar");
				String firstname= TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");
				String email= TestUtil.getValueByJPath(responsejson, "/data[0]/email");
				
				System.out.println(lastname);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstname);
				System.out.println(email);
				
				Assert.assertEquals(lastname, "Bluth");
				Assert.assertEquals(Integer.parseInt(id), 1);
				//similarly you can do assertions for all the value of JSON Array 
				
				
				// 4. All Headers

				Header[] headersarray = closeablehttpresponse.getAllHeaders();// to get all response headers

				HashMap<String, String> allHeaders = new HashMap<String, String>();

				for (Header header : headersarray) {
					allHeaders.put(header.getName(), header.getValue());
				}

				System.out.println("Headers Array------>" + allHeaders);

			}
	
}
   
       
       
       

