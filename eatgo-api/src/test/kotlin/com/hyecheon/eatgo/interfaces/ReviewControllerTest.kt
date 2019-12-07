package com.hyecheon.eatgo.interfaces

import com.hyecheon.eatgo.application.ReviewService
import com.hyecheon.eatgo.domain.Review
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(ReviewController::class)
internal class ReviewControllerTest {

	@MockBean
	private lateinit var reviewService: ReviewService
	@Autowired
	lateinit var mvc: MockMvc

	@Test
	internal fun createWithValidAttributes() {

		given(reviewService.addReview(eq(1L), any())).willReturn(Review(id = 1004L))

		mvc.perform(post("/restaurants/1/reviews")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
					"name":"JOCKER",
					"score":3,
					"description":"Mat-it-da"
					}
				""".trimIndent()))
				.andExpect(status().isCreated)
		verify(reviewService).addReview(eq(1L), any())
	}

	@Test
	internal fun createWithInValidAttributes() {
		given(reviewService.addReview(eq(1L), any())).willReturn(Review(id = 1004L))
		mvc.perform(post("/restaurants/1/reviews")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{}
				""".trimIndent()))
				.andExpect(status().isBadRequest)
		verify(reviewService, never()).addReview(eq(1L), any())
	}

	private fun <T> any(): T {
		Mockito.any<T>()
		return null as T
	}
}