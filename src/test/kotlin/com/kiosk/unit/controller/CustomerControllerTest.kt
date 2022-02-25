package com.kiosk.unit.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.kiosk.api.customer.controller.CustomerController
import com.kiosk.api.customer.domain.model.CustomerRequestDTO
import com.kiosk.api.customer.domain.model.CustomerResponseDTO
import com.kiosk.api.customer.service.CustomerService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.filter.CharacterEncodingFilter

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [CustomerController::class])
class CustomerControllerTest {

    @MockkBean
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

        every { customerService.register(any<CustomerRequestDTO.Register>()) } returns (result)

        print(result)

        mockMvc.perform(
            post("/v1/customer")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data.id").value(result.id))
            .andExpect(jsonPath("$.data.name").value(result.name))
            .andExpect(jsonPath("$.data.contactNumber").value(result.contactNumber))
            .andExpect(jsonPath("$.data.role").value(result.role.toString()))
    }

}