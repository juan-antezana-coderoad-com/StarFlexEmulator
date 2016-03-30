package com.tierconnect.riot.bridges.emulators.datagen.starflexgen.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.tierconnect.riot.bridges.emulators.utils.JsonUtils;

import java.io.Serializable;

/**
 * Created by jantezana on 3/23/16.
 */
public class StarFlexRequest implements StarFlex {

    private String macId;
    private Request request;

    /**
     * Command enumerator.
     */
    public static enum Command {
        RFID_SUBSCRIPTIONS("rfid/subscriptions"),
        RFID_EVENTS_DEFAULT_VIZIX_SUBSCRIPTIONS("rfid/events/default_vizix_subscription");

        private String value;

        /**
         * Builds an instance of Command
         *
         * @param value the value
         */
        Command(final String value) {
            Preconditions.checkNotNull(value);
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        @JsonValue
        public String getValue() {
            return value;
        }

        /**
         * Gets a Command from string.
         *
         * @param value the value
         * @return the Command
         */
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

    /**
     * Builds an instance of StarFlexRequest
     *
     * @param builder the StarFlexRequestBuilder
     */
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

    /**
     * Gets the value of macId
     *
     * @return the value of macId
     */
    public String getMacId() {
        return macId;
    }

    /**
     * Gets the request
     *
     * @return the request
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Sets the value of macId
     *
     * @param macId the new value of macId
     */
    public void setMacId(String macId) {
        this.macId = macId;
    }

    /**
     * Sets the request
     *
     * @param request the new request
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Request class.
     */
    public static class Request implements Serializable {
        private int uuid;
        private RequestMethod method;
        private String cmd;

        /**
         * Default constructor of Request
         */
        public Request() {
        }

        /**
         * Builds an instance of Request
         *
         * @param uuid   the value of uuid
         * @param method the method
         * @param cmd    the command
         */
        public Request(final int uuid, final RequestMethod method, final String cmd) {
            Preconditions.checkNotNull(method);
            Preconditions.checkNotNull(cmd);
            this.uuid = uuid;
            this.method = method;
            this.cmd = cmd;
        }

        /**
         * Builds an instance of Request
         *
         * @param builder the RequestBuilder
         */
        private Request(final RequestBuilder builder) {
            this.uuid = builder.getUuid();
            this.method = builder.getMethod();
            this.cmd = builder.getCmd();
        }

        /**
         * Gets the uuid
         *
         * @return the uuid
         */
        public int getUuid() {
            return uuid;
        }

        /**
         * Gets the method
         *
         * @return the method
         */
        public RequestMethod getMethod() {
            return method;
        }

        /**
         * Gets the cmd
         *
         * @return the cmd
         */
        public String getCmd() {
            return cmd;
        }

        /**
         * Sets the uuid
         *
         * @param uuid the new uuid
         */
        public void setUuid(int uuid) {
            this.uuid = uuid;
        }

        /**
         * Sets the method
         *
         * @param method the new method
         */
        public void setMethod(RequestMethod method) {
            this.method = method;
        }

        /**
         * Sets the cmd
         *
         * @param cmd the new cmd
         */
        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        /**
         * RequestBuilder class.
         */
        public static class RequestBuilder {
            public static final int DEFAULT_UUID = 12345;
            public static final String DEFAULT_CMD = "rfid/subscriptions";
            private int uuid;
            private RequestMethod method;
            private String cmd;

            /**
             * Gets the uuid
             *
             * @return the uuid
             */
            public int getUuid() {
                return uuid;
            }

            /**
             * Gets the method
             *
             * @return the method
             */
            public RequestMethod getMethod() {
                return method;
            }

            /**
             * Gets the cmd
             *
             * @return the cmd
             */
            public String getCmd() {
                return cmd;
            }

            /**
             * Sets the uuid
             *
             * @param uuid the new uuid
             * @return the RequestBuilder
             */
            public RequestBuilder setUuid(int uuid) {
                this.uuid = uuid;
                return this;
            }

            /**
             * Sets the method
             *
             * @param method the new method
             * @return the RequestBuilder
             */
            public RequestBuilder setMethod(RequestMethod method) {
                this.method = method;
                return this;
            }

            /**
             * Sets the cmd
             *
             * @param cmd the new cmd
             * @return the RequestBuilder
             */
            public RequestBuilder setCmd(String cmd) {
                this.cmd = cmd;
                return this;
            }

            /**
             * Sets all values with default values.
             *
             * @return the RequestBuilder
             */
            public RequestBuilder setDefaultValues() {
                this.uuid = DEFAULT_UUID;
                this.method = RequestMethod.GET;
                this.cmd = DEFAULT_CMD;

                return this;
            }

            /**
             * Sets all values with random values
             *
             * @return the RequestBuilder
             */
            public RequestBuilder setRandomValues() {
                return this.setDefaultValues();
            }

            /**
             * Builds a Request
             *
             * @return the Request
             */
            public Request build() {
                return new Request(this);
            }
        }
    }

    /**
     * StarFlexRequestBuilder class.
     */
    public static class StarFlexRequestBuilder {
        private String macId;
        private Request request;

        /**
         * Gets the value of macId
         *
         * @return the value of macId
         */
        public String getMacId() {
            return macId;
        }

        /**
         * Gets the request
         *
         * @return the request
         */
        public Request getRequest() {
            return request;
        }

        /**
         * Sets the value of macId
         *
         * @param macId the new value of macId
         * @return the StarFlexRequestBuilder
         */
        public StarFlexRequestBuilder setMacId(final String macId) {
            this.macId = macId;
            return this;
        }

        /**
         * Sets the request
         *
         * @param request the new request
         * @return the StarFlexRequestBuilder
         */
        public StarFlexRequestBuilder setRequest(final Request request) {
            this.request = request;
            return this;
        }

        /**
         * Sets all values with default values.
         *
         * @return the StarFlexRequestBuilder
         */
        public StarFlexRequestBuilder setDefaultValues() {
            this.request = new Request.RequestBuilder().setDefaultValues().build();
            return this;
        }

        /**
         * Sets all values with ramdom values
         *
         * @return the StarFlexRequestBuilder
         */
        public StarFlexRequestBuilder setRandomValues() {
            return this.setDefaultValues();
        }

        /**
         * Builds a StarFlexRequest
         *
         * @return the StarFlexRequest
         */
        public StarFlexRequest build() {
            return new StarFlexRequest(this);
        }
    }

}
