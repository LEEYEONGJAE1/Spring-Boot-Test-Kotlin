package com.havi.bookJsonTest

import com.havi.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@JsonTest
class BookJsonTest {
    @Autowired
    private lateinit var json: JacksonTester<Book>

    @Test
    @Throws(Exception::class)
    fun jsonTest() {
        val book= Book(
            title="테스트",
            publishedAt = null
        )
        val content="{\"title\":\"테스트\"}"
        assertThat(this.json.parseObject(content).title).isEqualTo(book.title)
        assertThat(this.json.parseObject(content).publishedAt).isNull()
        assertThat(this.json.write(book)).isEqualToJson("/test.json")
        assertThat(this.json.write(book)).extractingJsonPathStringValue("title")
            .isEqualTo("테스트")
    }
}
