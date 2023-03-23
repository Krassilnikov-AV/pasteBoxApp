package pasteBox.pasteboxApp.repository;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * \* @author AleKras on 18.03.2023
 * \* Description:
 * \*
 */
@Data
public class PasteBoxEntity {
	private int id;
	private String data;
	private String hash;
	private LocalDateTime lifetime;
	private boolean isPublic;
//	private PublicStatus publicStatus;

}