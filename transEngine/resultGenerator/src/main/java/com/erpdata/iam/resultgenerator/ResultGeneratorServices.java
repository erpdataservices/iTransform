package com.erpdata.iam.resultgenerator;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/resultGeneratorServices")
public class ResultGeneratorServices {

	@PostMapping("/generate")
	public String generate(@RequestBody Object object, @RequestParam(value = "fileName") String fileName,
			@RequestParam(value = "targetType") String targetType) {
		if (StringUtils.isEmpty(targetType) || StringUtils.isEmpty(fileName)) {
			return "Empty file name or file type";
		} else if (targetType.equalsIgnoreCase("json")) {
			try (FileWriter file = new FileWriter(fileName + "." + targetType)) {
				ObjectMapper mapper = new ObjectMapper();
				String jsonObject = mapper.writeValueAsString(object);
				file.write(jsonObject);
			} catch (IOException e) {
				return e.getMessage();
			}

		} else if (targetType.equalsIgnoreCase("xml")) {
			try (FileWriter file = new FileWriter(fileName + "." + targetType)) {
				ObjectMapper mapper = new ObjectMapper();
				String jsonObject = mapper.writeValueAsString(object);
				JSONObject obj = new JSONObject(jsonObject);
				String xml = XML.toString(obj);
				file.write(xml);
			} catch (IOException e) {
				return e.getMessage();
			}
		} else if (targetType.equalsIgnoreCase("csv")) {

		}
		return "Success";
	}

}
