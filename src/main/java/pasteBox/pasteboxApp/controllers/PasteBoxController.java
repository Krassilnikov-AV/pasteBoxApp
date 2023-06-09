package pasteBox.pasteboxApp.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pasteBox.pasteboxApp.api.request.PasteBoxRequest;
import pasteBox.pasteboxApp.api.response.*;
import pasteBox.pasteboxApp.service.PastBoxService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {
	private final PastBoxService pastBoxService;

//	@GetMapping("/box/")
//	public Collection<String> getPublic() {
//		return Collections.emptyList();
//	}

	@GetMapping("/box/")
	public Collection<PasteBoxResponse> getPublicList() {

		return pastBoxService.getFirstPublicPasteboxes();
	}

//	@GetMapping("/box/{hash}")
//	public String getByHash(@PathVariable String hash) {
//		return hash;
//	}

	@GetMapping("/box/{hash}")
	public PasteBoxResponse getByHash(@PathVariable String hash) {
		return pastBoxService.getByHash(hash);
	}

	//	@PostMapping("/")
//	public String add(@RequestBody PasteBoxRequest request) {
//		return request.getData();
//	}
	@PostMapping("/")
	public PasteBoxUrlResponse add(@RequestBody PasteBoxRequest request) {

		return pastBoxService.create(request);
	}
}