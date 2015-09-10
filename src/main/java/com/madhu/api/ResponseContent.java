package com.madhu.api;


/**
 * @author Madhukar Reddy
 *
 */
public class ResponseContent {

    private String id;

    private int httpStatusCode;

    protected ResponseContent(ResponseContentBuilder builder) {
        this.id = builder.id;
        this.httpStatusCode = builder.httpStatusCode;
    }

    public static class ResponseContentBuilder {

        private String id;
        private int httpStatusCode;

        public ResponseContentBuilder streamId(String input) {
            this.id = input;
            return this;
        }

        public ResponseContentBuilder httpStatusCode(Integer input) {
            this.httpStatusCode = input;
            return this;
        }

        public ResponseContent build() {
            return new ResponseContent(this);
        }
    }

    public static ResponseContentBuilder builder() {
        return new ResponseContentBuilder();
    }

}
