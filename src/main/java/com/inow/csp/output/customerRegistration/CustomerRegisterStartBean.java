package com.inow.csp.output.customerRegistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerRegisterStartBean {
    @JsonProperty("id")
    private String id;

    @JsonProperty("RegisterToken")
    private String registerToken;

    @JsonProperty("ResponseParams")
    private ResponseParams[] responseParams;

    @JsonProperty("CustomerRegisterQuestions")
    private CustomerRegisterQuestions[] customerRegisterQuestions;

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
        private Error[] errors;

        @JsonProperty("CMMParams")
        private CMMParams[] cmmParams;

        @JsonProperty("AdditionalParams")
        private AdditionalParams[] additionalParams;

        @JsonProperty("XFDF")
        private XFDF[] xfdf;

        @JsonProperty("TransactionResponseTm")
        private String transactionResponseTm;

        @Data
        public static class Error {
            @JsonProperty("id")
            private String id;

            @JsonProperty("TypeCd")
            private String typeCd;
        }

        @Data
        public static class CMMParams {
            @JsonProperty("id")
            private String id;
        }

        @Data
        public static class AdditionalParams {
            @JsonProperty("id")
            private String id;

            @JsonProperty("Param")
            private Param[] param;

            @Data
            public static class Param {
                @JsonProperty("id")
                private String id;

                @JsonProperty("Name")
                private String name;

                @JsonProperty("Value")
                private String value;
            }
        }

        @Data
        public static class XFDF {
            @JsonProperty("id")
            private String id;
        }
    }

    @Data
    public static class CustomerRegisterQuestions {
        @JsonProperty("id")
        private String id;

        @JsonProperty("NumberRequired")
        private String numberRequired;

        @JsonProperty("CustomerRegisterQuestion")
        private CustomerRegisterQuestion[] customerRegisterQuestion;

        @Data
        public static class CustomerRegisterQuestion {
            @JsonProperty("id")
            private String id;

            @JsonProperty("Name")
            private String name;

            @JsonProperty("Label")
            private String label;

            @JsonProperty("CustomerRegisterPinDestination")
            private CustomerRegisterPinDestination[] customerRegisterPinDestination;

            @Data
            public static class CustomerRegisterPinDestination {
                @JsonProperty("id")
                private String id;

                @JsonProperty("DestinationTypeCd")
                private String destinationTypeCd;

                @JsonProperty("DestinationCd")
                private String destinationCd;

                @JsonProperty("Description")
                private String description;
            }
        }
    }
}