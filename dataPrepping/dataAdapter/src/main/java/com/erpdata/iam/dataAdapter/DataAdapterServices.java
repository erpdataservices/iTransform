package com.erpdata.iam.dataAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/dataAdapterServices")
public class DataAdapterServices {

	@PostMapping("/adapter")
	public Object adapter(@RequestBody MultipartFile file) {

		// 1. xml to JSON
		try {
			InputStreamReader isReader = new InputStreamReader(file.getInputStream());
			BufferedReader reader = new BufferedReader(isReader);
			StringBuffer sb = new StringBuffer();
			String str;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
			String xmlData = sb.toString();
			System.out.println(xmlData);
			JSONObject xmlJSONObj = XML.toJSONObject(xmlData);
			return xmlJSONObj.toString();
		} catch (IOException e) {
			return e.getStackTrace();
		}

		// 2. CSV to JSON
		// 3. Excel to JSON
		// 4. If JSON file then return JSON content

	}

}
