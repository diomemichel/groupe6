package com.sir.wallet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sir.wallet.model.Wallet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.inject.Inject;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@AutoConfigureMockMvc
public class WalletAddSteps {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";

    @Inject
    MockMvc mockMvc;

    private Long postId = 0L;

    @Given("I can create a new wallet")
    public void i_can_create_a_new_wallet() {
        String url = postUrl + ":" + port + "/api/wallet/wallets/all";
        List<Wallet> allPost = restTemplate.getForObject(url, List.class);
        //http://localhost:8080/
        assertTrue(allPost.isEmpty());
    }

    @Given("I sending wallet to be created with wallet id {int}, name {string} and balance <{int}>")
    public void i_sending_wallet_to_be_created_with_wallet_id_name_and_balance(Integer int1, String string, Integer int2) {
        String url = postUrl + ":" + port + "/api/wallet/postsWallet";
        Wallet newPost = new Wallet();
        newPost.setId(int1);
        newPost.setName("ok");
        newPost.setBalance(int2);
        Wallet post = restTemplate.postForObject(url, newPost, Wallet.class);
        postId = post.getId();

        assertNotNull(post);
    }

    @Then("I should be able to see my newly created wallet")
    public void i_should_see_my_newly_created_wallet() {
        String url = postUrl + ":" + port + "/api/wallet/wallets/" + postId;
        Wallet myPost = restTemplate.getForObject(url, Wallet.class);

        assertNotNull(myPost);
    }


    private String name1;
    private Long balance1;
    int status;

    Wallet wa;
    @Given("a wallet with name {string} and balance {int}")
    public void a_wallet_with_name_and_balance(String string, int int1) {
        // Write code here that turns the phrase above into concrete actions
        name1 = string;
        balance1 = Long.valueOf(int1);
    }

    @When("I POST the wallet to the endpoint")
    public void i_post_the_wallet_to_the_endpoint() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        Wallet wallet = new Wallet(1, "My Wallet", 100);
        ObjectMapper mapper = new ObjectMapper();
        String walletJson = mapper.writeValueAsString(wallet);

        MockHttpServletRequestBuilder reqBuild = MockMvcRequestBuilders.post("/api/wallet/postsWallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(walletJson);

        ResultActions perform =  mockMvc.perform(reqBuild);


        MvcResult mvcResult = perform.andReturn();


        MockHttpServletResponse response = mvcResult.getResponse();


        wa = mapper.readValue(walletJson, Wallet.class);


        status = response.getStatus();
        assertEquals(201, status);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(int1, status);
    }

    @Then("the response body should contain the wallet")
    public void the_response_body_should_contain_the_wallet() {
        // Write code here that turns the phrase above into concrete actions
        assertNotNull(wa);
    }




    //
    private String name2;
    private Long balance2;
    private Long id2;
    private Wallet wallet2;
    private int status2;

    @Given("a wallet with ID {int} and name {string} and balance {int}")
    public void a_wallet_with_id_and_name_and_balance(Integer int1, String string, Integer int2) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        name2=string;
        id2 = Long.valueOf(int1);
        balance2 = Long.valueOf(int2);

    }
    @When("I GET the wallet from the endpoint")
    public void i_get_the_wallet_from_the_endpoint() throws Exception {


        ObjectMapper mapper = new ObjectMapper();


        MockHttpServletRequestBuilder reqBuild = MockMvcRequestBuilders.get("/api/wallet/wallets/"+id2)
                .contentType(MediaType.APPLICATION_JSON);


        ResultActions perform =  mockMvc.perform(reqBuild);


        MvcResult mvcResult = perform.andReturn();


        //MockHttpServletResponse response = mvcResult.getResponse();

        String response2 = mvcResult.getResponse().getContentAsString();
        wallet2 = mapper.readValue(response2, Wallet.class);
        System.out.println("================");
        System.out.println(response2);

        status2 = mvcResult.getResponse().getStatus();
        wallet2 = mapper.readValue(response2, Wallet.class);
        System.out.println(status2);
        assertEquals(200, status2);
    }

    @Then("the response status2 should be {int}")
    public void the_response_status2_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(int1, status2);
    }

    @Then("the response body2 should contain the wallet")
    public void the_response_body2_should_contain_the_wallet() {
        // Write code here that turns the phrase above into concrete actions
        assertNotNull(wallet2);
    }


    //
    private String name3;
    private Long balance3;
    private Long id3;
    private Wallet wallet3;
    private int status3;

    @Given("a wallet with ID {int} and name {string} and balance2 {int}")
    public void a_wallet_with_id_and_name_and_balance2(Integer int1, String string, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        name3=string;
        id3 = Long.valueOf(int1);
        balance3 = Long.valueOf(int2);

    }

    @When("I PUT the wallet to the {string} endpoint with name {string} and balance {int}")
    public void i_put_the_wallet_to_the_endpoint_with_name_and_balance(String string, String string2, Integer int1) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        // Write code here that turns the phrase above into concrete actions
        Wallet wallet = new Wallet(1, "My Wallet", 100);
        ObjectMapper mapper = new ObjectMapper();
        String walletJson = mapper.writeValueAsString(wallet);

        MockHttpServletRequestBuilder reqBuild = MockMvcRequestBuilders.put(string)
                .contentType(MediaType.APPLICATION_JSON)
                .content(walletJson);

        ResultActions perform =  mockMvc.perform(reqBuild);


        MvcResult mvcResult = perform.andReturn();


        MockHttpServletResponse response = mvcResult.getResponse();


        wallet3 = mapper.readValue(walletJson, Wallet.class);


        status = response.getStatus();
        assertEquals(200, status);
    }
    @Then("the response body should contain the updated wallet")
    public void the_response_body_should_contain_the_updated_wallet() {
        // Write code here that turns the phrase above into concrete actions
        assertNotNull(wallet3);
    }




    //
    private String name4;
    private Long balance4;
    private Long id4;
    private Wallet wallet4;
    private int status4;
    @Given("a wallet with ID {int} and name {string} and balance3 {int}")
    public void a_wallet_with_id_and_name_and_balance3(Integer int1, String string, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        name4=string;
        id4 = Long.valueOf(int1);
        balance4 = Long.valueOf(int2);
    }

    @When("I DELETE the wallet from the {string} endpoint")
    public void i_delete_the_wallet_from_the_endpoint(String string) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();



        MockHttpServletRequestBuilder reqBuild = MockMvcRequestBuilders.delete(string);



        ResultActions perform =  mockMvc.perform(reqBuild);


        MvcResult mvcResult = perform.andReturn();


        //MockHttpServletResponse response = mvcResult.getResponse();



        status4 = mvcResult.getResponse().getStatus();
        System.out.println("++++++++++++++++++++");
        System.out.println(status4);
        //assertEquals(200, status4);
    }

    @Then("the response should be {int}")
    public void the_response_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(int1, status4);
    }

    @Then("the wallet should not be retrievable")
    public void the_wallet_should_not_be_retrievable() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        String url = postUrl + ":" + port + "/api/wallet/wallets/1";
        Wallet myPost = restTemplate.getForObject(url, Wallet.class);

        assertNull(myPost);
    }


    //
    @When("I POST a transaction with wallet ID {int} and amount {int} and type {string} to the {string} endpoint")
    public void i_post_a_transaction_with_wallet_id_and_amount_and_type_to_the_endpoint(Integer int1, Integer int2, String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the wallet with ID {int} should have a balance of {int}")
    public void the_wallet_with_id_should_have_a_balance_of(Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
