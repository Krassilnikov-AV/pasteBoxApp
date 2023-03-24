package pasteBox.pasteboxApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pasteBox.pasteboxApp.api.response.PasteBoxResponse;
import pasteBox.pasteboxApp.exception.NotFoundEntityException;
import pasteBox.pasteboxApp.repository.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author AleKras on 24.03.2023
 */
@SpringBootTest
class PasteBoxServiceImplTest {
	@Autowired
	private PastBoxService pastBoxService;

	@MockBean
	private PasteBoxRepository pasteBoxRepository;

	@Test
	public void notExistHash() {
		when(pasteBoxRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
		assertThrows(NotFoundEntityException.class, () -> pastBoxService.getByHash("1"));

	}

	@Test
	public void getExistHash() {
		PasteBoxEntity entity = new PasteBoxEntity();
		entity.setHash("1");
		entity.setData("11");
		entity.setPublic(true);

		when(pasteBoxRepository.getByHash("1")).thenReturn(entity);

		PasteBoxResponse expected = new PasteBoxResponse("11", true);
		PasteBoxResponse actual = pastBoxService.getByHash("1");

		assertEquals(expected, actual);
	}
}