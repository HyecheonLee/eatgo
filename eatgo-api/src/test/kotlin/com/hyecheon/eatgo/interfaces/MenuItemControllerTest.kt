package com.hyecheon.eatgo.interfaces

import com.hyecheon.eatgo.domain.MenuItemService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(MenuItemController::class)
internal class MenuItemControllerTest {

	@Autowired
	private lateinit var mvc: MockMvc

	@MockBean
	private lateinit var menuItemService: MenuItemService

	@Test
	internal fun bulkUpdate() {
		val restaurantId: Long = 1
		mvc.perform(patch("/restaurants/${restaurantId}/menuitems")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[]"))
				.andExpect(status().isOk)

		verify(menuItemService).bulkUpdate(eq(restaurantId), any())
	}

	private fun <T> any(): T {
		Mockito.any<T>()
		return null as T
	}
}