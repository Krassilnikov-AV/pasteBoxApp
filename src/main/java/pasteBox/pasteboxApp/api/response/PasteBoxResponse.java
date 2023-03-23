package pasteBox.pasteboxApp.api.response;
//



import lombok.*;

@Data
@RequiredArgsConstructor
public class PasteBoxResponse {

	private final String data;
	private final boolean isPublic;


}