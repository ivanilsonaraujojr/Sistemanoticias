package br.com.ivanilsonjr.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class EncurtarLink {
	public static String shortURL(String urlToShort) {
        String acessToken = "82d858d8749de1711ea14433c6289ffb9c51d43b";
        try {
        	System.out.println("==> GERANDO LINK DE IMAGEM ENCURTADO <<=");
        	CloseableHttpClient client = HttpClients.createDefault();
        	HttpPost httpPost = new HttpPost("https://api-ssl.bitly.com/v4/shorten");
        	JSONObject jsonObject = new JSONObject();
        	jsonObject.put("group_guid", "Bk9iee5RSSx")
        			  .put("domain", "bit.ly")
        			  .put("long_url", urlToShort);
            StringEntity entity = new StringEntity(jsonObject.toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + acessToken);
            CloseableHttpResponse responses = client.execute(httpPost);
            try(BufferedReader br = new BufferedReader(new InputStreamReader(responses.getEntity().getContent()))){
            	String inputLine;
        		StringBuffer content = new StringBuffer();
        		while ((inputLine = br.readLine()) != null) {
        		    content.append(inputLine);
        		}
        		client.close();
        		br.close();
        		if(content.toString().contains("ALREADY_A_BITLY_LINK")) {
        			System.out.println("LINK INSERIDO JA É UM BITLY");
        			System.out.println("==> FIM <==");
            		return urlToShort;
        		}else {
            		JSONObject myobj = new JSONObject(content.toString());
            		System.out.println("LINK GERADO: "+ myobj.getString("link"));
            		System.out.println("==> FIM <==");
            		return myobj.getString("link");
        		}
            }} catch (Exception e) {
            System.out.println("==> Erro ao gerar link: " + e.getMessage() + "<<=");
            e.printStackTrace();
        }
        return null;
    }
}
