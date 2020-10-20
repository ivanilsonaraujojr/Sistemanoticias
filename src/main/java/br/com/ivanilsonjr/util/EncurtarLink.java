package br.com.ivanilsonjr.util;


import java.util.Collections;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


@Component
public class EncurtarLink {

	@Value("${token.bitly}")
	private String tokenBitly;

	@Value("${url.bitly}")
	private String urlBitly;

	private RestTemplate restTemplate = new RestTemplate();

	public String shortURL(String urlToShort) {

        	System.out.println("==> GERANDO LINK DE IMAGEM ENCURTADO <<=");

        	HttpHeaders headers = new HttpHeaders();
        	headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        	headers.setContentType(MediaType.APPLICATION_JSON);
        	headers.setBearerAuth(tokenBitly);

        	JSONObject jsonObject = new JSONObject();
        	jsonObject.put("group_guid", "Bk9iee5RSSx")
        			  .put("domain", "bit.ly")
        			  .put("long_url", urlToShort);

        	HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
            try {
            	ResponseEntity<String> response = restTemplate.postForEntity(urlBitly, entity, String.class);

                JSONObject myobj = new JSONObject(response.getBody());

                System.out.println("LINK GERADO: "+ myobj.getString("link"));
                System.out.println("==> FIM <==");

                //Retorna o link encurtado se nao houver algum erro
                return myobj.getString("link");
            }catch (HttpStatusCodeException e) {
        		System.out.println("Erro ao gerar link: " + e.getLocalizedMessage().trim());
        		System.out.println("Retornando url sem encurtar");
        		System.out.println("==> FIM <==");

        		//Retorna a url sem encurtar se houver algum erro
        		return urlToShort;
        }
    }
}
