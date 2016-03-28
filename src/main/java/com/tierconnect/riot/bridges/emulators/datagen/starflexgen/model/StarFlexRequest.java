package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Optional;
import com.tierconnect.riot.bridges.emulators.utils.JsonUtils;

import java.io.Serializable;

/**
 * Created by jantezana on 3/23/16.
 */
public class StarFlexRequest implements StarFlex {

    private String macId;
    private Request request;

    public static enum Command {
        RFID_SUBSCRIPTIONS("rfid/subscriptions"),
        RFID_EVENTS_DEFAULT_VIZIX_SUBSCRIPTIONS("rfid/events/default_vizix_subscription");

        private String value;

        Command(final String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        public static Optional<Command> fromString(final String value) {
            Command result = null;
            for (Command command :
                    Command.values()) {
                if (command.getValue().equalsIgnoreCase(value)) {
                    result = command;
                    break;
                }
            }

            return Optional.fromNullable(result);
        }
    }


    private StarFlexRequest(StarFlexRequestBuilder builder) {
        this.macId = builder.getMacId();
        this.request = builder.getRequest();
    }

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public String getTopic() {
        return String.format("%s/%s/request", this.getHeader(), this.getMacId());
    }

    @Override
    public String getMessage() {
        return String.format("%s", JsonUtils.convertObjectToJson(this.getRequest()));
    }

    public String getMacId() {
        return macId;
    }

    public Request getRequest() {
        return request;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public static class Request implements Serializable {
        private int uuid;
        private RequestMethod method;
        private String cmd;

        public Request() {
        }

        public Request(int uuid, RequestMethod method, String cmd) {
            this.uuid = uuid;
            this.method = method;
            this.cmd = cmd;
        }

        private Request(RequestBuilder builder) {
            this.uuid = builder.getUuid();
            this.method = builder.getMethod();
            this.cmd = builder.getCmd();
        }

        public int getUuid() {
            return uuid;
        }

        public RequestMethod getMethod() {
            return method;
        }

        public String getCmd() {
            return cmd;
        }

        public void setUuid(int uuid) {
            this.uuid = uuid;
        }

        public void setMethod(RequestMethod method) {
            this.method = method;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public static class RequestBuilder {
            public static final int DEFAULT_UUID = 12345;
            public static final String DEFAULT_CMD = "rfid/subscriptions";
            private int uuid;
            private RequestMethod method;
            private String cmd;

            public int getUuid() {
                return uuid;
            }

            public RequestMethod getMethod() {
                return method;
            }

            public String getCmd() {
                return cmd;
            }

            public RequestBuilder setUuid(int uuid) {
                this.uuid = uuid;
                return this;
            }

            public RequestBuilder setMethod(RequestMethod method) {
                this.method = method;
                return this;
            }

            public RequestBuilder setCmd(String cmd) {
                this.cmd = cmd;
                return this;
            }

            public RequestBuilder setDefaultValues() {
                this.uuid = DEFAULT_UUID;
                this.method = RequestMethod.GET;
                this.cmd = DEFAULT_CMD;

                return this;
            }

            public RequestBuilder setRandomValues() {
                return this.setDefaultValues();
            }

            public Request build() {
                return new Request(this);
            }
        }
    }

    public static class StarFlexRequestBuilder {
        private String macId;
        private Request request;

        public String getMacId() {
            return macId;
        }

        public Request getRequest() {
            return request;
        }

        public StarFlexRequestBuilder setMacId(final String macId) {
            this.macId = macId;
            return this;
        }

        public StarFlexRequestBuilder setRequest(final Request request) {
            this.request = request;
            return this;
        }

        public StarFlexRequestBuilder setDefaultValues() {
            this.request = new Request.RequestBuilder().setDefaultValues().build();
            return this;
        }

        public StarFlexRequestBuilder setRandomValues() {
            return this.setDefaultValues();
        }

        public StarFlexRequest build() {
            return new StarFlexRequest(this);
        }
    }

}