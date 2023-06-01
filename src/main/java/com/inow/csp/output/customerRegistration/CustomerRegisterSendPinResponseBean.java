package com.inow.csp.output.customerRegistration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inow.csp.output.ResponseBean;

import lombok.Data;

@Data
public class CustomerRegisterSendPinResponseBean implements ResponseBean {
    @JsonProperty("id")
    private String id;

    @JsonProperty("PinToken")
    private String pinToken;

    @JsonProperty("ResponseParams")
    private ResponseParams[] responseParams;

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
        }

        @Data
        public static class XFDF {
            @JsonProperty("id")
            private String id;
        }
    }
}

