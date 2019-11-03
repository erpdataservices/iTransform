package com.erpdata.iam.datafilter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;

@RestController
@RequestMapping("/dataFilterServices")
public class DataFilterServices {

	@PostMapping("/process")
	public Object process(@RequestBody Object object) {

		ClassPathResource resource = new ClassPathResource("sample.json");

		try {
			InputStream input = resource.getInputStream();
			File file = resource.getFile();
			JsonNode mySchema = JsonLoader.fromFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// JsonNode mySchema = JsonLoader.fromFile(file);
		return object;
	}

}
