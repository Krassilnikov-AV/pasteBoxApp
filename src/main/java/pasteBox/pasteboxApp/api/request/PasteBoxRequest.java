package pasteBox.pasteboxApp.api.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasteBoxRequest {
	private String data;
	private long expirationTimeSounds;
	private PublicStatus publicStatus;

}