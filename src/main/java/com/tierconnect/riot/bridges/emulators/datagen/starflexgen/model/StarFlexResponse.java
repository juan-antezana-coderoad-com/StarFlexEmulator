package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import com.google.common.base.Preconditions;
import com.tierconnect.riot.bridges.emulators.utils.JsonUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jantezana on 3/23/16.
 */
public class StarFlexResponse implements StarFlex {

    private String macId;
    private Response response;

    /**
     * Builds an instance of StarFlexResponse
     *
     * @param builder the StarFlexResponseBuilder
     */
    public StarFlexResponse(final StarFlexResponseBuilder builder) {
        Preconditions.checkNotNull(builder);
        this.macId = builder.getMacId();
        this.response = builder.getResponse();
    }

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public String getTopic() {
        return String.format("%s/%s/response", HEADER, macId);
    }

    @Override
    public String getMessage() {
        return String.format("%s", JsonUtils.convertObjectToJson(this.getResponse()));
    }

    /**
     * Gets the value of macId
     *
     * @return the value of macId
     */
    public String getMacId() {
        return macId;
    }

    /**
     * Gets the response
     *
     * @return the response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * Sets the value of macId
     *
     * @param macId the new value of macId
     */
    public void setMacId(final String macId) {
        Preconditions.checkNotNull(macId);
        this.macId = macId;
    }

    /**
     * Response class.
     */
    public static class Response implements Serializable {
        private StatusCode statusCode;
        private String uuid;
        private List<String> body;

        /**
         * Builds an instance of Response.
         *
         * @param builder the ResponseBuilder
         */
        private Response(final ResponseBuilder builder) {
            Preconditions.checkNotNull(builder);
            this.statusCode = builder.getStatusCode();
            this.uuid = builder.getUuid();
            this.body = builder.getBody();
        }

        /**
         * Gets the value of statusCode.
         *
         * @return the value of statusCode
         */
        public StatusCode getStatusCode() {
            return statusCode;
        }

        /**
         * Gets the value of uuid,
         *
         * @return the value of uuid
         */
        public String getUuid() {
            return uuid;
        }

        /**
         * Gets the body.
         *
         * @return the body
         */
        public List<String> getBody() {
            return body;
        }

        /**
         * Sets the value of statusCode.
         *
         * @param statusCode the new value of statusCode
         */
        public void setStatusCode(final StatusCode statusCode) {
            Preconditions.checkNotNull(statusCode);
            this.statusCode = statusCode;
        }

        /**
         * Sets the value of uuid.
         *
         * @param uuid the new value of uuid
         */
        public void setUuid(final String uuid) {
            this.uuid = uuid;
        }

        /**
         * Set the body
         *
         * @param body the new body
         */
        public void setBody(final List<String> body) {
            Preconditions.checkNotNull(body);
            this.body = body;
        }

        /**
         * Adds an item to body.
         *
         * @param value the value
         */
        public void addBody(final String value) {
            Preconditions.checkNotNull(value);
            this.body.add(value);
        }

        /**
         * Clears the items of body
         */
        public void clearBody() {
            this.body.clear();
        }

        /**
         * ResponseBuilder class.
         */
        public static class ResponseBuilder {
            public static final StatusCode DEFAULT_STATUS_CODE;
            public static final String DEFAULT_UUID;
            public static final List<String> DEFAULT_BODY;


            private StatusCode statusCode;
            private String uuid;
            private List<String> body;

            static {
                DEFAULT_STATUS_CODE = StatusCode.OK;
                DEFAULT_UUID = "12345";
                DEFAULT_BODY = new LinkedList<String>();
                DEFAULT_BODY.add("default_vizix_subscription");
                /*
                DEFAULT_BODY.add("extraSusbcription");
                DEFAULT_BODY.add("newSubscription");
                DEFAULT_BODY.add("allAttribs");*/
            }

            /**
             * Gets the value of statusCode
             *
             * @return the value of statusCode
             */
            public StatusCode getStatusCode() {
                return statusCode;
            }

            /**
             * Gets the value of uuid
             *
             * @return the value of uuid
             */
            public String getUuid() {
                return uuid;
            }

            /**
             * Gets the list of items of body.
             *
             * @return the list of items of body
             */
            public List<String> getBody() {
                return body;
            }

            /**
             * Sets the value of statusCode
             *
             * @param statusCode the new value of statusCode
             * @return the ResponseBuilder
             */
            public ResponseBuilder setStatusCode(final StatusCode statusCode) {
                this.statusCode = statusCode;
                return this;
            }

            /**
             * Sets the value of uuid
             *
             * @param uuid the new value of uuid
             * @return the ResponseBuilder
             */
            public ResponseBuilder setUuid(final String uuid) {
                this.uuid = uuid;
                return this;
            }

            /**
             * Sets the body
             *
             * @param body the new body
             * @return the ResponseBuilder
             */
            public ResponseBuilder setBody(final List<String> body) {
                this.body = body;
                return this;
            }

            /**
             * Sets all values with default values
             *
             * @return the ResponseBuilder
             */
            public ResponseBuilder setDefaultValues() {
                this.statusCode = DEFAULT_STATUS_CODE;
                this.uuid = DEFAULT_UUID;
                this.body = DEFAULT_BODY;
                return this;
            }

            /**
             * Sets all values with random values.
             *
             * @return the ResponseBuilder
             */
            public ResponseBuilder setRandomValues() {
                // TODO: Generate ramdom values.
                return this.setDefaultValues();
            }

            /**
             * Builds a response.
             *
             * @return the Response
             */
            public Response build() {
                return new Response(this);
            }
        }
    }

    /**
     * StarFlexResponseBuilder class.
     */
    public static class StarFlexResponseBuilder {
        private String macId;
        private Response response;

        /**
         * Gets the value of macId
         *
         * @return the value of macId
         */
        public String getMacId() {
            return macId;
        }

        /**
         * Gets the response.
         *
         * @return the response.
         */
        public Response getResponse() {
            return response;
        }

        /**
         * Sets the value of macId
         *
         * @param macId the new value of macId
         * @return the StarFlexResponseBuilder
         */
        public StarFlexResponseBuilder setMacId(String macId) {
            this.macId = macId;
            return this;
        }

        /**
         * Sets the response.
         *
         * @param response the response
         * @return the StarFlexResponseBuilder
         */
        public StarFlexResponseBuilder setResponse(Response response) {
            this.response = response;
            return this;
        }

        /**
         * Sets all values with default values.
         *
         * @return the StarFlexResponseBuilder
         */
        public StarFlexResponseBuilder setDefaultValues() {
            this.response = new Response.ResponseBuilder().setRandomValues().build();
            return this;
        }

        /**
         * Sets all values with random values.
         *
         * @return the StarFlexResponseBuilder
         */
        public StarFlexResponseBuilder setRandomValues() {
            return setDefaultValues();
        }

        /**
         * Builds a StarFlexResponse
         *
         * @return the StarFlexResponse
         */
        public StarFlexResponse build() {
            return new StarFlexResponse(this);
        }

    }
}
