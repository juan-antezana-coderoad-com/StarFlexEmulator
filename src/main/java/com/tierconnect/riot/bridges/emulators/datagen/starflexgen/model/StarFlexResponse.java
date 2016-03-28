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

    public StarFlexResponse(final StarFlexResponseBuilder builder) {
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

    public String getMacId() {
        return macId;
    }

    public Response getResponse() {
        return response;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public static class Response implements Serializable {
        private StatusCode statusCode;
        private String uuid;
        private List<String> body;

        private Response(ResponseBuilder builder) {
            this.statusCode = builder.getStatusCode();
            this.uuid = builder.getUuid();
            this.body = builder.getBody();
        }

        public StatusCode getStatusCode() {
            return statusCode;
        }

        public String getUuid() {
            return uuid;
        }

        public List<String> getBody() {
            return body;
        }

        public void setStatusCode(final StatusCode statusCode) {
            Preconditions.checkNotNull(statusCode);
            this.statusCode = statusCode;
        }

        public void setUuid(final String uuid) {
            this.uuid = uuid;
        }

        public void setBody(final List<String> body) {
            Preconditions.checkNotNull(body);
            this.body = body;
        }

        public void addBody(final String value) {
            Preconditions.checkNotNull(value);
            this.body.add(value);
        }

        public void clearBody() {
            this.body.clear();
        }

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
                DEFAULT_BODY = new LinkedList<String>(

                );
                DEFAULT_BODY.add("default_vizix_subscription");
                DEFAULT_BODY.add("extraSusbcription");
                DEFAULT_BODY.add("newSubscription");
                DEFAULT_BODY.add("allAttribs");
            }

            public StatusCode getStatusCode() {
                return statusCode;
            }

            public String getUuid() {
                return uuid;
            }

            public List<String> getBody() {
                return body;
            }

            public ResponseBuilder setStatusCode(final StatusCode statusCode) {
                this.statusCode = statusCode;
                return this;
            }

            public ResponseBuilder setUuid(final String uuid) {
                this.uuid = uuid;
                return this;
            }

            public ResponseBuilder setBody(final List<String> body) {
                this.body = body;
                return this;
            }

            public ResponseBuilder setDefaultValues() {
                this.statusCode = DEFAULT_STATUS_CODE;
                this.uuid = DEFAULT_UUID;
                this.body = DEFAULT_BODY;
                return this;
            }

            public ResponseBuilder setRandomValues() {
                // TODO: Generate ramdom values.
                return this.setDefaultValues();
            }

            public Response build() {
                return new Response(this);
            }
        }
    }

    public static class StarFlexResponseBuilder {
        private String macId;
        private Response response;

        public String getMacId() {
            return macId;
        }

        public Response getResponse() {
            return response;
        }

        public StarFlexResponseBuilder setMacId(String macId) {
            this.macId = macId;
            return this;
        }

        public StarFlexResponseBuilder setResponse(Response response) {
            this.response = response;
            return this;
        }

        public StarFlexResponseBuilder setDefaultValues() {
            this.response = new Response.ResponseBuilder().setRandomValues().build();
            return this;
        }

        public StarFlexResponseBuilder setRandomValues() {
            return setDefaultValues();
        }

        public StarFlexResponse build() {
            return new StarFlexResponse(this);
        }

    }
}
