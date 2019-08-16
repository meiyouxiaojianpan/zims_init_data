package com.helios.gao.util;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.helios.gao.domain.ContractDTO;
import com.helios.gao.domain.ProjectDTO;
import com.helios.gao.domain.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class ZimsClient {

    private static String host = "http://zims-portal.huilianyi.com/zims";
    private static String hostProd = "http://api.huilianyi.com";
    private static String getUserURL = "/api/users/like";
    private static String getContract = "/api/contracts/";
    private static String getContractURL = "/api/contracts/page";
    private static String postContractURL = "/api/contracts/submit";
    private static String postProjectURL = "/api/projects";
    private static String sendEmail = "/api/expense/reports/send/email";
    private static String printPdf = "/api/expense/reports/generate/pdf";

    private static RestTemplate restTemplate = new RestTemplate();

    public static Long getSalesId(String userName) {
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<JsonNode> responseEntity = null;
        responseEntity = restTemplate.exchange(host + getUserURL + "?userName=" + userName, HttpMethod.GET, httpEntity, JsonNode.class);
        User user = JSONObject.parseObject(responseEntity.getBody().get(0).toString(), User.class);
        return user.getId();
    }

    private static ContractDTO getContract(Long contractId) {
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<JsonNode> responseEntity = null;
        responseEntity = restTemplate.exchange(host + getContract + "/{entityId}", HttpMethod.GET, httpEntity, JsonNode.class, contractId);
        ContractDTO contractDTO = JSONObject.parseObject(responseEntity.getBody().toString(), ContractDTO.class);
        return contractDTO;
    }

    private static ProjectDTO getProject(Long projectId) {
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<JsonNode> responseEntity = null;
        responseEntity = restTemplate.exchange(host + postProjectURL + "/{entityId}", HttpMethod.GET, httpEntity, JsonNode.class, projectId);
        ProjectDTO projectDTO = JSONObject.parseObject(responseEntity.getBody().toString(), ProjectDTO.class);
        return projectDTO;
    }

    public String getLocationCode(String cityName) {
        return null;
    }

    public static ContractDTO insertContract(ContractDTO dto) {
        HttpHeaders httpHeaders = getPostHeaders();
        HttpEntity<ContractDTO> httpEntity = new HttpEntity<>(dto, httpHeaders);
        ResponseEntity<JsonNode> responseEntity = null;
        responseEntity = restTemplate.postForEntity(host + postContractURL, httpEntity, JsonNode.class);
        ContractDTO contractDTO = JSONObject.parseObject(responseEntity.getBody().toString(), ContractDTO.class);
        contractDTO = getContract(contractDTO.getId());
        return contractDTO;
    }

    public static ProjectDTO insertProject(ProjectDTO dto) {
        HttpHeaders httpHeaders = getPostHeaders();
        HttpEntity<ProjectDTO> httpEntity = new HttpEntity<>(dto, httpHeaders);
        ResponseEntity<JsonNode> responseEntity = null;
        responseEntity = restTemplate.postForEntity(host + postProjectURL, httpEntity, JsonNode.class);
        ProjectDTO projectDTO = JSONObject.parseObject(responseEntity.getBody().toString(), ProjectDTO.class);
        projectDTO = getProject(projectDTO.getId());
        return projectDTO;
    }

    public static void sendEmail(String oid) {
        HttpHeaders httpHeaders = getPostHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<JsonNode> responseEntity = null;
        responseEntity = restTemplate.exchange(hostProd + sendEmail + "/{oid}", HttpMethod.POST, httpEntity, JsonNode.class, oid);
        System.out.println(oid + " " + responseEntity.getBody().toString());
    }

    public static void printPDF(String oid) {
        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<JsonNode> responseEntity = null;
        responseEntity = restTemplate.exchange(hostProd + printPdf + "/" + oid, HttpMethod.GET, httpEntity, JsonNode.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            System.out.println(oid + " OK");
        } else {
            System.out.println(oid + " FALSE");
        }
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + "d941c37b-8677-49f9-a8a3-71eabfa2e147");
        return httpHeaders;
    }

    private static HttpHeaders getPostHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + "93e50d4f-d004-4afa-b00e-90336e17a43d");
        return httpHeaders;
    }

}
