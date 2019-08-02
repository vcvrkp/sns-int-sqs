/**
 * 
 */
package com.dell.sqsintsns;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vcvr
 *
 */
@RestController
@RequestMapping("/index.html")
public class SampleController {

	@GetMapping(produces="application/json")
	public String getmesometigs() {
		// send message to queue
		return "{ \"message\" : \"Hello Mister\" }";
	}
	
}
