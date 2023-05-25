package com.inow.csp.output.customerRegistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inow.csp.output.ResponseBean;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRegValidationByAnswerResponseBean implements ResponseBean {

    @JsonProperty("id")
    private String id;

    @JsonProperty("RegisterToken")
    private String registerToken;

    @JsonProperty("ResponseParams")
    private List<ResponseParams> responseParams;

    @JsonProperty("DTOCustomer")
    private List<DTOCustomer> dtoCustomer;

    @Data
    public static class ResponseParams {
        @JsonProperty("id")
        private String id;

        @JsonProperty("RqUID")
        private String rqUID;

        @JsonProperty("ConversationId")
        private String conversationId;

        @JsonProperty("TransactionResponseDt")
        private String transactionResponseDt;

        @JsonProperty("TransactionTime")
        private String transactionTime;

        @JsonProperty("UserId")
        private String userId;

        @JsonProperty("Errors")
        private List<Error> errors;

        @JsonProperty("CMMParams")
        private List<CMMParam> cmmParams;

        @JsonProperty("AdditionalParams")
        private List<AdditionalParam> additionalParams;

        @JsonProperty("XFDF")
        private List<XFDF> xfdf;

        @JsonProperty("TransactionResponseTm")
        private String transactionResponseTm;
    }

    @Data
    public static class Error {
        @JsonProperty("id")
        private String id;

        @JsonProperty("TypeCd")
        private String typeCd;
    }

    @Data
    public static class CMMParam {
        @JsonProperty("id")
        private String id;
    }

    @Data
    public static class AdditionalParam {
        @JsonProperty("id")
        private String id;

        @JsonProperty("Param")
        private List<Param> params;
    }

    @Data
    public static class Param {
        @JsonProperty("id")
        private String id;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Value")
        private String value;
    }

    @Data
    public static class XFDF {
        @JsonProperty("id")
        private String id;
    }

    @Data
    public static class DTOCustomer {
        @JsonProperty("id")
        private String id;

        @JsonProperty("SystemId")
        private String systemId;

        @JsonProperty("CustomerNumber")
        private String customerNumber;

        @JsonProperty("IndexName")
        private String indexName;

        @JsonProperty("Status")
        private String status;

        @JsonProperty("EntityTypeCd")
        private String entityTypeCd;

        @JsonProperty("Version")
        private String version;

        @JsonProperty("PreferredDeliveryMethod")
        private String preferredDeliveryMethod;

        @JsonProperty("PartyInfo")
        private List<PartyInfo> partyInfo;
    }

    @Data
    public static class PartyInfo {
        @JsonProperty("id")
        private String id;

        @JsonProperty("PartyTypeCd")
        private String partyTypeCd;

        @JsonProperty("PersonInfo")
        private List<PersonInfo> personInfo;

        @JsonProperty("EmailInfo")
        private List<EmailInfo> emailInfo;

        @JsonProperty("PhoneInfo")
        private List<PhoneInfo> phoneInfo;

        @JsonProperty("NameInfo")
        private List<NameInfo> nameInfo;

        @JsonProperty("Addr")
        private List<Addr> addr;
    }

    @Data
    public static class PersonInfo {
        @JsonProperty("id")
        private String id;

        @JsonProperty("PersonTypeCd")
        private String personTypeCd;
    }

    @Data
    public static class EmailInfo {
        @JsonProperty("id")
        private String id;

        @JsonProperty("EmailTypeCd")
        private String emailTypeCd;

        @JsonProperty("EmailAddr")
        private String emailAddr;

        @JsonProperty("PreferredInd")
        private String preferredInd;
    }

    @Data
    public static class PhoneInfo {
        @JsonProperty("id")
        private String id;

        @JsonProperty("PhoneTypeCd")
        private String phoneTypeCd;

        @JsonProperty("PhoneNumber")
        private String phoneNumber;

        @JsonProperty("PreferredInd")
        private String preferredInd;

        @JsonProperty("PhoneName")
        private String phoneName;
    }

    @Data
    public static class NameInfo {
        @JsonProperty("id")
        private String id;

        @JsonProperty("NameTypeCd")
        private String nameTypeCd;

        @JsonProperty("GivenName")
        private String givenName;

        @JsonProperty("Surname")
        private String surname;

        @JsonProperty("CommercialName")
        private String commercialName;
    }

    @Data
    public static class Addr {
        @JsonProperty("id")
        private String id;

        @JsonProperty("AddrTypeCd")
        private String addrTypeCd;

        @JsonProperty("Addr1")
        private String addr1;

        @JsonProperty("City")
        private String city;

        @JsonProperty("StateProvCd")
        private String stateProvCd;

        @JsonProperty("PostalCode")
        private String postalCode;

        @JsonProperty("PrimaryNumber")
        private String primaryNumber;

        @JsonProperty("StreetName")
        private String streetName;

        @JsonProperty("Suffix")
        private String suffix;
    }
}

