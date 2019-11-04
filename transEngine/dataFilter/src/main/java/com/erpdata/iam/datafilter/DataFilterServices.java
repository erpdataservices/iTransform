package com.erpdata.iam.datafilter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;

@RestController
@RequestMapping("/dataFilterServices")
public class DataFilterServices {

	@PostMapping("/process")
	public Object process(@RequestBody Object object) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonObject = mapper.writeValueAsString(object);
			JSONObject obj = new JSONObject(jsonObject);
			String xml = XML.toString(obj);

			ClassPathResource resource = new ClassPathResource("sample.json");
			InputStream input = resource.getInputStream();
			File file = resource.getFile();
			JsonNode mySchema = JsonLoader.fromFile(file);
			System.out.println(mySchema);
			JSONObject jsonSchema = new JSONObject(mySchema.toString());
			Schema schema = SchemaLoader.load(jsonSchema);
			schema.validate(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (ValidationException e) {
			// if data not valid return empty;
			e.printStackTrace();
			return new JSONObject("{}");
		}
		return object;
	}

}
