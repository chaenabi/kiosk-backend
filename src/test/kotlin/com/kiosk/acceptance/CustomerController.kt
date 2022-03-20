/*
package com.kiosk.acceptance

import com.fasterxml.jackson.databind.ObjectMapper
import com.kiosk.api.customer.controller.CustomerController
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.customer.service.CustomerService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.filter.CharacterEncodingFilter

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerController {

    @LocalServerPort
    var port: Int? = null

    @Autowired
    private lateinit var customerService: CustomerService
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @BeforeEach
    fun init() {
        mockMvc = MockMvcBuilders.standaloneSetup(CustomerController(customerService))
            .addFilters<StandaloneMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .build()
    }


    @Test
    fun `회원 정보 저장 - 성공`() {
        val content: Map<String, String> = mapOf<String, String>(
            "id" to "1",
            "contactNumber" to "010-1234-5678",
            "name" to "5678",
        )
        val json = mapper.writeValueAsString(content)
        val deserializedCustomerDTO = mapper.readValue(json, CustomerRequestDTO.Register::class.java)
        println(deserializedCustomerDTO)
        val result = CustomerResponseDTO(deserializedCustomerDTO.toEntity()).also { it.id = 1 }

        print(result)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/customer")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(result.id))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(result.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.contactNumber").value(result.contactNumber))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.role").value(result.role.toString()))
    }

}*/
