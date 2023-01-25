package com.sir.wallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sir.wallet.controller.WalletController;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletService;
import com.sir.wallet.services.WalletServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc

@WebMvcTest(value= WalletController.class)
class SirWalletApplicationTests {

    @Autowired
    @MockBean
    private WalletServiceImpl walletService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testGetAllWallet() throws Exception {

        when(walletService.getWalletById(1L)).thenReturn(Optional.of(new Wallet(1, "test", 100)));
        MockHttpServletRequestBuilder reqBuild = MockMvcRequestBuilders.get("/api/wallet/wallets/1");

        ResultActions perform =  mockMvc.perform(reqBuild);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();

        assertEquals(200, status);

    }

    @Test
    public void testAddWallet() throws Exception {
        //when(walletService.saveWallet(ArgumentMatchers.any())).thenReturn(true);


        Wallet wallet = new Wallet(1, "My Wallet", 100);
        ObjectMapper mapper = new ObjectMapper();
        String walletJson = mapper.writeValueAsString(wallet);

        MockHttpServletRequestBuilder reqBuild = MockMvcRequestBuilders.post("/api/wallet/postsWallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(walletJson);

        ResultActions perform =  mockMvc.perform(reqBuild);


        MvcResult mvcResult = perform.andReturn();


        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(201, status);

    }

    @Test
    public void testUpdateWallet() throws Exception {
        //when(walletService.saveWallet(ArgumentMatchers.any())).thenReturn(true);


        Wallet wallet = new Wallet(1, "Updated Wallet", 100);
        ObjectMapper mapper = new ObjectMapper();
        String walletJson = mapper.writeValueAsString(wallet);

        MockHttpServletRequestBuilder reqBuild = MockMvcRequestBuilders.put("/api/wallet//wallets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(walletJson);

        ResultActions perform =  mockMvc.perform(reqBuild);


        MvcResult mvcResult = perform.andReturn();


        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200, status);

    }

}
